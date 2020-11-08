package com.fiera.tracker.service;

import com.fiera.tracker.mapper.TrackerMapper;
import com.fiera.tracker.message.TrackerDTO;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.repository.TrackerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class TrackerService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Autowired
    private TrackerMapper trackerMapper;

    final static Logger log = LoggerFactory.getLogger(TrackerService.class);

    @Value("${tracker.leftLimit}")
    private int leftLimit;

    @Value("${tracker.rightLimit}")
    private int rightLimit;

    @Value("${tracker.targetStringLength}")
    private int targetStringLength;

    @Value("${tracker.urlLink}")
    private int urlLink;

    public TrackerDTO createTracker(String url) {
        Tracker model = trackerMapper.toModel(url,generateUrlLink());
        return trackerMapper.toDto(trackerRepository.save(model));
    }

    private String generateUrlLink() {
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return urlLink + generatedString;
    }
}


