package com.verby.restapi.cover.command.application;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.verby.restapi.common.storage.dto.Domain;
import com.verby.restapi.common.storage.dto.Resource;
import com.verby.restapi.common.storage.exception.ResourceTypeNotMatchException;
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
public class CoverS3StorageService implements CoverStorageService {

    private final AmazonS3Client amazonS3Client;
    private final CoverStoragePathProperties storagePathProperties;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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
        switch (resource) {
            case FULL_VIDEO -> {
                return storagePathProperties.getVideo();
            }
            case HIGHLIGHT -> {
                return storagePathProperties.getHighlight();
            }
            case IMAGE -> {
                return storagePathProperties.getImage();
            }
            default -> {
                log.warn("Not allow resource Type (Cover -> {}).", resource);
                throw new ResourceTypeNotMatchException(Domain.COVER, resource);
            }
        }
    }

}
