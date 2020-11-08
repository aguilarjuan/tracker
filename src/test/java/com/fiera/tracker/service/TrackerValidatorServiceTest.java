package com.fiera.tracker.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackerValidatorServiceTest {

    @Autowired
    TrackerValidatorService trackerValidatorService;

    @Test
    void validateUrlOK() {
        assertFalse(trackerValidatorService.validateUrl("123"));
    }

    @Test
    void validateUrlFail() {
        assertFalse(!trackerValidatorService.validateUrl("123"));
    }

}