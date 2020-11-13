package com.fiera.tracker.service;

import com.fiera.tracker.model.Tracker;

import java.util.Optional;

public interface TrackerValidatorService {

    boolean invalidateLink(String link);
    boolean allowAccess(Tracker tracker, String password);
    Optional<Tracker> getTrackerModel(String link);

}
