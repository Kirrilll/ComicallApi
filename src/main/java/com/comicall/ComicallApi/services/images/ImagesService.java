package com.comicall.ComicallApi.services.images;

import com.comicall.ComicallApi.helpers.files.IFileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

public class ImagesService implements IImagesService{

    @Autowired
    IFileStorage fileStorage;

    @Override
    public Path store(MultipartFile file, String path) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(path));
        String[] fileSplit = Objects.requireNonNull(file.getOriginalFilename()).split("\\.");
        String newFileName = getRandomName() + "." + fileSplit[1];
        String fileUrl = "/"+ path +"/" + newFileName;
        Path filePath = Paths.get(URI.create(fileStorage.getPath() + "/" + path  + "/" + newFileName));


        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Could not store the file", e);
        }
        return filePath;
    }

    @Override
    public Resource load(String path) {
        return null;
    }

    private String getRandomName(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
