package com.example.demo.service.implementation;

import com.example.demo.domain.File;
import com.example.demo.dto.FileDTO;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
@AllArgsConstructor

public class FileServiceImp implements FileService {


    private FileRepository fileRepository;


    @Override
    public FileDTO getFile(Long id) {
        Optional<File> file = fileRepository.findById(id);

        if (file.isEmpty()) {
            throw new RuntimeException("File not found with id " + id);
        }

        FileDTO fileDTO = new FileDTO();
        fileDTO.setId(file.get().getId());
        fileDTO.setName(file.get().getName());
        fileDTO.setPath(file.get().getPath());
        return fileDTO;

    }

    @Override
    public File uploadFile(MultipartFile file) throws IOException {
        try {

            String name = file.getOriginalFilename();
            Path path = Paths.get("C:\\Users\\Intern\\Downloads\\demo\\demo\\src\\main\\resources\\static\\images", name);
            file.transferTo(path);
            File fileEntity = new File();
            fileEntity.setName(name);
            fileEntity.setPath("http://localhost:8080/images/" + name);
            return fileRepository.save(fileEntity);


        } catch (IOException ex) {
            throw new IOException("Problem occured while uploading file");
        }
    }
}
