package com.jalmansa.meepchallenge;

import java.time.Duration;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class MeepchallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeepchallengeApplication.class, args);
    }

    @Bean
    public UrlValidator urlValidator() {
        return new UrlValidator();
    }

    @Bean
    public RestTemplate restTemplate(@Value("${connection.timeout}") int connectionTimeout, @Value("${read.timeout}") int readTimeout) {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(connectionTimeout))
                .setReadTimeout(Duration.ofMillis(readTimeout))
                .build();
    }
}
