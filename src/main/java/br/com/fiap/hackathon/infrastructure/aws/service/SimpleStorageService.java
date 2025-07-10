package br.com.fiap.hackathon.infrastructure.aws.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleStorageService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    public String bucketName;

    public void upload(String videoId, Path videoPath) throws IOException {
        try {
            byte[] bytes = Files.readAllBytes(videoPath);

            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(videoId)
                    .contentType("video/mp4")
                    .build();

            s3Client.putObject(request, RequestBody.fromBytes(bytes));
        } catch (IOException e) {
            log.error("Erro ao fazer upload do vídeo: {}", e.getMessage());
            throw new RuntimeException("Erro ao fazer upload do vídeo", e);
        }
    }

}
