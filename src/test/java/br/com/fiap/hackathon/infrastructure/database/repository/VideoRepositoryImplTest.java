package br.com.fiap.hackathon.infrastructure.database.repository;

import br.com.fiap.hackathon.domain.entity.VideoFrameEntity;
import br.com.fiap.hackathon.domain.repository.VideoFrameRepository;
import br.com.fiap.hackathon.domain.service.enums.ProcessorStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class VideoRepositoryImplTest {

    @Mock
    private VideoFrameRepository repository;

    @InjectMocks
    private VideoRepositoryImpl videoRepositoryImpl;

    @Test
    @DisplayName("Deve buscar vídeos por email")
    void deveBuscarPorEmail() {
        String email = "user@example.com";
        List<VideoFrameEntity> expected = List.of(new VideoFrameEntity());

        when(repository.findByEmail(email)).thenReturn(expected);

        List<VideoFrameEntity> result = videoRepositoryImpl.findByEmail(email);

        assertEquals(expected, result);
        verify(repository).findByEmail(email);
    }

    @Test
    @DisplayName("Deve buscar vídeos por status")
    void deveBuscarPorStatus() {
        ProcessorStatus status = ProcessorStatus.PROCESSING;
        List<VideoFrameEntity> expected = List.of(new VideoFrameEntity());

        when(repository.findByStatus(status)).thenReturn(expected);

        List<VideoFrameEntity> result = videoRepositoryImpl.findByStatus(status);

        assertEquals(expected, result);
        verify(repository).findByStatus(status);
    }
}
