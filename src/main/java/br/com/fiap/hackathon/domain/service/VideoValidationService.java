package br.com.fiap.hackathon.domain.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoValidationService {

    public boolean isValidVideoFile(String filename) {
        String ext = filename.substring(filename.lastIndexOf(".")).toLowerCase();
        return List.of(".mp4", ".avi", ".mov", ".mkv", ".wmv", ".flv", ".webm").contains(ext);
    }

}
