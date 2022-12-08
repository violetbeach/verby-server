package com.verby.restapi.song.infra;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.verby.restapi.common.storage.dto.Domain;
import com.verby.restapi.common.storage.dto.Resource;
import com.verby.restapi.common.storage.exception.ResourceTypeNotMatchException;
import com.verby.restapi.song.command.application.SongStorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.UUID;

import static com.verby.restapi.common.storage.StorageUtils.generatePreSignedUrlExpiration;

@Slf4j
@Component
@RequiredArgsConstructor
public class SongS3StorageService implements SongStorageService {
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    @Value("${static.paths.song.image}")
    private String imageBasePath;

    public String getPreSignedUrl(Resource resource) {
        String path = getResourcePath(resource) + "/" + UUID.randomUUID();
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePreSignedUrlRequest(bucket, path);
        URL url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(String bucket, String path) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, path)
                        .withMethod(HttpMethod.PUT)
                        .withExpiration(generatePreSignedUrlExpiration());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString());
        return generatePresignedUrlRequest;
    }

    private String getResourcePath(Resource resource) {
        if (resource == Resource.IMAGE) {
            return imageBasePath;
        }
        log.warn("Not allow resource Type (Song -> {}).", resource);
        throw new ResourceTypeNotMatchException(Domain.SONG, resource);
    }

}
