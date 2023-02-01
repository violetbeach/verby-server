package com.verby.internalconsumerserver.support.database;

import com.verby.internalconsumerserver.support.repository.TestCoverQueryModelRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = {TestCoverQueryModelRepository.class})
public class EmbeddedMongoConfig {
}
