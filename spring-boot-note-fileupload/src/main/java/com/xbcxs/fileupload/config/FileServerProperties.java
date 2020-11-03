package com.xbcxs.fileupload.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Xiao
 */
@ConfigurationProperties(prefix = "file.server")
public class FileServerProperties {

    private String path;
    private boolean encryption;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isEncryption() {
        return encryption;
    }

    public void setEncryption(boolean encryption) {
        this.encryption = encryption;
    }
}
