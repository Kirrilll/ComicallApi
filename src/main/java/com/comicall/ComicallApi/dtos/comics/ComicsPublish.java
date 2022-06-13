package com.comicall.ComicallApi.dtos.comics;

public class ComicsPublish {
    Long comicsId;
    boolean isRead;

    public ComicsPublish(){}

    public ComicsPublish(Long comicsId, boolean isRead) {
        this.comicsId = comicsId;
        this.isRead = isRead;
    }

    public Long getComicsId() {
        return comicsId;
    }

    public void setComicsId(Long comicsId) {
        this.comicsId = comicsId;
    }

    public boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }


}
