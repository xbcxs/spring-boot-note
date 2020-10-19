package com.xbcxs.quartz.scheduler;

import org.quartz.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * SchedulerController
 *
 * @author Xiao
 */
@RestController
public class QuartzController {

    @Autowired
    Scheduler scheduler;

    @Autowired
    QuartzService quartzService;

    private static final Logger logger = LoggerFactory.getLogger(QuartzController.class);

    @RequestMapping("list")
    public Object list() {
        return quartzService.list();
    }

    @RequestMapping(value = "save")
    public Object save(@RequestParam String jobName) {
        logger.info("save...");
        return quartzService.saveCommonJob(jobName, "0/10 * * * * ? ");
    }

    @RequestMapping(value = "update")
    public Object update(@RequestParam String jobName, @RequestParam String second) {
        logger.info("update...");
        return quartzService.updateCommonJob(jobName, "0/" + second + " * * * * ? ");
    }

    @RequestMapping(value = "delete")
    public Object delete(@RequestParam String jobName) {
        logger.info("delete...");
        return quartzService.deleteCommonJob(jobName);
    }

    @RequestMapping(value = "pause")
    public Object pause(@RequestParam String jobName) {
        logger.info("pause...");
        return quartzService.pauseCommonJob(jobName);
    }

    @RequestMapping(value = "resume")
    public Object resume(@RequestParam String jobName) {
        logger.info("resume...");
        return quartzService.resumeCommonJob(jobName);
    }
}