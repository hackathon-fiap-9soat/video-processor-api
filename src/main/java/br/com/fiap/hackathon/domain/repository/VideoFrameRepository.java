package br.com.fiap.hackathon.domain.repository;

import br.com.fiap.hackathon.domain.entity.VideoFrameEntity;
import br.com.fiap.hackathon.domain.service.enums.ProcessorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoFrameRepository extends JpaRepository<VideoFrameEntity, Long> {
    List<VideoFrameEntity> findByEmail(String email);
    List<VideoFrameEntity> findByStatus(ProcessorStatus status);
}
