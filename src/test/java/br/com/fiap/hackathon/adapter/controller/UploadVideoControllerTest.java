package br.com.fiap.hackathon.adapter.controller;

import br.com.fiap.hackathon.domain.service.VideoValidationService;
import br.com.fiap.hackathon.usecase.UploadVideoUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UploadVideoController.class)
@Import(UploadVideoControllerTest.MockConfig.class)
class UploadVideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private VideoValidationService videoValidationService;

    @Autowired
    private UploadVideoUseCase uploadVideoUseCase;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public VideoValidationService videoValidationService() {
            return mock(VideoValidationService.class);
        }

        @Bean
        public UploadVideoUseCase uploadVideoUseCase() {
            return mock(UploadVideoUseCase.class);
        }
    }

    @Test
    @DisplayName("Deve realizar upload com sucesso")
    void deveRealizarUploadComSucesso() throws Exception {
        MockMultipartFile videoFile = new MockMultipartFile(
                "videoFile", "video.mp4", MediaType.MULTIPART_FORM_DATA_VALUE, "dummy-content".getBytes()
        );

        when(videoValidationService.isValidVideoFile("video.mp4")).thenReturn(true);

        mockMvc.perform(multipart("/upload")
                        .file(videoFile)
                        .param("email", "user@example.com"))
                .andExpect(status().isOk());

        verify(uploadVideoUseCase, times(1)).uploadVideo(any(), any(), eq("user@example.com"));
    }

    @Test
    @DisplayName("Deve retornar erro ao enviar vídeo com formato inválido")
    void deveRetornarErroFormatoInvalido() throws Exception {
        MockMultipartFile videoFile = new MockMultipartFile(
                "videoFile", "video.txt", MediaType.MULTIPART_FORM_DATA_VALUE, "invalid-content".getBytes()
        );

        when(videoValidationService.isValidVideoFile("video.txt")).thenReturn(false);

        mockMvc.perform(multipart("/upload")
                        .file(videoFile)
                        .param("email", "user@example.com"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Formato de arquivo inválido. Use: mp4, avi, mov, mkv"));
    }
}