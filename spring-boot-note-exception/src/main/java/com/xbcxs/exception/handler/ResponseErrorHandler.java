package com.xbcxs.exception.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResponseErrorHandler implements ErrorController {

    private final String ERROR_PATH = "/error";

    /**
     * <p>如果需要返回后端页面，则需要引入模板JAR例如（thymeleaf）
     * <p>如果需要返回数据，则需要使用@ResponseBody注解
     *
     * @param request
     * @return
     */
    @ResponseBody
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
