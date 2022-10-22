package com.verby.restapi.common.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StaticStorage {
    public String upload(MultipartFile multipartFile, String dirName);
}
