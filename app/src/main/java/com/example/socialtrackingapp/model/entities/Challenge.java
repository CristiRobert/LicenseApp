package com.example.socialtrackingapp.model.entities;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Random;

public class Challenge implements Serializable {

    private long id;
    private boolean isDeleted;
    private String title;
    private String description;
    private ChallengeType challengeType;
    private int target;
    private Sport sport;
    private Bitmap image;
    private long startDate;
    private long finishDate;

    public Challenge(
            String title, String description, ChallengeType challengeType, int target,
            Sport sport, Bitmap image, long startDate, long finishDate
                    ) {
        this.id = new Random().nextLong();
        this.title = title;
        this.description = description;
        this.challengeType = challengeType;
        this.target = target;
        this.sport = sport;
        this.image = image;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted( boolean deleted ) {
        isDeleted = deleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle( String title ) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    @JsonIgnore
    public void setChallengeType( ChallengeType challengeType ) {
        this.challengeType = challengeType;
    }

    public void setChallengeType( String challengeTypeAsString ) throws IllegalArgumentException {
        this.challengeType = ChallengeType.valueOf(challengeTypeAsString);
    }

    public int getTarget() {
        return target;
    }

    public void setTarget( int target ) {
        this.target = target;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport( Sport sport ) {
        this.sport = sport;
    }

    @JsonIgnore
    public Bitmap getImage() {
        return image;
    }

    @JsonIgnore
    public void setImage( Bitmap image ) {
        this.image = image;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate( long startDate ) {
        this.startDate = startDate;
    }

    public long getFinishDate() {
        return finishDate;
    }

    public void setFinishDate( long finishDate ) {
        this.finishDate = finishDate;
    }

    public boolean isValid()
    {
        return System.currentTimeMillis() <= finishDate;
    }
}
