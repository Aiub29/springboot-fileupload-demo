package com.com.SpringBootFileUpload.controller;


import com.com.SpringBootFileUpload.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/image")
public class StorageController {

    @Autowired
    private StorageService service;

    @PostMapping
    public ResponseEntity<?> uploadImage(@RequestParam("image")MultipartFile file) throws Exception {
        String uploadImage = service.uploadImage(file);
        return  ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    @GetMapping("/{fileName}")
    public  ResponseEntity<?> downloadImage(@PathVariable String fileName){
        byte[] imageData = service.downloadImage(fileName);
        return  ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(imageData);
    }

//    @PostMapping("/fileSystem")
//    public ResponseEntity<?> uploadImageToFileSystem(@RequestParam("image") MultipartFile file) throws IOException {
//        String uploadImage = service.uploadImageToFileSystem(file);
//        return  ResponseEntity.status(HttpStatus.OK)
//                .body(uploadImage);
//    }
//
//
//    @GetMapping("/fileSystem/{fileName}")
//    public  ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String fileName) throws IOException {
//        byte[] imageData = service.downloadImageFromFileSystem(fileName);
//        return  ResponseEntity.status(HttpStatus.OK)
//                .contentType(MediaType.valueOf("image/png"))
//                .body(imageData);
//    }

}

