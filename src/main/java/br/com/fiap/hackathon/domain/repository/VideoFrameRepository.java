package br.com.fiap.hackathon.domain.repository;

import br.com.fiap.hackathon.domain.entity.VideoFrameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoFrameRepository extends JpaRepository<VideoFrameEntity, Long> {

}
