package br.com.fiap.hackathon.usecase;

import br.com.fiap.hackathon.adapter.presenter.dto.search.SearchVideoResponse;
import br.com.fiap.hackathon.domain.entity.VideoFrameEntity;
import br.com.fiap.hackathon.domain.service.enums.ProcessorStatus;
import br.com.fiap.hackathon.infrastructure.database.repository.VideoRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchVideoUseCaseTest {

    @Mock
    private VideoRepositoryImpl repository;

    @InjectMocks
    private SearchVideoUseCase useCase;

    @Test
    @DisplayName("Deve buscar vídeos por email e converter para resposta")
    void deveBuscarPorEmail() {
        VideoFrameEntity entity = new VideoFrameEntity();
        entity.setEmail("user@example.com");
        entity.setStatus(ProcessorStatus.SUCCESS);
        entity.setCriadoEm(LocalDateTime.now());

        when(repository.findByEmail("user@example.com")).thenReturn(List.of(entity));

        List<SearchVideoResponse> response = useCase.searchByEmail("user@example.com");

        assertEquals(1, response.size());
        assertEquals("user@example.com", response.get(0).getEmail());
        assertEquals(ProcessorStatus.SUCCESS, response.get(0).getStatus());
    }

    @Test
    @DisplayName("Deve buscar vídeos por status e converter para resposta")
    void deveBuscarPorStatus() {
        VideoFrameEntity entity = new VideoFrameEntity();
        entity.setEmail("user2@example.com");
        entity.setStatus(ProcessorStatus.ERROR);
        entity.setCriadoEm(LocalDateTime.now());

        when(repository.findByStatus(ProcessorStatus.ERROR)).thenReturn(List.of(entity));

        List<SearchVideoResponse> response = useCase.searchByStatus(ProcessorStatus.ERROR);

        assertEquals(1, response.size());
        assertEquals("user2@example.com", response.get(0).getEmail());
        assertEquals(ProcessorStatus.ERROR, response.get(0).getStatus());
    }
}
