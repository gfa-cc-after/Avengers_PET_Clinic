package com.avangers.backendapi.config;

import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.services.s3.S3Client;

public class S3Config {
    @Bean
    public S3Client s3Client(){
        return S3Client.builder().build();
    }
}
