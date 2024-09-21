package com.avangers.backendapi.services;

import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;

public class S3Service {

    private final S3Service s3Service;

    public S3Service(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    public void uploadFile(String bucketName, String keyname, File file) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(keyname)
                .build();

    }
}
