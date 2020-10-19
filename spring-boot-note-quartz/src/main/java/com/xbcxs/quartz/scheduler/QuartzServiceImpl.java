package com.xbcxs.quartz.scheduler;

import com.xbcxs.quartz.scheduler.job.CommonJob;
import com.xbcxs.quartz.scheduler.mapper.QuartzMapper;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * QuartzServiceImpl
 *
 * @author Xiao
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    private static final Logger logger = LoggerFactory.getLogger(QuartzServiceImpl.class);

    @Autowired
    private Scheduler scheduler;

    @Resource
    QuartzMapper jobAndTriggerMapper;

    private static final String COMMON_JOB_GROUP = "CommonJobGroup";
    private static final String COMMON_TRIGGER_GROUP = "CommonTriggerGroup";
    private static final String POST_TRIGGER = "_trigger";

    /**
     * 生成triggerName
     *
     * @param jobName
     * @return
     */
    private String commonTriggerName(String jobName) {
        return jobName + POST_TRIGGER;
    }

    @Override
    public QuartzDTO list() {
        return jobAndTriggerMapper.getJobAndTrigger();
    }

    @Override
    public boolean saveCommonJob(String jobName, String cronExpression) {
        try {
            JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.put("jobName", jobName);
            JobDetail jobDetail = JobBuilder.newJob(CommonJob.class).withIdentity(jobName, COMMON_JOB_GROUP).setJobData(jobDataMap).build();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(commonTriggerName(jobName), COMMON_TRIGGER_GROUP).withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
            scheduler.scheduleJob(jobDetail, trigger);
            logger.info("创建定时任务成功");
        } catch (SchedulerException e) {
            throw new RuntimeException("saveCommonJob error", e);
        }
        return true;
    }

    @Override
    public boolean updateCommonJob(String jobName, String cronExpression) {
        try {
            TriggerKey triggerKey = TriggerKey.triggerKey(commonTriggerName(jobName), COMMON_TRIGGER_GROUP);
            CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            cronTrigger = cronTrigger.getTriggerBuilder().withSchedule(CronScheduleBuilder.cronSchedule(cronExpression)).build();
            scheduler.rescheduleJob(triggerKey, cronTrigger);
        } catch (SchedulerException e) {
            throw new RuntimeException("updateCommonJob error", e);
        }
        return true;
    }

    @Override
    public boolean deleteCommonJob(String jobName) {
        try {
            // TODO 测试验证Trigger是否删除
            scheduler.pauseTrigger(TriggerKey.triggerKey(commonTriggerName(jobName), COMMON_TRIGGER_GROUP));
            scheduler.unscheduleJob(TriggerKey.triggerKey(commonTriggerName(jobName), COMMON_TRIGGER_GROUP));
            scheduler.deleteJob(JobKey.jobKey(jobName, COMMON_JOB_GROUP));
        } catch (SchedulerException e) {
            throw new RuntimeException("deleteCommonJob error", e);
        }
        return true;
    }

    @Override
    public boolean pauseCommonJob(String jobName) {
        try {
            scheduler.pauseJob(JobKey.jobKey(jobName, COMMON_JOB_GROUP));
        } catch (SchedulerException e) {
            throw new RuntimeException("pauseCommonJob error", e);
        }
        return true;
    }

    @Override
    public boolean resumeCommonJob(String jobName) {
        try {
            scheduler.resumeJob(JobKey.jobKey(jobName, COMMON_JOB_GROUP));
        } catch (SchedulerException e) {
            throw new RuntimeException("pauseCommonJob error", e);
        }
        return true;
    }
}
