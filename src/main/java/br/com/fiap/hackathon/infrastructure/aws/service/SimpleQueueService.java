package br.com.fiap.hackathon.infrastructure.aws.service;

import br.com.fiap.hackathon.infrastructure.aws.service.dto.VideoMessageDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class SimpleQueueService {

    private final SqsClient sqsClient;
    private final ObjectMapper objectMapper;

    @Value("${aws.sqs.queue-url}")
    public String queueUrl;

    public void sendMessage(VideoMessageDTO request) {
        try {
            String messageBody = objectMapper.writeValueAsString(request);

            SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .messageBody(messageBody)
                    .build();

            sqsClient.sendMessage(sendMsgRequest);
            log.info("Mensagem enviada para fila SQS: {}", request);
        } catch (JsonProcessingException e) {
            log.error("Erro ao serializar mensagem para fila SQS");
            throw new RuntimeException(e);
        }
    }

}
