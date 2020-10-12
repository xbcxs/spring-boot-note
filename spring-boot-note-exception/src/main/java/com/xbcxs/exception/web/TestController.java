package com.xbcxs.exception.web;

import com.xbcxs.exception.common.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制层异常测试
 * @author Xiao
 */
@RestController
public class TestController {

    @RequestMapping("response")
    public HttpResult testRequest() {
        return HttpResult.response(HttpResult.ERROR_CODE, "ERROR", 5 / 0);
    }
}
