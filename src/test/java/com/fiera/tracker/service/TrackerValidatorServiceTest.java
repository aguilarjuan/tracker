package com.fiera.tracker.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackerValidatorServiceTest {

    final static String URL_VALID = "https://www.fierastudios.com";
    final static String URL_INVALID = "htttps://fierastudios.com";

    @Autowired
    TrackerValidatorService trackerValidatorService;

    @Test
    void validateUrlIsValid() {
        assertTrue(trackerValidatorService.validateUrl(URL_VALID));
    }

    @Test
    void validateUrlIsNotValid() {
        assertFalse(trackerValidatorService.validateUrl(URL_INVALID));
    }

}