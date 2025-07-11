package br.com.fiap.hackathon.infrastructure.aws.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class SimpleStorageServiceTest {

    @Mock
    private S3Client s3Client;

    @InjectMocks
    private SimpleStorageService simpleStorageService;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(simpleStorageService, "bucketName", "test-bucket");
    }

    @Test
    @DisplayName("Deve fazer upload de vídeo com sucesso")
    void deveFazerUploadComSucesso() throws IOException {
        Path tempFile = Files.createTempFile("video", ".mp4");
        Files.write(tempFile, "dummy-content".getBytes());

        simpleStorageService.upload("video123", tempFile);

        verify(s3Client).putObject(argThat((PutObjectRequest req) ->
                req.bucket().equals("test-bucket") &&
                        req.key().equals("video123") &&
                        req.contentType().equals("video/mp4")
        ), any(RequestBody.class));

        Files.deleteIfExists(tempFile);
    }

    @Test
    @DisplayName("Deve lançar exceção ao falhar leitura do arquivo")
    void deveLancarErroAoFalharLeituraArquivo() {
        Path invalidPath = Path.of("/path/invalido/video.mp4");

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                simpleStorageService.upload("video123", invalidPath)
        );

        assertEquals("Erro ao fazer upload do vídeo", exception.getMessage());
    }
}