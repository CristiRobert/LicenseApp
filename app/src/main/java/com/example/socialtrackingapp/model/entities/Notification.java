package com.example.socialtrackingapp.model.entities;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Notification implements Serializable {

    private long id;
    private String description;
    private Bitmap image;
    private long releaseDateTime;

    public Notification( String description, Bitmap image ) {
        this.id = new Random().nextLong();
        this.description = description;
        this.image = image;
        this.releaseDateTime = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    @JsonIgnore
    public Bitmap getImage() {
        return image;
    }

    @JsonIgnore
    public void setImage( Bitmap image ) {
        this.image = image;
    }

    public long getReleaseDateTime() {
        return releaseDateTime;
    }

    public void setReleaseDateTime( long releaseDateTime ) {
        this.releaseDateTime = releaseDateTime;
    }

    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Notification that = (Notification)o;
        return id == that.id &&
                releaseDateTime == that.releaseDateTime &&
                Objects.equals(description, that.description) &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, image, releaseDateTime);
    }
}
