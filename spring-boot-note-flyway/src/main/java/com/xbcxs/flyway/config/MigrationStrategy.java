package com.xbcxs.flyway.config;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.stereotype.Component;

/**
 *
 * 基于Springboot自动装载扩展接口实现，自定义migrate步骤
 *
 * @author Xiao
 */
@Component
public class MigrationStrategy implements FlywayMigrationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(MigrationStrategy.class);

    @Override
    public void migrate(Flyway flyway) {
        logger.info("Flyway start...");
        // 用于先修复上次执行失败的语句。
        flyway.repair();
        // 没有失败语句记录，才能执行合并。
        flyway.migrate();
        logger.info("Flyway end...");
    }
}
