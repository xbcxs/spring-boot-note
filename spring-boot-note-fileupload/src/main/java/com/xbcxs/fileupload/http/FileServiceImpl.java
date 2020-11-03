package com.xbcxs.fileupload.http;

import com.xbcxs.fileupload.common.CipherKey;
import com.xbcxs.fileupload.common.UUIDGenerator;
import com.xbcxs.fileupload.config.FileServerProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.CipherInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件服务层实现
 *
 * @author Xiao
 */
@Service
public class FileServiceImpl implements FileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    FileServerProperties fsp;

    @Override
    public List<String> uploads(MultipartFile[] files, HttpServletRequest request) throws IOException {
        List newFileNames = new ArrayList<String>();
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
            logger.info("上传的文件名为：" + fileName + "，大小为：" + file.getSize() + "B");
            File dest;
            InputStream is;
            String newFileName = UUIDGenerator.getUUID();
            if (fsp.isEncryption()) {
                newFileName = fsp.getNameEncryptionSign() + newFileName;
                is = new CipherInputStream(file.getInputStream(), CipherKey.getEncryptCipher());
            } else {
                is = file.getInputStream();
            }
            dest = new File(fsp.getStorage() + File.separator + newFileName);
            FileCopyUtils.copy(new BufferedInputStream(is), new BufferedOutputStream(Files.newOutputStream(dest.toPath())));
            newFileNames.add(newFileName);
        }
        return newFileNames;
    }

    @Override
    public void load(String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File(fsp.getStorage() + File.separator + id);
        InputStream is;
        if (fsp.getNameEncryptionSign().equals(file.getName().substring(0, 1))) {
            is = new BufferedInputStream(new CipherInputStream(new FileInputStream(file), CipherKey.getDecryptCipher()));
        } else {
            is = new FileInputStream(file);
        }
        OutputStream out = new BufferedOutputStream(response.getOutputStream());
        byte[] buffer = new byte[1024];
        int counter;
        while ((counter = is.read(buffer)) >= 0) {
            out.write(buffer, 0, counter);
        }
        // 从DB取出名字
        String fileName = "我爱中国.txt";
        // 浏览器设置
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            //IE浏览器处理
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器的处理：ISO-8859-1 为HTTP header 协议要求
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        // 设置文件头：最后一个参数是设置下载文件名
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
        // 设置文件ContentType类型，这样设置，会自动判断下载文件类型
        // response.setContentType("application/octet-stream;charset=utf-8");
        is.close();
        out.close();
    }
}
