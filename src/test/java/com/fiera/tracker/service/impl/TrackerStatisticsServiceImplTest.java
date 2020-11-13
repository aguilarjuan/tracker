package com.fiera.tracker.service.impl;

import com.fiera.tracker.message.TrackerStatisticsDTO;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerStatistics;
import com.fiera.tracker.repository.TrackerStatisticsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackerStatisticsServiceImplTest {

    @MockBean
    private TrackerStatisticsRepository trackerStatisticsRepository;

    @Autowired
    private TrackerStatisticsServiceImpl trackerStatisticsService;

    final static String URL_VALID = "https://www.fierastudios.com";

    @Test
    void getStatistics(){

        Tracker trackerModelMock = new Tracker();
        trackerModelMock.setId("1");
        trackerModelMock.setValid(true);
        trackerModelMock.setTarget(URL_VALID);

        TrackerStatistics trackerStatisticsMock = new TrackerStatistics();
        trackerStatisticsMock.setId("1");
        trackerStatisticsMock.setConnections(23);

        Optional<TrackerStatistics> statisticsOptionalMock = Optional.of(trackerStatisticsMock);

        trackerModelMock.setTrackerStatistics(trackerStatisticsMock);

        Mockito.when(trackerStatisticsRepository.findById(Mockito.anyString())).thenReturn(statisticsOptionalMock);

        TrackerStatisticsDTO trackerStatisticsDTO = trackerStatisticsService.getRedirect(trackerModelMock);

        assertTrue(trackerStatisticsDTO.getAmountRedirect() == 23);

    }
}