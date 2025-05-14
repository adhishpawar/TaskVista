package com.taskvista.taskvista.file;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    //Init the upload directory if it doesn't exist
    public void init(){
        try{
            Files.createDirectories(Paths.get(uploadDir));
        }catch (IOException e){
            throw new RuntimeException("Could not create upload directory", e);
        }
    }

    //Store the file in the specified direct
    public String storeFile(MultipartFile file){
        //Normalize file name
        String filename  = file.getOriginalFilename();
        Path targetLocation = Paths.get(uploadDir + File.separator + filename);

        try{
            Files.copy(file.getInputStream(),targetLocation);
            return filename;
        }catch (IOException e){
            throw new RuntimeException("Could not store file " + filename, e);
        }
    }

    // Load the file from the local directory for download
    public Path loadFile(String filename) {
        return Paths.get(uploadDir).resolve(filename).normalize();
    }

    // Serve the file as a download
    public byte[] downloadFile(String filename) throws IOException {
        Path filePath = loadFile(filename);
        return Files.readAllBytes(filePath);
    }

}
