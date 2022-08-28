package com.backend.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SkyscannerConfig {

    @Value("${skyscanner.api.endpoint}")
    private String skyscannerApiEndpoint;

    @Value("${skyscanner.api.key}")
    private String skyscannerApiKey;
}
