package br.com.fiap.hackathon.domain.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoValidationService {

    public boolean isValidVideoFile(String filename) {
        int dotIndex = filename.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == filename.length() - 1) {
            return false; // Sem extensão/extensão vazia
        }
        String ext = filename.substring(dotIndex).toLowerCase();
        return List.of(".mp4", ".avi", ".mov", ".mkv", ".wmv", ".flv", ".webm").contains(ext);
    }

}
