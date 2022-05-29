package com.comicall.ComicallApi.services.images;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface IImagesService {
    Path store(MultipartFile file, String path);
    Resource load(String path);
}
