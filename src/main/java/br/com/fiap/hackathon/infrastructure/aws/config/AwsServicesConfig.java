package br.com.fiap.hackathon.infrastructure.aws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.EnvironmentVariableCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.sqs.SqsClient;

@Configuration
public class AwsServicesConfig {

    @Bean
    public SqsClient sqsClient() {
        AwsCredentialsProvider credentials = EnvironmentVariableCredentialsProvider.create();
        return SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentials)
                .build();
    }

    @Bean
    public S3Client s3Client() {
        AwsCredentialsProvider credentials = EnvironmentVariableCredentialsProvider.create();
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentials)
                .build();
    }
}
