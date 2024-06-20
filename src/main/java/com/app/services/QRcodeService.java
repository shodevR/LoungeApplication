package com.app.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

@Service
public class QRcodeService {

	public String QrGenerate(String data,String id,String location) throws WriterException, IOException{
		String path = System.getProperty("user.dir") + "/Uploads/" + "QR/"+location;
		String filePath =path +"/" + id +".jpg"; 
       
        
        try { 
            // Creating directories if they don't exist
            File directory = new File(filePath).getParentFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }
            
             BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, 500, 500);
        MatrixToImageWriter.writeToPath(matrix, "jpg", Paths.get(filePath));
              
        }  
        // Catch block to handle exceptions 
        catch (Exception e) { 
            e.printStackTrace(); 
            
        } 
        
        return filePath;
	}
}
