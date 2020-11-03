package com.xbcxs.fileupload.common;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 路径计算工具类
 *
 * @author Xiao
 */
public class PathUtils {

    /**
     * 获取应用部署父路径
     *
     * @return
     */
    public static String getDeployParentPath() {
        String path;
        try {
            path = new ClassPathResource("").getFile().getParentFile().getParentFile().getParent();
        } catch (IOException e) {
            throw new RuntimeException("获取应用部署父路径失败！", e);
        }
        return path;
    }

}
