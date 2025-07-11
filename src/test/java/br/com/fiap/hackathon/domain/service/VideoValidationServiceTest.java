package br.com.fiap.hackathon.domain.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VideoValidationServiceTest {

    private final VideoValidationService service = new VideoValidationService();

    @Test
    @DisplayName("Deve retornar true para extensões válidas")
    void deveRetornarTrueParaExtensoesValidas() {
        assertTrue(service.isValidVideoFile("video.mp4"));
        assertTrue(service.isValidVideoFile("movie.avi"));
        assertTrue(service.isValidVideoFile("clip.mov"));
        assertTrue(service.isValidVideoFile("film.mkv"));
        assertTrue(service.isValidVideoFile("recording.wmv"));
        assertTrue(service.isValidVideoFile("stream.flv"));
        assertTrue(service.isValidVideoFile("video.webm"));
    }

    @Test
    @DisplayName("Deve retornar false para extensões inválidas")
    void deveRetornarFalseParaExtensoesInvalidas() {
        assertFalse(service.isValidVideoFile("image.jpg"));
        assertFalse(service.isValidVideoFile("document.pdf"));
        assertFalse(service.isValidVideoFile("video"));
        assertFalse(service.isValidVideoFile("videos."));
        assertFalse(service.isValidVideoFile("video.mp3"));
    }
}
