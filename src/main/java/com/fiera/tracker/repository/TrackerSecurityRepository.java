package com.fiera.tracker.repository;

import com.fiera.tracker.model.TrackerSecurity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerSecurityRepository extends MongoRepository<TrackerSecurity, String> {
}
