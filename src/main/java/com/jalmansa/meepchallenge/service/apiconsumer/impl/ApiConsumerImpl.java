package com.jalmansa.meepchallenge.service.apiconsumer.impl;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jalmansa.meepchallenge.domain.VehicleResource;
import com.jalmansa.meepchallenge.domain.Vehicles;
import com.jalmansa.meepchallenge.exception.UnexpectedApiResponseException;
import com.jalmansa.meepchallenge.service.apiconsumer.ApiConsumer;
import com.jalmansa.meepchallenge.service.apiconsumer.ApiProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ApiConsumerImpl implements ApiConsumer {

    private RestTemplate restTemplate;
    private ApiProperties apiProperties;
    private UrlValidator urlValidator;

    @Autowired
    public ApiConsumerImpl(ApiProperties apiProperties, UrlValidator urlValidator, RestTemplate restTemplate) {
        this.apiProperties = apiProperties;
        this.urlValidator = urlValidator;
        this.restTemplate = restTemplate;
    }

    @Override
    public Vehicles execute() throws UnexpectedApiResponseException {
        validateParams();
        ResponseEntity<VehicleResource[]> response = perfomApiCall();

        if(response.getStatusCode() != HttpStatus.OK) {
            throw new UnexpectedApiResponseException("API returned an unexpected response. Skipping update");
        }

        VehicleResource[] responseBody = Optional
                .ofNullable(response)
                .map(ResponseEntity::getBody)
                .orElse(new VehicleResource[0]);
        return Vehicles.of(responseBody);
    }

    private ResponseEntity<VehicleResource[]> perfomApiCall() {
        String targetUrl = buildUrl();
        log.info("Perform HTTP GET call to: " + targetUrl);
        return restTemplate.getForEntity(targetUrl, VehicleResource[].class);
    }

    private void validateParams() {
        Assert.notNull(apiProperties.getBaseUrl(), "API base URL must be valid!");
        Assert.isTrue(urlValidator.isValid(apiProperties.getBaseUrl()), "Invalid base URL");
        Assert.isTrue(StringUtils.hasLength(apiProperties.getCity()), "City must has value!");
    }

    private String buildUrl() {
        return UriComponentsBuilder.fromUriString(apiProperties.getBaseUrl())
                .path(MessageFormat.format("/{0}/resources", apiProperties.getCity()))
                .queryParams(buildQueryParams())
                .build()
                .encode()
                .toUri()
                .toString();
    }

    private MultiValueMap<String, String> buildQueryParams() {
        MultiValueMap<String, String> queryParams = new LinkedMultiValueMap<>();
        Map<String, String> params = apiProperties.getQueryParams();
        params.entrySet().forEach(entry -> queryParams.add(entry.getKey(), entry.getValue()));
        return queryParams;
    }

}
