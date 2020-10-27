package com.example.httpclient.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Httpclient例子
 *
 * @author Xiao
 */
@RequestMapping("request")
@RestController
public class HttpClientRequest {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientRequest.class);

    @RequestMapping("get")
    public void get() {
        try {
            String result = HttpClientUtils.get("http://localhost:8080/httpclient/response/get?name=丘比特");
            logger.info("get result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("post")
    public void post() {
        try {
            // 行参数
            String lineParam = "中央大街";
            // 体参数
            Map<String, String> bodyParams = new HashMap(1);
            bodyParams.put("name", "丘比特");
            String result = HttpClientUtils.post("http://localhost:8080/httpclient/response/post?address=" + lineParam, bodyParams);
            logger.info("get result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("put")
    public void put() {
        try {
            Map<String, String> params = new HashMap(1);
            params.put("name", "丘比特");
            String result = HttpClientUtils.put("http://localhost:8080/httpclient/response/put", params);
            logger.info("get result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("delete")
    public void delete() {
        try {
            Map<String, String> params = new HashMap(1);
            params.put("name", "丘比特");
            String result = HttpClientUtils.delete("http://localhost:8080/httpclient/response/delete", params);
            logger.info("get result:{}", result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
