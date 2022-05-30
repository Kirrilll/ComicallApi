package com.comicall.ComicallApi.dtos.page;

import org.springframework.web.multipart.MultipartFile;

public class PageRequest {
    private MultipartFile[] images;
    private Long comicsId;

    public PageRequest(){}

    public PageRequest(MultipartFile[] images, Long comicsId) {
        this.images = images;
        this.comicsId = comicsId;
    }

    public MultipartFile[] getImages() {
        return images;
    }

    public void setImages(MultipartFile[] images) {
        this.images = images;
    }

    public Long getComicsId() {
        return comicsId;
    }

    public void setComicsId(Long comicsId) {
        this.comicsId = comicsId;
    }
}
