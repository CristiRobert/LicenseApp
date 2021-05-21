package com.example.socialtrackingapp.model.entities;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Activity implements Serializable {

    private long id;
    private String title;
    private String description;
    private long startDateTime;
    private List<Point> pointList;
    private TypeEffort typeEffort;
    private Sport sport;
    private ActivityAccess activityAccess;
    private List<Like> likeList;
    private List<PublicMessage> responses;
    private Bitmap image;

    public Activity(
            String title, String description, long startDateTime, TypeEffort typeEffort,
            Sport sport, ActivityAccess activityAccess, Bitmap image
                   ) {
        this.title = title;
        this.description = description;
        this.startDateTime = startDateTime;
        this.typeEffort = typeEffort;
        this.sport = sport;
        this.activityAccess = activityAccess;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
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

    public long getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime( long startDateTime ) {
        this.startDateTime = startDateTime;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void setPointList( List<Point> pointList ) {
        this.pointList = pointList;
    }

    public TypeEffort getTypeEffort() {
        return typeEffort;
    }

    public void setTypeEffort( TypeEffort typeEffort ) {
        this.typeEffort = typeEffort;
    }

    public void setTypeEffort( String typeEffortAsString ) throws IllegalArgumentException {
        this.typeEffort = TypeEffort.valueOf(typeEffortAsString);
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport( Sport sport ) {
        this.sport = sport;
    }

    public ActivityAccess getActivityAccess() {
        return activityAccess;
    }

    @JsonIgnore
    public void setActivityAccess( ActivityAccess activityAccess ) {
        this.activityAccess = activityAccess;
    }

    public void setActivityAccess( String activityAccessAsString ) throws IllegalArgumentException {
        this.activityAccess = ActivityAccess.valueOf(activityAccessAsString);
    }

    public List<Like> getLikeList() {
        return likeList;
    }

    public void setLikeList( List<Like> likeList ) {
        this.likeList = likeList;
    }

    public List<PublicMessage> getResponses() {
        return responses;
    }

    public void setResponses( List<PublicMessage> responses ) {
        this.responses = responses;
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
        Activity activity = (Activity)o;
        return id == activity.id &&
                startDateTime == activity.startDateTime &&
                Objects.equals(title, activity.title) &&
                Objects.equals(description, activity.description) &&
                Objects.equals(pointList, activity.pointList) &&
                typeEffort == activity.typeEffort &&
                Objects.equals(sport, activity.sport) &&
                activityAccess == activity.activityAccess &&
                Objects.equals(likeList, activity.likeList) &&
                Objects.equals(responses, activity.responses) &&
                Objects.equals(image, activity.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, startDateTime, pointList, typeEffort, sport,
                            activityAccess, likeList, responses, image
                           );
    }
}
