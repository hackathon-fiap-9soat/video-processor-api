package br.com.fiap.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VideoProcessorApplication {
    public static void main(String[] args) {
        System.out.println("UPLOAD LIMIT: " +
                System.getProperty("spring.servlet.multipart.max-file-size"));
        SpringApplication.run(VideoProcessorApplication.class);
    }
}
