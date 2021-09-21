package com.ocean.controller;

import com.ocean.models.*;
import com.ocean.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("fileUploadController")
@RequestMapping(value= "api")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class FileUploadController {

    private S3Service s3Service;

    @Autowired
    public FileUploadController(S3Service s3Service){this.s3Service = s3Service;}

    @Autowired
    private Environment environment;

    @PostMapping("/profile")
    public ResponseEntity<Response> uploadProfileImage(@RequestParam(value = "file")MultipartFile file) {
        return new ResponseEntity<Response>(s3Service.uploadProfileImage(file), HttpStatus.OK);
    }

    @PostMapping("/image")
    public ResponseEntity<Response> uploadFile(@RequestParam(value = "file")MultipartFile file) {
        return new ResponseEntity<Response>(s3Service.uploadImage(file), HttpStatus.OK);
    }

}