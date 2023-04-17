package com.verby.core.cover.infra;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.verby.core.cover.command.application.CoverStoragePathProperties;
import com.verby.core.cover.command.application.CoverStorageService;
import com.verby.core.storage.StorageUtils;
import com.verby.core.storage.dto.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
class CoverS3StorageService extends CoverStorageService {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public CoverS3StorageService(CoverStoragePathProperties storagePathProperties, AmazonS3Client amazonS3Client) {
        super(storagePathProperties);
        this.amazonS3Client = amazonS3Client;
    }

    public String getPreSignedUrl(Resource resource, String fileName) {
        Path path = Paths.get(getResourcePath(resource), fileName);
        GeneratePresignedUrlRequest generatePresignedUrlRequest = getGeneratePreSignedUrlRequest(bucket, path.toString());
        URL url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();
    }

    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(String bucket, String path) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucket, path)
                .withMethod(HttpMethod.POST)
                .withExpiration(StorageUtils.generatePreSignedUrlExpiration());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString());
        return generatePresignedUrlRequest;
    }

}
