package com.fiera.tracker.service.impl;

import com.fiera.tracker.mapper.TrackerMapper;
import com.fiera.tracker.message.TrackerDTO;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.repository.TrackerRepository;
import com.fiera.tracker.service.TrackerCreateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Qualifier("trackerCreateServiceImpl")
public class TrackerCreateServiceImpl implements TrackerCreateService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Autowired
    private TrackerMapper trackerMapper;

    final static Logger log = LoggerFactory.getLogger(TrackerCreateServiceImpl.class);

    @Value("${tracker.leftLimit}")
    private String leftLimit;

    @Value("${tracker.rightLimit}")
    private String rightLimit;

    @Value("${tracker.targetStringLength}")
    private String targetStringLength;

    @Value("${tracker.urlLink}")
    private String urlLink;

    @Override
    public Tracker createTracker(String url) {
        Tracker model = trackerMapper.toModel(url,generateUrlLink());
        return trackerRepository.save(model);
    }

    private String generateUrlLink() {
        Random random = new Random();
        String generatedString = random.ints(Integer.parseInt(leftLimit), Integer.parseInt(rightLimit) + 1)
                .limit(Integer.parseInt(targetStringLength))
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return urlLink + generatedString;
    }
}


