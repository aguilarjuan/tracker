package com.fiera.tracker.repository;

import com.fiera.tracker.model.Tracker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackerRepository extends MongoRepository<Tracker, String> {
    List<Tracker> findByValidIsTrueAndLink(String link);
}
