package com.fiera.tracker.service;

import java.util.Optional;

public interface TrackerRedirectService {

    Optional<String> getUrl(String link);

}
