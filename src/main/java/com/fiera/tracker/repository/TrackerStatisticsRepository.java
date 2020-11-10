package com.fiera.tracker.repository;

import com.fiera.tracker.model.TrackerStatistics;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerStatisticsRepository extends MongoRepository<TrackerStatistics, String> {
}
