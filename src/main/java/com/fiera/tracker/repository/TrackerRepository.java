package com.fiera.tracker.repository;

import com.fiera.tracker.model.Tracker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackerRepository extends MongoRepository<Tracker, String> {

}
