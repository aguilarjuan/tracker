package com.fiera.tracker.service.impl;

import com.fiera.tracker.model.Tracker;
import com.fiera.tracker.model.TrackerSecurity;
import com.fiera.tracker.repository.TrackerRepository;
import com.fiera.tracker.repository.TrackerSecurityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TrackerValidatorService {

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

    public boolean allowAccess(Tracker tracker, String password, String expirationDate){
        Optional<TrackerSecurity> securityOptional = trackerSecurityRepository.findById(tracker.getTrackerSecurity().getId());
        if(securityOptional.isPresent()){
            TrackerSecurity security = securityOptional.get();
            return validatePassword(password,security) || validateExpirationDate(expirationDate,security);
        } else {
            return true;
        }
    }

    public Optional<Tracker> getTrackerModel(String link){
        List<Tracker> trackerList = trackerRepository.findByLink(link);
        if(trackerList != null && !trackerList.isEmpty()){
            return Optional.of(trackerList.get(0));
        } else {
            return Optional.empty();
        }
    }

    private boolean validatePassword(String password,TrackerSecurity trackerSecurity){
        if(password != null && trackerSecurity.getPassword() != null){
            return trackerSecurity.equals(password);
        } else {
            return false;
        }
    }

    private boolean validateExpirationDate(String expirationDate, TrackerSecurity trackerSecurity){
       if(expirationDate != null && trackerSecurity.getExpirationDate() != null ){
          LocalDate expirationDateFormat =  LocalDate.parse(expirationDate, formatter);
          return !expirationDateFormat.isBefore(LocalDate.now());
       } else {
           return false;
       }
    }

}
