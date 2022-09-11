package com.backend.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class SkyscannerConfigTests {

    @Test
    void shouldNotShowInformation() {
        //Given
        SkyscannerConfig config = new SkyscannerConfig();
        //When
        String skyscannerApiEndpoint = config.getSkyscannerApiEndpoint();
        String skyscannerApiKey = config.getSkyscannerApiKey();
        //Then
        assertNull(skyscannerApiEndpoint);
        assertNull(skyscannerApiKey);
    }
}
