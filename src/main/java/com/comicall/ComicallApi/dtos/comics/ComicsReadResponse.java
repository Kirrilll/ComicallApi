package com.comicall.ComicallApi.dtos.comics;

import com.comicall.ComicallApi.dtos.page.PageRequest;
import com.comicall.ComicallApi.dtos.page.PageResponse;

import java.util.List;

public class ComicsReadResponse {
    private List<PageResponse> pages;
    private int bookmark;

    public ComicsReadResponse(){}

    public ComicsReadResponse(List<PageResponse> pages, int bookmark) {
        this.pages = pages;
        this.bookmark = bookmark;
    }

    public List<PageResponse> getPages() {
        return pages;
    }

    public void setPages(List<PageResponse> pages) {
        this.pages = pages;
    }

    public int getBookmark() {
        return bookmark;
    }

    public void setBookmark(int bookmark) {
        this.bookmark = bookmark;
    }
}
