package com.epam.service;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Vadym_Vlasenko on 8/20/2015.
 */
@Service
public class DownloadService {

    public String downloadFile(String name, MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                //String rootPath = System.getProperty("catalina.home");
                String rootPath = "C:";
                File dir = new File(rootPath + File.separator + "tmpFiles");
                if (!dir.exists())
                    dir.mkdirs();

                // Create the file on server
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                serverFile.createNewFile();
                System.out.println(serverFile);
                System.out.println(serverFile.getAbsolutePath());
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return serverFile.getAbsolutePath();
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

}
