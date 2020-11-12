package com.fiera.tracker.mapper;

import com.fiera.tracker.message.TrackerStatisticsDTO;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerStatistics;
import org.springframework.stereotype.Component;

@Component
public class TrackerStatisticsMapper {

    public TrackerStatistics toModel(Tracker trackerModel){
        TrackerStatistics trackerStatistics = new TrackerStatistics();
        trackerStatistics.setTracker(trackerModel);
        trackerStatistics.setConnections(0);
        return trackerStatistics;
    }

    public TrackerStatisticsDTO toDto(TrackerStatistics trackerStatistics){
        TrackerStatisticsDTO trackerStatisticsDTO = new TrackerStatisticsDTO();
        trackerStatisticsDTO.setQuantityRedirect(trackerStatistics.getConnections());
        return trackerStatisticsDTO;
    }
}
