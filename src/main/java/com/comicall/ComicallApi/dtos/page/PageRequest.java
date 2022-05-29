package com.comicall.ComicallApi.dtos.page;

import org.springframework.web.multipart.MultipartFile;

public class PageRequest {
    //Заменить на MulipartFile
    private String filePath;
    private int pageNumber;

    public PageRequest(){}

    public PageRequest(String filePath, int pageNumber) {
        this.filePath = filePath;
        this.pageNumber = pageNumber;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

}
