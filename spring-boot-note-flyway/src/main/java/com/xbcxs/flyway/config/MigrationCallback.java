package com.xbcxs.flyway.config;

import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 生命周期中事件扩展
 *
 * @author Xiao
 */
@Component
public class MigrationCallback implements Callback {

    private static final Logger logger = LoggerFactory.getLogger(MigrationCallback.class);

    /**
     * 开启事件支持
     *
     * @param event
     * @param context
     * @return
     */
    @Override
    public boolean supports(Event event, Context context) {
        switch (event) {
            case BEFORE_MIGRATE:
            case AFTER_MIGRATE:
            case AFTER_EACH_MIGRATE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Whether this event can be handled in a transaction or whether it must be handled outside a transaction instead.
     *
     * @param event
     * @param context
     * @return
     */
    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return false;
    }

    /**
     * 事件处理
     * 打印脚本执行信息
     * @param event
     * @param context
     */
    @Override
    public void handle(Event event, Context context) {
        if (event.equals(Event.BEFORE_MIGRATE)) {
            logger.info(Event.AFTER_MIGRATE.toString());
        } else if (event.equals(Event.AFTER_MIGRATE)) {
            logger.info(Event.AFTER_MIGRATE.toString());
        } else if (event.equals(Event.AFTER_EACH_MIGRATE)) {
            MigrationInfo migrationInfo = context.getMigrationInfo();
            logger.info("Flyway script:{} finished！", migrationInfo != null ? migrationInfo.getScript() : null);
        }
    }
}
