package com.learn.journalapp.service.impl;

import com.learn.journalapp.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MongoTemplateQueriesTests {

    @Autowired
    private MongoTemplateQueries mongoTemplateQueries;

    @Test
    void testGetUSerBySA() {
     List<User> user = mongoTemplateQueries.getUSerBySA();
     assertNotNull(user);
    }
}