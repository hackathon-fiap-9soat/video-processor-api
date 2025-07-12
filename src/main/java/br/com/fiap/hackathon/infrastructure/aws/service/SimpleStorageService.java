package br.com.fiap.hackathon.infrastructure.aws.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleStorageService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    public String bucketName;

    public void upload(String videoId, byte[] bytes) throws IOException {
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(videoId)
                .contentType("video/mp4")
                .build();

        s3Client.putObject(request, RequestBody.fromBytes(bytes));
    }

}
