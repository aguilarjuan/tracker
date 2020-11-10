package com.fiera.tracker.mapper;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerStatistics;
import org.springframework.stereotype.Component;

@Component
public class TrackerStatisticsMapper {

    public TrackerStatistics toModel(Tracker tracker){
        TrackerStatistics trackerStatistics = new TrackerStatistics();
        trackerStatistics.setTracker(tracker);
        trackerStatistics.setConnections(0);
        return trackerStatistics;
    }
}
