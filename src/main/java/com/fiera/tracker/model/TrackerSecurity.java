package com.fiera.tracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "tracker_security")
public class TrackerSecurity {

    @Id
    private String id;
    private String password;
    private LocalDate expirationDate;

}
