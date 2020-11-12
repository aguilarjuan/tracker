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
    public String getUrl(Tracker TrackerModel) {
            String response = trackerRedirectServiceImpl.getUrl(TrackerModel);
            updateDataStatistics(TrackerModel);
            return response;
    }

    private void updateDataStatistics(Tracker trackerModel){
          int TotalCurrentConnection = trackerModel.getTrackerStatistics().getConnections() + 1;
          TrackerStatistics currentTrackerStatistics = trackerModel.getTrackerStatistics();
          currentTrackerStatistics.setConnections(TotalCurrentConnection);
          trackerStatisticsRepository.save(currentTrackerStatistics);
    }

}