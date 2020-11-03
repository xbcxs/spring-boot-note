package com.xbcxs.fileupload.config;

import com.xbcxs.fileupload.common.PathUtils;

import java.io.File;

/**
 * @author Xiao
 */
public class FileServerConfig {

    private String storage = PathUtils.getDeployParentPath() + File.separator + "FileServer" + File.separator + "storage";
    private String storageTemp = PathUtils.getDeployParentPath() + File.separator + "FileServer" + File.separator + "temp";
    private String nameEncryptionSign = "_";
    private boolean encryption = false;

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getStorageTemp() {
        return storageTemp;
    }

    public void setStorageTemp(String storageTemp) {
        this.storageTemp = storageTemp;
    }

    public String getNameEncryptionSign() {
        return nameEncryptionSign;
    }

    public void setNameEncryptionSign(String nameEncryptionSign) {
        this.nameEncryptionSign = nameEncryptionSign;
    }

    public boolean isEncryption() {
        return encryption;
    }

    public void setEncryption(boolean encryption) {
        this.encryption = encryption;
    }
}
