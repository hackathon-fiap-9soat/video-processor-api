package br.com.fiap.hackathon.adapter.controller;

import br.com.fiap.hackathon.adapter.presenter.dto.search.SearchVideoResponse;
import br.com.fiap.hackathon.domain.service.enums.ProcessorStatus;
import br.com.fiap.hackathon.usecase.SearchVideoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SearchVideoController.class)
@Import(SearchVideoControllerTest.MockConfig.class)
class SearchVideoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SearchVideoUseCase searchVideoUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @TestConfiguration
    static class MockConfig {
        @Bean
        public SearchVideoUseCase searchVideoUseCase() {
            return mock(SearchVideoUseCase.class);
        }
    }

    @BeforeEach
    void setup() {
        objectMapper.findAndRegisterModules();
    }

    @Test
    @DisplayName("Deve buscar vídeos por email")
    void deveBuscarPorEmail() throws Exception {
        SearchVideoResponse response = new SearchVideoResponse(ProcessorStatus.PROCESSING, "user@example.com", LocalDateTime.now());
        when(searchVideoUseCase.searchByEmail("user@example.com")).thenReturn(List.of(response));

        mockMvc.perform(get("/search/by-email")
                        .param("email", "user@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }

    @Test
    @DisplayName("Deve buscar vídeos por status")
    void deveBuscarPorStatus() throws Exception {
        SearchVideoResponse response = new SearchVideoResponse(ProcessorStatus.PROCESSING, "user@example.com", LocalDateTime.now());
        when(searchVideoUseCase.searchByStatus(ProcessorStatus.PROCESSING)).thenReturn(List.of(response));

        mockMvc.perform(get("/search/by-status/PROCESSING"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").exists());
    }
}
