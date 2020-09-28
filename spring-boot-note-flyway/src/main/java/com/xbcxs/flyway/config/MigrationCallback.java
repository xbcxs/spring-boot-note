package com.xbcxs.flyway.config;

import org.flywaydb.core.api.MigrationInfo;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 生命周期中回调事件
 *
 * @author Xiao
 * @date 2020/9/25
 */
@Component
public class MigrationCallback implements Callback {

    private final Logger log = LoggerFactory.getLogger(MigrationCallback.class);

    /**
     * Whether this callback supports this event or not. This is primarily meant as a way to optimize event handling
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

    @Override
    public void handle(Event event, Context context) {
        if (event.equals(Event.BEFORE_MIGRATE)) {
            log.info(Event.AFTER_MIGRATE.toString());
        } else if (event.equals(Event.AFTER_MIGRATE)) {
            log.info(Event.AFTER_MIGRATE.toString());
        } else if (event.equals(Event.AFTER_EACH_MIGRATE)) {
            MigrationInfo migrationInfo = context.getMigrationInfo();
            log.info("Flyway执行脚本:{}完成！", migrationInfo != null ? migrationInfo.getScript() : null);
        }
    }
}
