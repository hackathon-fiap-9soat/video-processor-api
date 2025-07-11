package br.com.fiap.hackathon.infrastructure.aws.service;

import br.com.fiap.hackathon.infrastructure.aws.service.dto.VideoMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SimpleQueueServiceTest {

    @Mock
    private SqsClient sqsClient;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private SimpleQueueService simpleQueueService;

    @BeforeEach
    void init() {
        ReflectionTestUtils.setField(simpleQueueService, "queueUrl", "https://sqs.us-east-1.amazonaws.com/123456789012/my-queue");
    }

    @Test
    @DisplayName("Deve enviar mensagem corretamente para a fila SQS")
    void deveEnviarMensagemParaSqs() throws Exception {
        VideoMessageDTO dto = new VideoMessageDTO("videoId123", "user@example.com");
        when(objectMapper.writeValueAsString(dto)).thenReturn("{\"videoId\":\"videoId123\"}");

        simpleQueueService.sendMessage(dto);

        verify(sqsClient).sendMessage(argThat((SendMessageRequest req) ->
                req.queueUrl().equals("https://sqs.us-east-1.amazonaws.com/123456789012/my-queue") &&
                        req.messageBody().contains("videoId123")
        ));
    }

    @Test
    @DisplayName("Deve lançar exceção ao falhar serialização JSON")
    void deveLancarErroAoFalharSerializacao() throws JsonProcessingException {
        VideoMessageDTO dto = new VideoMessageDTO("videoId123", "user@example.com");
        when(objectMapper.writeValueAsString(any())).thenThrow(JsonProcessingException.class);

        assertThrows(RuntimeException.class, () -> simpleQueueService.sendMessage(dto));
    }
}