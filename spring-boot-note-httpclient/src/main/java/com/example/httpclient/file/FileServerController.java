package com.example.httpclient.file;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件服务模拟测试
 *
 * @author Xiao
 */
@RequestMapping("web")
@RestController
public class FileServerController {

    private static final Logger logger = LoggerFactory.getLogger(FileServerController.class);

    /**
     * 读取本地文件上传到
     *
     * @param response
     */
    @RequestMapping("server/load")
    public void load(HttpServletResponse response) {
        try (
                OutputStream output = response.getOutputStream();
                FileInputStream input = new FileInputStream("D:/adfadfa.log")
        ) {
            byte[] bytes = new byte[2048];
            int length = -1;
            while ((length = input.read(bytes)) != -1) {
                output.write(bytes, 0, length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从远程服务读取文件流，下载到磁盘目录
     */
    @RequestMapping("client/load")
    public void load() {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("http://localhost:8080/httpclient/web/server/load");
            HttpResponse httpResponse = httpClient.execute(httpGet);
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status < 200 || status >= 300) {
                // ... handle unsuccessful request
                logger.error("向服务器发起请求错误，错误码：{}", status);
            }
            HttpEntity entity = httpResponse.getEntity();
            InputStream ins = entity.getContent();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("D:/test_" + System.currentTimeMillis() + ".log"));
            byte[] bytes = new byte[2048];
            int length = -1;
            while ((length = ins.read(bytes)) != -1) {
                bos.write(bytes, 0, length);
            }
            // ... do something with response
        } catch (IOException e) {
            // ... handle IO exception
        }
    }

}
