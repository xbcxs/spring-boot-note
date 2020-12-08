package com.xbcxs.exception.web;

import com.xbcxs.exception.exception.NotFoundException;
import com.xbcxs.exception.exception.UncheckedException;
import com.xbcxs.exception.model.ResponseResult;
import com.xbcxs.exception.model.ResponseResultWrap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制层异常测试
 *
 * @author Xiao
 */
@RestController
public class TestController {

    /**
     * 测试未被ControllerExceptionHandler捕获的异常
     *
     * @return
     */
    @RequestMapping("testException")
    public Object testException() {
        return 5 / 0;
    }

    /**
     * 测试被ControllerExceptionHandler捕获的异常
     *
     * @return
     */
    @RequestMapping("testUncheckException")
    public Object testUncheckException() {
        throw new UncheckedException("自定义运行时异常!");
    }

    /**
     * 测试被ControllerExceptionHandler捕获的异常
     *
     * @return
     */
    @RequestMapping("testNotFoundException")
    public Object testNotFoundException() {
        throw new NotFoundException();
    }

    /**
     * 测试被ControllerExceptionHandler根据@ResponseResultWrap自动包装处理结果
     *
     * @return
     */
    @ResponseResultWrap
    @RequestMapping("testString")
    public String testString() {
        return "happy";
    }

    /**
     * 测试ResponseResult返回数据
     *
     * @return
     */
    @RequestMapping("testSuccess")
    public ResponseResult testSuccess() {
        return ResponseResult.success();
    }

    /**
     * 测试ResponseResult返回数据
     *
     * @return
     */
    @RequestMapping("testSuccess2")
    public ResponseResult testSuccess2() {
        Map map = new HashMap(1);
        map.put("name", "Jack Chen");
        return ResponseResult.success(map);
    }

    /**
     * 测试ResponseResult返回数据
     *
     * @return
     */
    @RequestMapping("testFailure")
    public ResponseResult testFailure() {
        return ResponseResult.failure();
    }

    /**
     * 测试ResponseResult返回数据
     *
     * @return
     */
    @RequestMapping("testFailure2")
    public ResponseResult testFailure2() {
        return ResponseResult.failure("自定义错误描述信息");
    }
}
