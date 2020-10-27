package com.example.httpclient.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * HttpClientResponse
 *
 * @author Xiao
 */
@RequestMapping("response")
@RestController
public class HttpClientResponse {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientResponse.class);

    @GetMapping("get")
    public void get(@RequestParam String name) {
        logger.info("get name:{}", name);
    }

    @PostMapping("post")
    public void post(@RequestBody Map data, @RequestParam String address) {
        logger.info("post name:{}, address:{}", data, address);
    }

    @PutMapping("put")
    public void put(@RequestBody Map data) {
        logger.info("put name:{}", data);
    }

    @DeleteMapping("delete")
    public void delete(@RequestBody Map data) {
        logger.info("delete name:{}", data);
    }
}
