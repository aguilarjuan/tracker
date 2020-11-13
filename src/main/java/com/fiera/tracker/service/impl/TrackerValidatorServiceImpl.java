package com.fiera.tracker.service.impl;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerSecurity;
import com.fiera.tracker.repository.TrackerRepository;
import com.fiera.tracker.repository.TrackerSecurityRepository;
import com.fiera.tracker.service.TrackerValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TrackerValidatorServiceImpl implements TrackerValidatorService {

    @Autowired
    private TrackerRepository trackerRepository;

    @Autowired
    private TrackerSecurityRepository trackerSecurityRepository;

    @Value("${tracker.validation.url.pattern}")
    private String pattern;

    final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public boolean validateUrl(String url){
        return url.matches(pattern);
    }

    @Override
    public boolean invalidateLink(String link){
        Optional<Tracker> trackerOptional = getTrackerModel(link);
        if(trackerOptional.isPresent()){
            Tracker tracker = trackerOptional.get();
            tracker.setValid(false);
            trackerRepository.save(tracker);
            return true;
        } {
            return false;
        }
    }

    @Override
    public boolean allowAccess(Tracker tracker, String password){
        Optional<TrackerSecurity> securityOptional = trackerSecurityRepository.findById(tracker.getTrackerSecurity().getId());
        return securityOptional.get().getPassword().equals(password) && validateExpirationDate(securityOptional.get());
    }

    @Override
    public Optional<Tracker> getTrackerModel(String link){
        List<Tracker> trackerList = trackerRepository.findByValidIsTrueAndLink(link);
        if(trackerList != null && !trackerList.isEmpty()){
            return Optional.of(trackerList.get(0));
        } else {
            return Optional.empty();
        }
    }

    private boolean validateExpirationDate(TrackerSecurity trackerSecurity){
       if(trackerSecurity.getExpirationDate() != null ){
          return LocalDate.now().isBefore(trackerSecurity.getExpirationDate());
       } else {
           return true;
       }
    }
}
