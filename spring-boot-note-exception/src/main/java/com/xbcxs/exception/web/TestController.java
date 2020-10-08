package com.xbcxs.exception.web;

import com.xbcxs.exception.common.HttpResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiao
 * @date 2020/9/28
 */
@RestController
public class TestController {

    @RequestMapping("response")
    public HttpResult testRequest() {
        return HttpResult.response(5 / 0);
    }
}
