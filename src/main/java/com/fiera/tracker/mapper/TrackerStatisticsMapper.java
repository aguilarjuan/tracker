package com.fiera.tracker.mapper;

import com.fiera.tracker.message.TrackerStatisticsDTO;
import com.fiera.tracker.model.TrackerStatistics;
import org.springframework.stereotype.Component;

@Component
public class TrackerStatisticsMapper {

    public TrackerStatistics toModel(){
        TrackerStatistics trackerStatistics = new TrackerStatistics();
        trackerStatistics.setConnections(0);
        return trackerStatistics;
    }

    public TrackerStatisticsDTO toDto(TrackerStatistics trackerStatistics){
        TrackerStatisticsDTO trackerStatisticsDTO = new TrackerStatisticsDTO();
        trackerStatisticsDTO.setAmountRedirect(trackerStatistics.getConnections());
        return trackerStatisticsDTO;
    }
}
