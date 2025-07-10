package br.com.fiap.hackathon.infrastructure.aws.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoMessageDTO {
    private String email;
    private String videoId;
}
