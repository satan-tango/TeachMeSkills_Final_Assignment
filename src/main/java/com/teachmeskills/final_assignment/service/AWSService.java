package com.teachmeskills.final_assignment.service;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;


import java.io.File;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class AWSService {

    public static void pushDataToAWS(File filepath) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        String accessKey = resourceBundle.getString("AWS.accessKey");
        String secretKey = resourceBundle.getString("AWS.secretKey");
        String bucketName = resourceBundle.getString("AWS.bucketName");
        String regionName = resourceBundle.getString("AWS.regionName");

        String key = filepath.getName();

        AwsCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

        S3Client s3Client = S3Client
                .builder()
                .region(Region.of(regionName))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .build();

        PutObjectResponse response = s3Client.putObject(request, Paths.get(filepath.getAbsolutePath()));
    }
}
