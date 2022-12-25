package verby.apiserver.cover.infra;

import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import verby.apiserver.common.storage.dto.Resource;
import verby.apiserver.cover.command.application.CoverStoragePathProperties;
import verby.apiserver.cover.command.application.CoverStorageService;

import java.net.URL;
import java.util.UUID;

import static verby.apiserver.common.storage.StorageUtils.generatePreSignedUrlExpiration;

@Component
public class CoverS3StorageService extends CoverStorageService {
    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public CoverS3StorageService(CoverStoragePathProperties storagePathProperties, AmazonS3Client amazonS3Client) {
        super(storagePathProperties);
        this.amazonS3Client = amazonS3Client;
    }

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

}
