package br.com.fiap.hackathon.adapter.presenter.dto.search;

import br.com.fiap.hackathon.domain.service.enums.ProcessorStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class SearchVideoResponse {
    private ProcessorStatus status;
    private String email;
    private LocalDateTime criadoEm;
}
