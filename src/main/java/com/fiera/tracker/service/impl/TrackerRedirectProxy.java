package com.fiera.tracker.service.impl;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerStatistics;
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
    @Qualifier("trackerRedirectServiceImpl")
    private TrackerRedirectServiceImpl trackerRedirectServiceImpl;

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
            Optional<String> response = trackerRedirectServiceImpl.getUrl(url);
            updateDataStatistics(TrackerModel);
            return response;
        } else {
            return Optional.empty();
        }
    }

    private void updateDataStatistics(Tracker trackerModel){
          int TotalCurrentConnection = trackerModel.getTrackerStatistics().getConnections() + 1;
          TrackerStatistics currentTrackerStatistics = trackerModel.getTrackerStatistics();
          currentTrackerStatistics.setConnections(TotalCurrentConnection);
          trackerStatisticsRepository.save(currentTrackerStatistics);
    }

}