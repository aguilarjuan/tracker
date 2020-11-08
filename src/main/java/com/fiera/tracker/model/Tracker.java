package com.fiera.tracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "tracker")
public class Tracker {

    @Id
    private String id;
    private String target;
    private String link;
    private boolean valid;
}
