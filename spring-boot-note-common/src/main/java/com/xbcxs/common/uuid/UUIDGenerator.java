package com.xbcxs.common.uuid;

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
