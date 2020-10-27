package com.example.httpclient.http;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * HttpClient工具
 *
 * @author Xiao
 */
public class HttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static void main(String[] args) throws IOException {
        get("https://www.baidu.com/");
    }

    /**
     * 请求对象配置
     *
     * @return
     */
    private static RequestConfig requestConfig() {
        return RequestConfig.custom().setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000).build();
    }

    /**
     * GET请求
     * <p>
     * GET可发起行参数、头参数请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url) throws IOException {
        return get(url, null);
    }

    /**
     * GET请求
     * <p>
     * GET可发起行参数、头参数请求
     *
     * @param url
     * @param headers
     * @return
     * @throws IOException
     */
    public static String get(String url, Map<String, String> headers) throws IOException {
        String responseResult;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
            logger.info("Executing request get " + httpGet.getRequestLine());
            httpGet.setConfig(requestConfig());
            // 设置header
            if (headers != null) {
                for (Map.Entry<String, String> entry : headers.entrySet()) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }

            httpResponse = httpClient.execute(httpGet);

            int status = httpResponse.getStatusLine().getStatusCode();
            if (status != 200) {
                throw new IOException("Failure! Http request get status " + status);
            }
            HttpEntity entity = httpResponse.getEntity();
            responseResult = entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
            EntityUtils.consume(entity);
            return responseResult;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    /**
     * POST请求
     * <p>
     * POST可发起行参数、体参数、头参数请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String post(String url, Map<String, String> params) throws IOException {
        String responseResult;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            logger.info("Executing request[post] " + httpPost.getRequestLine());
            httpPost.setConfig(requestConfig());
            // 设置请求Content-Type
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            // 设置体参数
            httpPost.setEntity(new StringEntity(JSONObject.toJSONString(params), Consts.UTF_8));

            httpResponse = httpClient.execute(httpPost);

            int status = httpResponse.getStatusLine().getStatusCode();
            if (status != 200) {
                throw new IOException("Failure! Http request[post] status " + status);
            }
            HttpEntity entity = httpResponse.getEntity();
            responseResult = entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
            EntityUtils.consume(entity);
            return responseResult;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    /**
     * 用于Content type：application/x-www-form-urlencoded格式下post请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String postByform(String url, Map<String, String> params) throws IOException {
        String responseResult;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            logger.info("Executing request[post] " + httpPost.getRequestLine());
            httpPost.setConfig(requestConfig());

            // 设置传参
            if (params != null) {
                List<NameValuePair> parameters = new ArrayList();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    parameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                httpPost.setEntity(new UrlEncodedFormEntity(parameters, Consts.UTF_8));
            }

            httpResponse = httpClient.execute(httpPost);

            HttpEntity entity = httpResponse.getEntity();
            responseResult = entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
            EntityUtils.consume(entity);
            return responseResult;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    /**
     * PUT请求
     * <p>
     * PUT可发起行参数、体参数、头参数请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String put(String url, Map<String, String> params) throws IOException {
        String responseResult;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpPut httpPut = new HttpPut(url);
            logger.info("Executing request[put] " + httpPut.getRequestLine());
            httpPut.setConfig(requestConfig());

            // 设置请求Content-Type
            httpPut.setHeader("Content-Type", "application/json;charset=UTF-8");
            // 设置体参数
            httpPut.setEntity(new StringEntity(JSONObject.toJSONString(params), Consts.UTF_8));

            httpResponse = httpClient.execute(httpPut);

            int status = httpResponse.getStatusLine().getStatusCode();
            if (status != 200) {
                throw new IOException("Failure! Http request[put] status " + status);
            }
            HttpEntity entity = httpResponse.getEntity();
            responseResult = entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
            EntityUtils.consume(entity);
            return responseResult;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

    /**
     * DELETE请求
     * <p>
     * DELETE可发起行参数、体参数、头参数请求
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String delete(String url, Map<String, String> params) throws IOException {
        String responseResult;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            httpClient = HttpClients.createDefault();
            HttpDeleteWithBody httpDelete = new HttpDeleteWithBody(url);
            logger.info("Executing request[delete] " + httpDelete.getRequestLine());
            httpDelete.setConfig(requestConfig());

            // 设置请求Content-Type
            httpDelete.setHeader("Content-Type", "application/json;charset=UTF-8");
            // 设置体参数
            httpDelete.setEntity(new StringEntity(JSONObject.toJSONString(params), Consts.UTF_8));
            httpResponse = httpClient.execute(httpDelete);
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status != 200) {
                throw new IOException("Failure! Http request[delete] status " + status);
            }

            HttpEntity entity = httpResponse.getEntity();
            responseResult = entity != null ? EntityUtils.toString(entity, Consts.UTF_8) : null;
            EntityUtils.consume(entity);
            return responseResult;
        } finally {
            if (httpResponse != null) {
                httpResponse.close();
            }
            if (httpClient != null) {
                httpClient.close();
            }
        }
    }

}
