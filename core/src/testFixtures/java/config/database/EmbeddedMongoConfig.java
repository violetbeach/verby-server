package config.database;

import com.verby.core.cover.query.dao.CoverQueryDao;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import support.repository.TestCoverQueryModelRepository;

@Configuration
@EnableMongoRepositories(basePackageClasses = {TestCoverQueryModelRepository.class, CoverQueryDao.class})
public class EmbeddedMongoConfig {
}
