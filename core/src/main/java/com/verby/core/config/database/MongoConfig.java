package com.verby.core.config.database;

import com.verby.core.cover.query.dao.CoverQueryDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = CoverQueryDao.class)
public class MongoConfig {
}
