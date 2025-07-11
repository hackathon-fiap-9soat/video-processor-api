package br.com.fiap.hackathon.usecase;

import br.com.fiap.hackathon.adapter.presenter.dto.search.SearchVideoResponse;
import br.com.fiap.hackathon.domain.entity.VideoFrameEntity;
import br.com.fiap.hackathon.domain.service.enums.ProcessorStatus;
import br.com.fiap.hackathon.infrastructure.database.repository.VideoRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchVideoUseCase {

    private final VideoRepositoryImpl repository;

    public List<SearchVideoResponse> searchByEmail(String email) {
        List<VideoFrameEntity> entities = repository.findByEmail(email);
        return toDtoList(entities);
    }

    public List<SearchVideoResponse> searchByStatus(ProcessorStatus status) {
        List<VideoFrameEntity> entities = repository.findByStatus(status);
        return toDtoList(entities);
    }

    private List<SearchVideoResponse> toDtoList(List<VideoFrameEntity> entities) {
        return entities.stream()
                .map(entity -> new SearchVideoResponse(
                        entity.getStatus(),
                        entity.getEmail(),
                        entity.getCriadoEm()
                )).toList();
    }

}
