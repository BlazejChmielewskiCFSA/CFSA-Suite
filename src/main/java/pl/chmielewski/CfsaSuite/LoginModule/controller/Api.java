package pl.chmielewski.CfsaSuite.LoginModule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import pl.chmielewski.CfsaSuite.LoginModule.service.FileUploadService;

import java.io.IOException;

@RestController
public class Api {

    private FileUploadService fileUploadService;

    @Autowired
    public Api(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        fileUploadService.uploadFile(file);
    }
}