# Httpclient笔记

## 基于表单提交
```
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
```

## 基于Json提交
```
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
```
## Delete post扩展实现
```
public class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
    public static final String METHOD_NAME = "DELETE";

    public HttpDeleteWithBody() {
    }

    public HttpDeleteWithBody(URI uri) {
        this.setURI(uri);
    }

    public HttpDeleteWithBody(String uri) {
        this.setURI(URI.create(uri));
    }

    @Override
    public String getMethod() {
        return "DELETE";
    }
}

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
```

## 基本操作
参考com.example.httpclient.http包内容 