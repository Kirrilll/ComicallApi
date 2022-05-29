package com.comicall.ComicallApi.helpers.files;

import java.nio.file.Path;

public interface IFileStorage {
    String getLocation();
    Path getPath();
    void createDirectory();
}
