package com.fiera.tracker.service.impl;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerStatistics;
import com.fiera.tracker.repository.TrackerRepository;
import com.fiera.tracker.repository.TrackerStatisticsRepository;
import com.fiera.tracker.service.TrackerRedirectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackerRedirectProxyTest {

    @MockBean
    @Qualifier("trackerRedirectServiceImpl")
    private TrackerRedirectServiceImpl trackerRedirectServiceImpl;

    @Autowired
    @Qualifier("trackerRedirectProxy")
    private TrackerRedirectService trackerRedirectService;

    @MockBean
    private TrackerStatisticsRepository trackerStatisticsRepository;

    @MockBean
    private TrackerRepository trackerRepository;

    @Value("${tracker.urlLink}")
    private String urlLink;


    final static String URL_VALID = "https://www.fierastudios.com";

    @Test
    void getUrl() {

        Tracker trackerModelMock = new Tracker();
        trackerModelMock.setId("1");
        trackerModelMock.setValid(true);
        trackerModelMock.setTarget(URL_VALID);

        TrackerStatistics trackerStatisticsMock = new TrackerStatistics();
        trackerStatisticsMock.setId("1");
        trackerStatisticsMock.setConnections(2);

        trackerModelMock.setTrackerStatistics(trackerStatisticsMock);

        Mockito.when(trackerRedirectServiceImpl.getUrl(Mockito.any(Tracker.class))).thenReturn("<html></html>");
        Mockito.when(trackerStatisticsRepository.save(Mockito.any(TrackerStatistics.class))).thenReturn(trackerStatisticsMock);

        String response = trackerRedirectService.getUrl(trackerModelMock);

        assertTrue(response.equals("<html></html>") && trackerStatisticsMock.getConnections() == 3);

    }
}