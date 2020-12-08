package com.xbcxs.exception.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 复写
 * Whitelabel Error Page
 * This application has no explicit mapping for /error, so you are seeing this as a fallback.
 * <p>
 * Tue Dec 01 11:39:47 CST 2020
 * There was an unexpected error (type=Internal Server Error, status=500).
 * <p>
 * 对/error进行细分处理
 *
 * @author Xiao
 */
@Controller
public class ResponseErrorHandler implements ErrorController {

    private final String ERROR_PATH = "/error";

    /**
     * 需要引入模板JAR例如（thymeleaf）
     *
     * @param request
     * @return
     */
    @RequestMapping(value = ERROR_PATH, produces = {"text/html"})
    public String handleError(HttpServletRequest request, Throwable ex) {
        System.out.println(ex.getMessage());
        // 404、401、400、403、500
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (404 == code) {
            return "error/404";
        } else if (403 == code) {
            return "error/403";
        } else if (400 == code) {
            return "error/400";
        } else {
            return "error/500";
        }
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
