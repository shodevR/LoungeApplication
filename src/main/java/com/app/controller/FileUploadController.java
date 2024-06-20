package com.app.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {

    // Define the location to store the uploaded files
    private final Path fileStorageLocation = Paths.get(System.getProperty("user.dir") + "/uploads");

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String filePath = null;
        String pathReturn = "";

        try {
            // Generate a random file name
            String randomFileName = UUID.randomUUID().toString().replace("-", "");
            String fileExtension = getFileExtension(file.getOriginalFilename());
            pathReturn = "/api/file/view/" + randomFileName + "." + fileExtension;
            filePath = fileStorageLocation.resolve(randomFileName + "." + fileExtension).toString();

            // Ensure the directory exists
            File directory = fileStorageLocation.toFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Save the file
            FileOutputStream fout = new FileOutputStream(filePath);
            fout.write(file.getBytes());
            fout.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (filePath != null) {
                File fileToDelete = new File(filePath);
                fileToDelete.delete();
            }
            return ResponseEntity.status(500).body("Error in uploading file: " + e.getMessage());
        }
        return ResponseEntity.ok(pathReturn);
    }

    @GetMapping("/view/{filename:.+}")
    public ResponseEntity<Resource> viewFile(@PathVariable String filename) {
        try {
            // Load the file as a resource
            Path filePath = fileStorageLocation.resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1 || dotIndex == fileName.length() - 1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }

    @GetMapping("/getFiles")
    public String[] getFiles() {
        File directory = fileStorageLocation.toFile();
        return directory.list();
    }
}
