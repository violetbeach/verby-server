package config.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import support.repository.TestCoverQueryModelRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = TestCoverQueryModelRepository.class)
public class EmbeddedMongoConfig {
}
