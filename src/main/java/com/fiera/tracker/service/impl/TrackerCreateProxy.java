package com.fiera.tracker.service.impl;

import com.fiera.tracker.mapper.TrackerStatisticsMapper;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.repository.TrackerStatisticsRepository;
import com.fiera.tracker.service.TrackerCreateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("trackerCreateProxy")
public class TrackerCreateProxy implements TrackerCreateService {

    @Autowired
    private TrackerStatisticsRepository trackerStatisticsRepository;

    @Autowired
    private TrackerStatisticsMapper trackerStatisticsMapper;

    @Autowired
    @Qualifier("trackerCreateServiceImpl")
    private TrackerCreateServiceImpl TrackerCreateServiceImpl;



    @Override
    public Tracker createTracker(String url, String password, String expirationDate) {
     Tracker trackerModel = TrackerCreateServiceImpl.createTracker(url,password,expirationDate);
        createTrackerStatistics(trackerModel);
        return trackerModel;
    }

    private void createTrackerStatistics(Tracker trackerModel){
        trackerStatisticsRepository.save(trackerStatisticsMapper.toModel(trackerModel));
    }
}
