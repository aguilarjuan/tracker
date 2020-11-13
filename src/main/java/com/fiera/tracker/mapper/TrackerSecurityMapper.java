package com.fiera.tracker.mapper;

import com.fiera.tracker.model.TrackerSecurity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class TrackerSecurityMapper {

   final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public TrackerSecurity toModel(String password, String expirationDate){
        TrackerSecurity trackerSecurity = new TrackerSecurity();
        trackerSecurity.setPassword(password);
        trackerSecurity.setExpirationDate(expirationDate != null ? LocalDate.parse(expirationDate, formatter) : null);
        return trackerSecurity;
    }
}
