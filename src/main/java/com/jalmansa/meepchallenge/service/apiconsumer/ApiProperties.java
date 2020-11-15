package com.jalmansa.meepchallenge.service.apiconsumer;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "api")
public class ApiProperties {
    private static final String LOWER_LEFT_LAT_LONG = "lowerLeftLatLon";
    private static final String UPPER_RIGHT_LAT_LONG = "upperRightLatLon";
    private static final String COMPANY_ZONE_IDS = "companyZoneIds";

    private String baseUrl;
    private String squareLowerLeft;
    private String squareUpperRight;
    private String companyZoneIds;
    private String city;

    public Map<String, String> getQueryParams() {
        return Map.ofEntries(
                Map.entry(LOWER_LEFT_LAT_LONG, squareLowerLeft),
                Map.entry(UPPER_RIGHT_LAT_LONG, squareUpperRight),
                Map.entry(COMPANY_ZONE_IDS, companyZoneIds));
    }
}
