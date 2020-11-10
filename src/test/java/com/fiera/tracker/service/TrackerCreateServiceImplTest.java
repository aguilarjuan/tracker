package com.fiera.tracker.service;

import com.fiera.tracker.mapper.TrackerMapper;
import com.fiera.tracker.message.TrackerDTO;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.repository.TrackerRepository;
import com.fiera.tracker.service.impl.TrackerCreateServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class TrackerCreateServiceImplTest {

    @MockBean
    TrackerRepository repository;

    @MockBean
    TrackerMapper trackerMapper;

    @Autowired
    TrackerCreateServiceImpl trackerCreateServiceImpl;

    @Test
    void createTrackerOK() {

        String url = "http://fierastudios.com";
        Tracker trackerModel = new Tracker();
        trackerModel.setId("1");
        trackerModel.setValid(true);
        trackerModel.setTarget(url);
        trackerModel.setLink("http://localhost:8080/l/qasd233");
        Mockito.when(repository.save(Mockito.any(Tracker.class))).thenReturn(trackerModel);

        TrackerDTO trackerDTO = new TrackerDTO();
        trackerDTO.setValid(true);
        trackerDTO.setLink("http://localhost:8080/l/qasd233");
        trackerDTO.setTarget(url);
        Mockito.when(trackerMapper.toDto(Mockito.any(Tracker.class))).thenReturn(trackerDTO);
        Mockito.when(trackerMapper.toModel(Mockito.anyString(),Mockito.anyString())).thenReturn(trackerModel);

        TrackerDTO resultObtained = trackerCreateServiceImpl.createTracker(url);

        assertNotNull(resultObtained);
    }

}