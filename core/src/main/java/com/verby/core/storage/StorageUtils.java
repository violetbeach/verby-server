package com.verby.core.storage;

import lombok.experimental.UtilityClass;

import java.util.Date;

@UtilityClass
public class StorageUtils {

    public static Date generatePreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        expTimeMillis += 1000 * 60 * 3;
        expiration.setTime(expTimeMillis);
        return expiration;
    }

}
