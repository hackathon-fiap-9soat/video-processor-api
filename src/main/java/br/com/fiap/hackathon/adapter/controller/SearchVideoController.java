package br.com.fiap.hackathon.adapter.controller;

import br.com.fiap.hackathon.adapter.presenter.dto.search.SearchVideoRequest;
import br.com.fiap.hackathon.adapter.presenter.dto.search.SearchVideoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class SearchVideoController {

    @PostMapping("/search")
    public SearchVideoResponse searchVideo(@RequestBody SearchVideoRequest searchVideoRequest) {
        return  null;
    }
}
