package br.com.fiap.hackathon.usecase;

import br.com.fiap.hackathon.infrastructure.aws.service.SimpleQueueService;
import br.com.fiap.hackathon.infrastructure.aws.service.SimpleStorageService;
import br.com.fiap.hackathon.infrastructure.aws.service.dto.VideoMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadVideoUseCase {

    private final SimpleStorageService simpleStorageService;
    private final SimpleQueueService simpleQueueService;

    public void uploadVideo(String videoId, Path videoPath, String email) {
        try {
            simpleStorageService.upload(videoId, videoPath);
            log.info("Upload realizado com sucesso: {}", videoId);

            VideoMessageDTO request = new VideoMessageDTO(email, videoId);
            simpleQueueService.sendMessage(request);
        } catch (Exception e) {
            log.error("Erro ao fazer envio do video: "+ e.getMessage(), e);
            throw new RuntimeException("Erro ao fazer envio do video: "+e.getMessage(), e);
        }
    }

}
