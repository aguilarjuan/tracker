package com.fiera.tracker.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TrackerServiceTest {

    @Autowired
    TrackerService trackerService;

    @Test
    void createTrackerOK() {
        assertNotNull(null);
    }

    @Test
    void createTrackerFail() {
        assertNotNull(null);
    }

}