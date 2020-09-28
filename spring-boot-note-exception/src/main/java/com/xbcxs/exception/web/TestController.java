package com.xbcxs.exception.web;

import com.xbcxs.exception.custom.HttpResult;
import com.xbcxs.exception.exception.UncheckedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiao
 * @date 2020/9/28
 */
@RestController
public class TestController {

    @RequestMapping("testRequest")
    public HttpResult testRequest() {
        System.out.println(5 / 0);
        return HttpResult.success();
    }
}
