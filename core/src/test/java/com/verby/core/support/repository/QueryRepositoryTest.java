package com.verby.core.support.repository;

import config.database.EmbeddedMongoConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

@Import({EmbeddedMongoConfig.class})
@DataMongoTest
public class QueryRepositoryTest {

    @Autowired
    protected MongoTemplate mongoTemplate;

}
