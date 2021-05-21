package com.example.socialtrackingapp.model.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Like implements Serializable {

    private long id;
    private long userId;

    public Like( long userId ) {
        this.id = new Random().nextLong();
        this.userId = userId;
    }

    public Like() {}

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId( long userId ) {
        this.userId = userId;
    }

    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Like like = (Like)o;
        return id == like.id &&
                userId == like.userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }
}
