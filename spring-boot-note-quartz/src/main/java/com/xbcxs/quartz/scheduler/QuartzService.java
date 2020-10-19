package com.xbcxs.quartz.scheduler;

/**
 * QuartzService
 *
 * @author Xiao
 */
public interface QuartzService {

    /**
     * 查询JobAndTrigger
     *
     * @return
     */
    QuartzDTO list();

    /**
     * 新建通用任务
     *
     * @param jobName
     * @param cronExpression
     * @return
     */
    boolean saveCommonJob(String jobName, String cronExpression);

    /**
     * 修改通用任务
     * 实际修改的是该任务一对一对应的触发器的Corn
     *
     * @param jobName
     * @param cronExpression
     * @return
     */
    boolean updateCommonJob(String jobName, String cronExpression);


    boolean deleteCommonJob(String jobName);

    boolean pauseCommonJob(String jobName);

    boolean resumeCommonJob(String jobName);
}
