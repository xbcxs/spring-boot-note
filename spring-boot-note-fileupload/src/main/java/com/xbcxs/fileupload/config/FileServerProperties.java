package com.xbcxs.fileupload.config;

import com.xbcxs.fileupload.common.PathUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author Xiao
 */
@Configuration
@ConfigurationProperties(prefix = "file.server")
public class FileServerProperties {

    private String dir;
    private String storage;
    private String storageTemp;
    private String nameEncryptionSign;
    private boolean encryption;

    public FileServerProperties() {
        this.nameEncryptionSign = "_";
        this.dir = PathUtils.getDeployParentPath() + File.separator + "FileServer";
        this.storage = dir + File.separator + "storage";
        this.storageTemp = dir + File.separator + "temp";
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
        this.storage = dir + File.separator + "storage";
        this.storageTemp = dir + File.separator + "temp";
    }

    public String getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(String storageTemp) {
        this.storageTemp = storageTemp;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public boolean isEncryption() {
        return encryption;
    }

    public void setEncryption(boolean encryption) {
        this.encryption = encryption;
    }

    public String getNameEncryptionSign() {
        return nameEncryptionSign;
    }

    public void setNameEncryptionSign(String nameEncryptionSign) {
        this.nameEncryptionSign = nameEncryptionSign;
    }

    @Override
    public String toString() {
        return "FileServerProperties{" +
                "dir='" + dir + '\'' +
                ", storage='" + storage + '\'' +
                ", storageTemp='" + storageTemp + '\'' +
                ", nameEncryptionSign='" + nameEncryptionSign + '\'' +
                ", encryption=" + encryption +
                '}';
    }
}
