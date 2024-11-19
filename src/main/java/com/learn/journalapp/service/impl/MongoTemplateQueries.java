package com.learn.journalapp.service.impl;

import com.learn.journalapp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MongoTemplateQueries {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUSerBySA() {
        Query query = new Query();
        Criteria criteria = new Criteria();
        query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),
                Criteria.where("sentimentalAnalysis").is(true)));
        return mongoTemplate.find(query, User.class);
    }
}
