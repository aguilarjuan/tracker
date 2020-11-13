package com.fiera.tracker.service;

import com.fiera.tracker.message.TrackerStatisticsDTO;
import com.fiera.tracker.model.Tracker;

public interface TrackerStatisticsService {

    TrackerStatisticsDTO getRedirect(Tracker tracker);
}
