package com.comicall.ComicallApi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserComicsKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "comics_id")
    Long comicsId;

    public UserComicsKey(){}

    public UserComicsKey(Long userId, Long comicsId) {
        this.userId = userId;
        this.comicsId = comicsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getComicsId() {
        return comicsId;
    }

    public void setComicsId(Long comicsId) {
        this.comicsId = comicsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserComicsKey that = (UserComicsKey) o;
        return userId != null && Objects.equals(userId, that.userId)
                && comicsId != null && Objects.equals(comicsId, that.comicsId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, comicsId);
    }
}
