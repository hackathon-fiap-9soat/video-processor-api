package br.com.fiap.hackathon.infrastructure.aws.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

import static org.assertj.core.api.Assertions.assertThat;

class AwsServicesConfigTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(br.com.fiap.hackathon.infrastructure.aws.config.AwsServicesConfig.class);

    @Test
    @DisplayName("Deve criar bean de S3Client no contexto")
    void deveCriarBeanS3Client() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(S3Client.class);
        });
    }

    @Test
    @DisplayName("Deve criar bean de SqsClient no contexto")
    void deveCriarBeanSqsClient() {
        contextRunner.run(context -> {
            assertThat(context).hasSingleBean(SqsClient.class);
        });
    }
}