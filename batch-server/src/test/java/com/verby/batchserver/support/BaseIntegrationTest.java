package com.verby.batchserver.support;

import config.database.EmbeddedRedisConfig;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
@Import(EmbeddedRedisConfig.class)
public class BaseIntegrationTest {
}
