package com.fiera.tracker.service.impl;

import com.fiera.tracker.mapper.TrackerStatisticsMapper;
import com.fiera.tracker.message.TrackerStatisticsDTO;
import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerStatistics;
import com.fiera.tracker.repository.TrackerStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrackerStatisticsServiceImpl {

    @Autowired
    private TrackerStatisticsRepository trackerStatisticsRepository;

    @Autowired
    private TrackerStatisticsMapper trackerStatisticsMapper;

    public TrackerStatisticsDTO getRedirect(Tracker tracker){
        Optional<TrackerStatistics> trackerStatisticsOptional = trackerStatisticsRepository.findById(tracker.getTrackerStatistics().getId());
        if (trackerStatisticsOptional.isPresent()){
            return trackerStatisticsMapper.toDto(trackerStatisticsOptional.get());
        } else {
            return new TrackerStatisticsDTO();
        }
    }
}
