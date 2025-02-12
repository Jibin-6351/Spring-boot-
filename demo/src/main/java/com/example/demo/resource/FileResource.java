package com.example.demo.resource;

import com.example.demo.Exception.ErrorObj;
import com.example.demo.domain.File;
import com.example.demo.repository.FileRepository;
import com.example.demo.service.FileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class FileResource {


    private FileService fileService;

    @PostMapping("uploadfile")
    public ResponseEntity<File>uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileService.uploadFile(file));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<ErrorObj> IOException(IOException e){
        ErrorObj errorObj = new ErrorObj();
        errorObj.setError(e.getMessage());
        errorObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorObj, HttpStatus.BAD_REQUEST);
    }



}
