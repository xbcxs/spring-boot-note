package com.xbcxs.fileupload.http;

import com.xbcxs.fileupload.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 上传处理
 *
 * @author Xiao
 */
@RestController
public class FileServerController {

    @Autowired
    FileServiceImpl fileServiceImpl;

    @PostMapping("upload")
    public Object upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws IOException {
        return ObjectUtils.isEmpty(files) ? Result.failure("file is empty") : Result.success(fileServiceImpl.uploads(files, request));
    }


    @RequestMapping("load")
    public void load(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileServiceImpl.load(id, request, response);
    }

}
