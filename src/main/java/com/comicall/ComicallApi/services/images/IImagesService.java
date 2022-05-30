package com.comicall.ComicallApi.services.images;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface IImagesService {
    String store(MultipartFile file, String author, String comics, String fileName) throws IOException;
    UrlResource load(String path);
    void delete(String path);
}
