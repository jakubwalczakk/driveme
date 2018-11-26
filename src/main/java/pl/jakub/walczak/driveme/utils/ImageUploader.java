package pl.jakub.walczak.driveme.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;

@Component
public class ImageUploader {

    public byte[] uploadFile(String path) {

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());

        byte[] bFile = new byte[(int) file.length()];
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return bFile;
    }
}
