package com.fiera.tracker.service.impl;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerSecurity;
import com.fiera.tracker.repository.TrackerRepository;
import com.fiera.tracker.repository.TrackerSecurityRepository;
import com.fiera.tracker.service.TrackerValidatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrackerValidatorServiceImplTest {

    final static String URL_VALID = "https://www.fierastudios.com";
;
    @Autowired
    TrackerValidatorService trackerValidatorService;

    @MockBean
    private TrackerRepository trackerRepository;

    @MockBean
    private TrackerSecurityRepository trackerSecurityRepository;

    @Value("${tracker.validation.url.pattern}")
    private String pattern;




    @Test
    void getTrackerModelEmpty(){
        List<Tracker> mock = new ArrayList<>();
        Mockito.when(trackerRepository.findByValidIsTrueAndLink(Mockito.anyString())).thenReturn(mock);
        Optional<Tracker> response = trackerValidatorService.getTrackerModel(URL_VALID);
        if (response.isPresent()){
            fail();
        }
    }

    @Test
    void incorrectPassword(){

        Tracker trackerModelMock = new Tracker();
        trackerModelMock.setId("1");
        trackerModelMock.setValid(true);
        trackerModelMock.setTarget(URL_VALID);

        TrackerSecurity trackerSecurityMock = new TrackerSecurity();
        trackerSecurityMock.setId("1");
        trackerSecurityMock.setPassword("admin");
        trackerSecurityMock.setExpirationDate(null);

        trackerModelMock.setTrackerSecurity(trackerSecurityMock);

        Optional<TrackerSecurity> mock = Optional.of(trackerSecurityMock);
        Mockito.when(trackerSecurityRepository.findById(Mockito.anyString())).thenReturn(mock);

        assertFalse(trackerValidatorService.allowAccess(trackerModelMock,"1234"));
    }


    @Test
    void invalidateLink(){

        List<Tracker> TrackerMock = new ArrayList<>();

        Tracker trackerModelMock = new Tracker();
        trackerModelMock.setId("1");
        trackerModelMock.setValid(true);
        trackerModelMock.setTarget(URL_VALID);

        TrackerMock.add(trackerModelMock);

        Mockito.when(trackerRepository.findByValidIsTrueAndLink(Mockito.anyString())).thenReturn(TrackerMock);
        Mockito.when(trackerRepository.save(Mockito.any(Tracker.class))).thenReturn(trackerModelMock);

        Boolean result = trackerValidatorService.invalidateLink(URL_VALID);

        assertTrue(result && !trackerModelMock.isValid());

    }


}