package com.app.services;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;



public class ImageToBash {
    public static String imageToBash(String imagePath) throws IOException{

        // Replace with your image path
        byte[] fileContent = Files.readAllBytes(Paths.get(imagePath));
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        System.out.println(encodedString);
        return encodedString;
    }
}
