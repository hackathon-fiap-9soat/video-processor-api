package br.com.fiap.hackathon.adapter.controller;

import br.com.fiap.hackathon.domain.service.VideoValidationService;
import br.com.fiap.hackathon.usecase.UploadVideoUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UploadVideoController {

    public final VideoValidationService videoValidationService;
    public final UploadVideoUseCase uploadVideoUseCase;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(@RequestPart("videoFile") MultipartFile videoFile, String email) {
        String originalName = videoFile.getOriginalFilename();
        if (originalName == null || !videoValidationService.isValidVideoFile(originalName)) {
            return ResponseEntity.badRequest().body("Formato de arquivo inválido. Use: mp4, avi, mov, mkv");
        }

        try {
            String id = UUID.randomUUID().toString();
            Path tempFile = Files.createTempFile(id, ".mp4");
            videoFile.transferTo(tempFile.toFile());
            uploadVideoUseCase.uploadVideo(id, tempFile, email);
            Files.deleteIfExists(tempFile);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro no upload do video" + e.getMessage());
        }
    }

}
