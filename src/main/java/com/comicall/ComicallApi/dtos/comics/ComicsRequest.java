package com.comicall.ComicallApi.dtos.comics;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class ComicsRequest {
    private String name;
    private boolean isReady;
    private String description;
    private int publishYear;
    private MultipartFile logo;

    public ComicsRequest(){}

    public ComicsRequest(String name, boolean isReady, String description, int publishYear, MultipartFile logo) {
        this.name = name;
        this.isReady = isReady;
        this.description = description;
        this.publishYear = publishYear;
        this.logo = logo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    public boolean getIsReady() {
        return isReady;
    }

    public void setIsReady(boolean ready) {
        isReady = ready;
    }
}
