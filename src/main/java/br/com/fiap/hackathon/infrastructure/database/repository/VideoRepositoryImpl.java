package br.com.fiap.hackathon.infrastructure.database.repository;

import br.com.fiap.hackathon.domain.entity.VideoFrameEntity;
import br.com.fiap.hackathon.domain.repository.VideoFrameRepository;
import br.com.fiap.hackathon.domain.service.enums.ProcessorStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class VideoRepositoryImpl {

    private final VideoFrameRepository repository;

    public List<VideoFrameEntity> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public List<VideoFrameEntity> findByStatus(ProcessorStatus status) {
        return repository.findByStatus(status);
    }

}
