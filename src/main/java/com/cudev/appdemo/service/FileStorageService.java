package com.cudev.appdemo.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path storageFolder = Paths.get("uploads");

    public FileStorageService() {

        try {

            Files.createDirectories(storageFolder);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Value("${server.host}") // Đọc URL server từ cấu hình
    private String serverHost;


    private boolean isImgFile(MultipartFile file) {

        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());

        return Arrays.asList(new String[]{"png", "jpg", "jpeg", "bmp"}).contains(fileExtension.trim().toLowerCase());
    }

    public String saveFile(MultipartFile file) {
        try {

            if (file.isEmpty()) {
                throw new RuntimeException("File is empty");
            }

            if(!isImgFile(file)) {
                throw new RuntimeException("File khong hop le");
            }

            float fileSize = file.getSize() / 1_000_000f;
            if(fileSize > 5.0f) {
                throw new RuntimeException("File size too large");
            }

            // Tạo tên file duy nhất
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path destination = storageFolder.resolve(fileName);

            try(InputStream input = file.getInputStream()) {
                Files.copy(input, destination, StandardCopyOption.REPLACE_EXISTING);
            }

            if (Files.exists(destination)) {
                System.out.println("File saved successfully!");
            } else {
                System.out.println("File save failed!");
            }
            return buildFullImageUrl(fileName);
        } catch (IOException e) {
            throw new RuntimeException("Lỗi khi lưu file: " + e.getMessage());
        }
    }

    private String buildFullImageUrl(String fileName) {
        return serverHost + "/uploads/" + fileName;
    }
}
