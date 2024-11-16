package com.learn.journalapp.repository;

import com.learn.journalapp.entity.Journal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalRepository extends MongoRepository<Journal, String> {
}
