package com.example.demo.service;

import com.example.demo.domain.File;
import com.example.demo.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    FileDTO getFile(Long id);

    File uploadFile(MultipartFile file) throws IOException;

}
