package com.fiera.tracker.service.impl;

import com.fiera.tracker.mapper.TrackerMapper;
import com.fiera.tracker.mapper.TrackerSecurityMapper;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerSecurity;
import com.fiera.tracker.repository.TrackerRepository;
import com.fiera.tracker.repository.TrackerSecurityRepository;
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
    private TrackerSecurityRepository trackerSecurityRepository;

    @Autowired
    private TrackerMapper trackerMapper;

    @Autowired
    private TrackerSecurityMapper trackerSecurityMapper;

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
    public Tracker createTracker(String url, String password, String expirationDate) {
        TrackerSecurity trackerSecurity = addSecurity(password,expirationDate);
        Tracker model = trackerMapper.toModel(url,generateUrlLink());
        model.setTrackerSecurity(trackerSecurity);
        trackerRepository.save(model);
        return model;
    }

    private TrackerSecurity addSecurity(String password, String expirationDate){
        if((password != null && !password.isEmpty()) || expirationDate != null && !expirationDate.isEmpty()){
           return trackerSecurityRepository.save(trackerSecurityMapper.toModel(password,expirationDate));
        } else {
            return null;
        }
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


