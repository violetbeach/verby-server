package com.verby.restapi.common.storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StaticStorage {

    public String upload(MultipartFile multipartFile, String dirName) throws IOException;

}
