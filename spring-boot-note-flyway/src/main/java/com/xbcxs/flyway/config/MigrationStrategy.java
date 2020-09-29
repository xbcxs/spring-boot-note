package com.xbcxs.flyway.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

/**
 * 自定义migrate步骤
 *
 * @author Xiao
 * @date 2020/9/27
 */
@Component
public class MigrationStrategy implements FlywayMigrationStrategy {

    private final Logger log = LoggerFactory.getLogger(MigrationStrategy.class);

    @Override
    public void migrate(Flyway flyway) {
        log.info("Flyway start...");
        flyway.repair();
        flyway.migrate();
        log.info("Flyway end...");
    }
}
