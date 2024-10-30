package com.com.SpringBootFileUpload.service;
import com.com.SpringBootFileUpload.entity.FileData;
import com.com.SpringBootFileUpload.entity.ImageData;
import com.com.SpringBootFileUpload.repository.FileDataRepository;
import com.com.SpringBootFileUpload.repository.StorageRepository;
import com.com.SpringBootFileUpload.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
@Service
public class StorageService {
    @Autowired
    private StorageRepository repository;

    @Autowired
    private FileDataRepository fileDataRepository;
   private final String FOLDER_PATH="F:\\JavaSpring\\MyFiles";

    // Image Upload with binary File
    public  String uploadImage(MultipartFile file) throws Exception {
        ImageData imageData = repository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtils.compressImage(file.getBytes())).build());

        if (imageData !=null){
            return "File Upload Successfully" +file.getOriginalFilename();
        }
        return  null;

    }
// Image Download with binary file
    public byte [] downloadImage(String fileName){
        Optional<ImageData> dbImageData = repository.findByName(fileName);
        byte [] images = ImageUtils.decompressImage(dbImageData.get().getImageData());

        return  images;
    }

    // Image Upload with filePath
//    public  String uploadImageToFileSystem(MultipartFile file) throws IOException {
//        String filePath = FOLDER_PATH + file.getOriginalFilename();
//
//        FileData fileData = fileDataRepository.save(FileData.builder()
//                .name(file.getOriginalFilename())
//                .type(file.getContentType())
//                .filePath(filePath).build() );
//
//                file.transferTo(new File(filePath));
//
//
//        if (fileData !=null){
//            return "File Upload Successfully" + filePath;
//        }
//        return  null;
//
//    }
//
//    // Image Download with FileSystem
//    public byte [] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
//        String filePath = fileData.get().getFilePath();
//        byte [] images = Files.readAllBytes(new File(filePath).toPath());
//
//        return  images;
//    }





}





















