package com.xbcxs.exception.web;

import com.xbcxs.exception.exception.UncheckedException;
import com.xbcxs.exception.response.ResponseResult;
import com.xbcxs.exception.response.Result;
import com.xbcxs.exception.response.ResultCode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层异常测试
 *
 * @author Xiao
 */
@RestController
public class TestController {

    /**
     * 当前场景异常进入ControllerExceptionHandler，的exception处理，然后交由ControllerErrorHandler：/error处理
     *
     * @return
     */
    @RequestMapping("testException")
    public Object testException() {
        return 5 / 0;
    }

    @RequestMapping("testUncheckException")
    public Object testUncheckException() {
        throw new UncheckedException("自定义运行时异常!");
    }

    @ResponseResult
    @RequestMapping("testString")
    public String testString() {
        return "happy";
    }

    @RequestMapping("testResult")
    public Result testResult() {
        return Result.error(ResultCode.SYSTEM_EXECUTE_ERROR);
    }
}
