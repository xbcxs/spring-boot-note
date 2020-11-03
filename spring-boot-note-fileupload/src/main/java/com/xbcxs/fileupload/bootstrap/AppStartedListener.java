package com.xbcxs.fileupload.bootstrap;

import com.xbcxs.fileupload.config.FileServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * 应用启动事件监听
 *
 * @author Xiao
 */
@Order(100)
@Component
public class AppStartedListener implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppStartedListener.class);

    @Autowired
    FileServerConfig fileServerConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Your application started with option names : {}", args.getOptionNames());
        initFileServerDir(args);
    }

    /**
     * 初始化问价服务存储目录
     *
     * @param args
     * @throws IOException
     */
    private boolean initFileServerDir(ApplicationArguments args) {
        logger.info("初始化文件服务存储目录{}，请确保目录具备操作权限！", fileServerConfig.getStorage());
        File storage = new File(fileServerConfig.getStorage());
        File storageTemp = new File(fileServerConfig.getStorageTemp());
        boolean flag = !storage.exists() ? storage.mkdirs() : true;
        flag &= !storageTemp.exists() ? storageTemp.mkdirs() : true;
        return flag;
    }
}
