package com.xbcxs.exception.response;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 对/error进行细分处理
 *
 * @author Xiao
 */
@Controller
public class ResponseErrorHandler implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        //获取statusCode:401,404,500
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == 500) {
            return "/WEB-INF/error/500";
        } else if (statusCode == 404) {
            return "/WEB-INF/error/404";
        } else if (statusCode == 403) {
            return "/WEB-INF/error/403";
        } else {
            return "/WEB-INF/error/500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
