package com.fiera.tracker.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "tracker_statistics")
public class TrackerStatistics {

    @Id
    private String id;
    private Tracker tracker;
    private Integer connections;
}
