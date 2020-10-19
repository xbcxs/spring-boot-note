package com.xbcxs.common.patch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * ClassPath资源访问
 *
 * @author X
 */
public class ClassPathResourceUtils {

    private static final Logger logger = LoggerFactory.getLogger(ClassPathResourceUtils.class);

    /**
     * 测试
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        logger.info(getClasspath());
        InputStream is = getClasspathStream("test" + File.separator + "test.md");
        // InputStream is = getResourceAsStream("test" + File.separator + "test.md");
        byte[] bytes = new byte[1024];
        is.read(bytes);
        logger.info("bytes：{}", new String(bytes));
    }

    /**
     * 读取classes的相对路径
     * 可用于windows和Linux
     * 不可用于jar工程
     *
     * eg: C:\workspace\spring-boot-note\spring-boot-note-common\target\classes
     *
     * @return
     */
    public static String getClasspath() {
        try {
            return new org.springframework.core.io.ClassPathResource("").getFile().getPath();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 得到class目录流
     * 可用于windows和Linux
     * 可用jar,bin,webroot工程环境
     *
     * @return
     */
    public static InputStream getClasspathStream() {
        try {
            return new ClassPathResource("").getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * 根据classes下相对路径得到文件流
     * 可用于windows和Linux
     * 可用jar,bin,webroot工程环境
     *
     * eg: C:\workspace\spring-boot-note\spring-boot-note-common\target\classes\{relativeFilePath}
     *
     * @return
     */
    public static InputStream getClasspathStream(String relativeFilePath) {
        try {
            return new ClassPathResource(relativeFilePath).getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(relativeFilePath + "not found ！");
        }
    }

    /**
     * 根据classes下相对路径得到文件流
     * 可用于windows和Linux
     * [待验证]jar,bin,webroot工程环境
     *
     * eg: C:\workspace\spring-boot-note\spring-boot-note-common\target\classes\{relativeFilePath}
     *
     * @return
     */
    public static InputStream getResourceAsStream(String relativeFilePath) {
        return ClassPathResourceUtils.class.getClassLoader().getResourceAsStream(relativeFilePath);
    }

}
