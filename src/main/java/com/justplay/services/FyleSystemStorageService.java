package com.justplay.services;

import static java.nio.file.Files.copy;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.PostConstruct;

@Service
public  class FyleSystemStorageService implements StorageService {

    @Value("${media.location}")
    private String mediaLocation; // Donde almacenar y recuperar archivos

    private Path rootLocation; // Representara la ruta raiz de almacenamiento

    @Override
    @PostConstruct
    public void init() throws IOException {
        rootLocation = Paths.get(mediaLocation);
        Files.createDirectories(rootLocation);
    }

    @Override
    public String store(MultipartFile file) {
        try{

            if(file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file");
            }
            String filename = file.getOriginalFilename();
            Path destinationFile = rootLocation.resolve(Paths.get(filename))
                                    .normalize().toAbsolutePath();
             try(InputStream inputStream = file.getInputStream()) {
                copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
             }
             return filename;
        }catch(IOException e) {
            throw new RuntimeException("Failed to store file", e);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource((file.toUri()));

            if(resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read file: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read file: " + filename);
        }
    }
    
}
