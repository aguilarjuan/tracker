package com.fiera.tracker.service.impl;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.repository.TrackerRepository;
import com.fiera.tracker.repository.TrackerStatisticsRepository;
import com.fiera.tracker.service.TrackerRedirectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Qualifier("trackerRedirectProxy")
public class TrackerRedirectProxy implements TrackerRedirectService {

    @Value("${tracker.urlLink}")
    private String urlLink;

    @Autowired
    @Qualifier("redirectServiceImpl")
    private TrackerRedirectServiceImpl trackerRedirectService;

    @Autowired
    private TrackerRepository trackerRepository;

    @Autowired
    private TrackerStatisticsRepository trackerStatisticsRepository;

    @Override
    public Optional<String> getUrl(String link) {
        String url = urlLink + link;
        List<Tracker> trackerList = trackerRepository.findByLink(url);
        if(trackerList != null && !trackerList.isEmpty()){
            Tracker TrackerModel = trackerList.get(0);
           // Optional<String> response = trackerRedirectService.getUrl(Tracker.getTarget());
            updateDataStatistics(TrackerModel);
            return null;
        } else {
            return Optional.empty();
        }
    }

    private void updateDataStatistics(Tracker trackerModel){
        //int currentAmountConnection = trackerStatisticsRepository.deleteAll();
    }

}