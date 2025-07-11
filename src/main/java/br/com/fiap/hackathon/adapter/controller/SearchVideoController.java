package br.com.fiap.hackathon.adapter.controller;

import br.com.fiap.hackathon.adapter.presenter.dto.search.SearchVideoResponse;
import br.com.fiap.hackathon.domain.entity.VideoFrameEntity;
import br.com.fiap.hackathon.domain.service.enums.ProcessorStatus;
import br.com.fiap.hackathon.usecase.SearchVideoUseCase;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor
public class SearchVideoController {

    private final SearchVideoUseCase service;

    @GetMapping("/by-email")
    public ResponseEntity<List<SearchVideoResponse>> searchVideoByEmail(@RequestParam String email) {
        List<SearchVideoResponse> response = service.searchByEmail(email);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/by-status/{status}")
    public ResponseEntity<List<SearchVideoResponse>> searchVideoByProcessStatus(@PathVariable ProcessorStatus status) {
        List<SearchVideoResponse> response = service.searchByStatus(status);
        return ResponseEntity.ok(response);
    }
}
