package com.comicall.ComicallApi.helpers.files;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class FileUtils {
    public String getRandomName(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
