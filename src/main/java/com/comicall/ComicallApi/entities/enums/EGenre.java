package com.comicall.ComicallApi.entities.enums;

public enum EGenre {
    ACTION("Экшен"),
    DETECTIVE("Детектив"),
    DRAMA("Драма"),
    COMEDY("Комедия"),
    ADVENTURES("Приключение"),
    SUPERHERO("Супергероика"),
    HORROR("Ужасы"),
    FANTASY("Фэнтези");

    private final String rus;
    EGenre(String rus){
        this.rus = rus;
    }

    public String getRus(){
        return this.rus;
    }
}
