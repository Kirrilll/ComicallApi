package com.comicall.ComicallApi.helpers.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorage implements IFileStorage{
    private final Path fileStoragePath;
    private final String fileStorageLocation;

    public FileStorage(String propertyLocation) {
        fileStorageLocation = propertyLocation;
        fileStoragePath = Paths.get("storage").toAbsolutePath().normalize();
    }

    @Override
    public String getLocation() {
        return fileStorageLocation;
    }

    @Override
    public Path getPath() {
        return fileStoragePath;
    }

    @Override
    public void createDirectory() {
        try {
            Files.createDirectories(fileStoragePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not create the directory");
        }
    }
}
