package org.peterkovalets.lucky_tweet_api.service;

import org.peterkovalets.lucky_tweet_api.common.enums.StorageBucket;
import org.peterkovalets.lucky_tweet_api.common.exceptions.StorageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class S3StorageService implements StorageService {

    private final S3Client s3Client;

    public S3StorageService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public String store(MultipartFile file, StorageBucket bucket) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file.");
        }

        String fileName = generateUniqueFileName(file);

        try (InputStream inputStream = file.getInputStream()) {
            uploadToS3(bucket.getBucketName(), fileName, file.getContentType(),
                    inputStream, file.getSize());
            return fileName;
        } catch (IOException e) {
            throw new StorageException("Failed to store file.", e);
        }
    }

    private String generateUniqueFileName(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (originalName == null || !originalName.contains(".")) {
            throw new StorageException("Invalid filename or missing extension.");
        }

        String extension = originalName.substring(originalName.lastIndexOf(".") + 1);
        return UUID.randomUUID() + "." + extension;
    }

    private void uploadToS3(String bucket,String key, String contentType,
                            InputStream inputStream, long size) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .contentType(contentType)
                .build();

        s3Client.putObject(putObjectRequest,
                RequestBody.fromInputStream(inputStream, size));
    }
}
