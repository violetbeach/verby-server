package com.verby.restapi.common.storage;

import java.util.Date;

public class StorageUtils {

    public static Date generatePreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 3;
        expiration.setTime(expTimeMillis);
        return expiration;
    }

}
