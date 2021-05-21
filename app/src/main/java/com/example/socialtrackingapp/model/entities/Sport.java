package com.example.socialtrackingapp.model.entities;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

public class Sport implements Serializable {

    private SportType sportType;
    private Bitmap image;

    public Sport( SportType sportType ) {
        this.sportType = sportType;
    }

    public SportType getSportType() {
        return sportType;
    }

    @JsonIgnore
    public void setSportType( SportType sportType ) {
        this.sportType = sportType;
    }

    public void setSportType( String sportTypeAsString ) throws IllegalArgumentException {
        this.sportType = SportType.valueOf(sportTypeAsString);
    }

    @JsonIgnore
    public Bitmap getImage() {
        return image;
    }

    @JsonIgnore
    public void setImage( Bitmap image ) {
        this.image = image;
    }

    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Sport sport = (Sport)o;
        return sportType == sport.sportType &&
                Objects.equals(image, sport.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sportType, image);
    }
}
