package com.fiera.tracker.service;

import com.fiera.tracker.model.Tracker;

public interface TrackerCreateService {

    Tracker createTracker(String url, String password, String expirationDate);
}
