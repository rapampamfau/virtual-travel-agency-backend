package com.backend.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class AdminConfigTests {

    @Test
    void shouldNotShowInformation() {
        //Given
        AdminConfig adminConfig = new AdminConfig();
        //When
        String result = adminConfig.getAdminMail();
        //Then
        assertNull(result);
    }
}
