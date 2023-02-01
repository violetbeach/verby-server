package com.verby.internalconsumerserver.config;

import com.verby.internalconsumerserver.cover.CoverQueryModelRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackageClasses = {CoverQueryModelRepository.class})
public class MongoConfig {
}
