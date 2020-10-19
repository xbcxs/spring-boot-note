package com.xbcxs.quartz.scheduler.mapper;

import com.xbcxs.quartz.scheduler.QuartzDTO;
import org.apache.ibatis.annotations.Mapper;

/**
 * JobAndTriggerMapper
 *
 * @author Xiao
 */
@Mapper
public interface QuartzMapper {

    /**
     * 查询JobAndTrigger
     *
     * @return
     */
    QuartzDTO getJobAndTrigger();
}
