package com.xbcxs.fileupload.common;

import java.util.UUID;

/**
 * uuid
 *
 * @author Xiao
 */
public class UUIDGenerator {

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
