package br.com.fiap.hackathon.adapter.presenter.dto.upload;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@RequiredArgsConstructor
public class UploadVideoRequest {

    private String email;
    private MultipartFile videoFile;

}
