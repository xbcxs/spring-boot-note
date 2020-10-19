package com.xbcxs.quartz.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SampleJob
 *
 * @author Xiao
 */
public class CommonJob implements Job {

    private static final Logger logger = LoggerFactory.getLogger(CommonJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        logger.info("CommonJob execute...");
        // 根据jobName定制触发业务实现
        logger.info(jobExecutionContext.getMergedJobDataMap().get("jobName").toString());
    }
}


