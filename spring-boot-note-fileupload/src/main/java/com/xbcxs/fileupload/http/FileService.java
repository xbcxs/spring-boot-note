package com.xbcxs.fileupload.http;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Xiao
 */
public interface FileService {

    /**
     * 上传文件
     *
     * @param files   文件
     * @param request request
     *                * @return
     * @throws IOException
     */
    List<String> uploads(MultipartFile[] files, HttpServletRequest request) throws IOException;

    /**
     * 下载文件
     *
     * @param id       文件ID
     * @param request  request
     * @param response response
     * @throws IOException
     */
    void load(String id, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
