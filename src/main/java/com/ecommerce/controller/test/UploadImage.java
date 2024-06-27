package com.ecommerce.controller.test;

import com.ecommerce.service.impl.ImageServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadImage {

    @Autowired
    private ImageServiceImpl imageService;

    /*@PostMapping("/upload-picture")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws Exception {

        return new ResponseEntity<>(imageService.handleFileUpload(file), HttpStatus.OK);

    }*/
}
