package com.verby.restapi.common.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StaticStorage {
    /**
     * Storage에 정적 파일을 업로드하는 메소드
     * @param multipartFile 업로드 대상 파일
     * @param dirName 파일을 저장할 경로(Path)
     * @return  저장된 파일의 경로
     */
    public String upload(MultipartFile multipartFile, String dirName);
}
