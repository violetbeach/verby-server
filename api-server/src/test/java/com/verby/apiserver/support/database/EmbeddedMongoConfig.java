package com.verby.apiserver.support.database;

import com.verby.apiserver.support.repository.cover.TestCoverQueryModelRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = TestCoverQueryModelRepository.class)
public class EmbeddedMongoConfig {
}
