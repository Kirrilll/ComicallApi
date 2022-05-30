package com.comicall.ComicallApi.services.images;

import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class ImagesService implements IImagesService{


    Path storage = Paths.get("storage");

    @Override
    public String store(MultipartFile file, String author, String comics, String inputFileName) throws IOException {

        String[] fileSplit = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String fileName = inputFileName + "." + fileSplit[1];

        Path fileRelativePath = Paths.
                get(author)
                .resolve(comics)
                .resolve(fileName);

        Path uploadPath = Paths
                .get(storage.toUri())
                .resolve(fileRelativePath);


        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, uploadPath, StandardCopyOption.REPLACE_EXISTING);
            return fileRelativePath.toString();
        } catch (IOException ioe) {
            throw new IOException("Could not save file: " + file.getOriginalFilename(), ioe);
        }
    }

    @Override
    public UrlResource load(String filePath) {
        Path path = Paths.get("storage").resolve(filePath);
        try {
            UrlResource resource = new UrlResource(path.toUri());
            if (resource.exists() && resource.isReadable())
                return resource;
            else
                throw new RuntimeException("File doesn't exist or not readable");

        } catch (MalformedURLException e) {
            throw new RuntimeException("Could not read the file", e);
        }
    }

    @Override
    public void delete(String path){
        String fullPath = Paths.get(storage.toUri()).resolve(path).toString();

        File file = new File(fullPath);
        if(file.exists()){
            file.delete();
        }
        if(file.exists()){
            String[] paths = file.list();
            for(String str:paths){
                delete(fullPath+"\\"+str);
            }
            file.delete();
            paths = null;
        }
        file = null;
    }

    private String getRandomName(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
