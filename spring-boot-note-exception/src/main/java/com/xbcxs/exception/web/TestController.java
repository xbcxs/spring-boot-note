package com.xbcxs.exception.web;

import com.xbcxs.exception.exception.NotFoundException;
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
    @RequestMapping("testError")
    public ResponseResult testError() {
        return ResponseResult.error();
    }

    /**
     * 测试ResponseResult返回数据
     *
     * @return
     */
    @RequestMapping("testError2")
    public ResponseResult testError2() {
        return ResponseResult.error("自定义错误描述信息");
    }

    /**
     * 测试被统一拦截器捕获，且进行异常转CODE处理，返回异常对应code
     *
     * @return
     */
    @RequestMapping("testNotFoundException")
    public Object testNotFoundException() {
        throw new NotFoundException();
    }

    /**
     * 测试被统一拦截器捕获，但未进行细分类处理的异常，统一返回code:500
     *
     * @return
     */
    @RequestMapping("testException")
    public Object testException() {
        return 5 / 0;
    }

    /**
     * 测试被统一拦截器捕获，但未进行细分类处理的异常，统一返回code:500
     *
     * @return
     */
    @RequestMapping("testRuntimeException")
    public Object testRuntimeException() {
        throw new RuntimeException("运行时异常!");
    }


    /**
     * 测试被ControllerExceptionHandler根据@ResponseResultWrap自动包装处理结果
     * ps: 需要打开ResponseResultHandler.java注释掉的两个 @Override方法
     *
     * @return
     */
    @ResponseResultWrap
    @RequestMapping("testString")
    public String testString() {
        return "happy";
    }

}
