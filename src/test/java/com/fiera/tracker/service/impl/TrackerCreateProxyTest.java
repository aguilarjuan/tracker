package com.fiera.tracker.service.impl;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerStatistics;
import com.fiera.tracker.repository.TrackerRepository;
import com.fiera.tracker.repository.TrackerStatisticsRepository;
import com.fiera.tracker.service.TrackerCreateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackerCreateProxyTest {

    @MockBean
    @Qualifier("trackerCreateServiceImpl")
    private TrackerCreateServiceImpl TrackerCreateServiceImpl;

    @Autowired
    @Qualifier("trackerCreateProxy")
    private TrackerCreateService trackerCreateProxy;

    @MockBean
    private TrackerRepository trackerRepository;

    @MockBean
    private TrackerStatisticsRepository trackerStatisticsRepository;

    final static String URL_VALID = "https://www.fierastudios.com";

    @Test
    void createTracker() {

        Tracker trackerModelMock = new Tracker();
        trackerModelMock.setId("1");
        trackerModelMock.setValid(true);
        trackerModelMock.setTarget(URL_VALID);

        Mockito.when(TrackerCreateServiceImpl.createTracker(URL_VALID,"1234",null)).thenReturn(trackerModelMock);

        TrackerStatistics trackerStatisticsMock = new TrackerStatistics();
        trackerStatisticsMock.setId("1");
        trackerStatisticsMock.setConnections(0);

        Mockito.when(trackerStatisticsRepository.save(Mockito.any(TrackerStatistics.class))).thenReturn(trackerStatisticsMock);
        Mockito.when(trackerRepository.save(Mockito.any(Tracker.class))).thenReturn(trackerModelMock);

        Tracker response = trackerCreateProxy.createTracker(URL_VALID,"1234",null);

        assertNotNull(response.getTrackerStatistics());

    }

}