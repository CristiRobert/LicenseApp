package com.example.socialtrackingapp.model.entities;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Club implements Serializable {

    private long id;
    private boolean isDeleted;
    private String name;
    private String description;
    private String city;
    private Country country;
    private Sport sport;
    private long administratorId;
    private ClubAccess clubAccess;
    private List<PublicMessage> messageList;
    private List<User> memberList;
    private Bitmap image;

    public Club(
            String name, String description, String city, Country country, Sport sport,
            long administratorId, ClubAccess clubAccess, Bitmap image
               ) {
        this.id = new Random().nextLong();
        this.name = name;
        this.description = description;
        this.city = city;
        this.country = country;
        this.sport = sport;
        this.administratorId = administratorId;
        this.clubAccess = clubAccess;
        this.image = image;
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

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity( String city ) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    @JsonIgnore
    public void setCountry( Country country ) {
        this.country = country;
    }

    public void setCountry( String countryAsString ) throws IllegalArgumentException {
        this.country = Country.valueOf(countryAsString);
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport( Sport sport ) {
        this.sport = sport;
    }

    public long getAdministratorId() {
        return administratorId;
    }

    public void setAdministratorId( long administratorId ) {
        this.administratorId = administratorId;
    }

    public ClubAccess getClubAccess() {
        return clubAccess;
    }

    @JsonIgnore
    public void setClubAccess( ClubAccess clubAccess ) {
        this.clubAccess = clubAccess;
    }

    public void setClubAccess( String clubAccessAsString ) throws IllegalArgumentException {
        this.clubAccess = ClubAccess.valueOf(clubAccessAsString);
    }

    public List<PublicMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList( List<PublicMessage> messageList ) {
        this.messageList = messageList;
    }

    public List<User> getMemberList() {
        return memberList;
    }

    public void setMemberList( List<User> memberList ) {
        this.memberList = memberList;
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
        Club club = (Club)o;
        return id == club.id &&
                isDeleted == club.isDeleted &&
                administratorId == club.administratorId &&
                Objects.equals(name, club.name) &&
                Objects.equals(description, club.description) &&
                Objects.equals(city, club.city) &&
                country == club.country &&
                Objects.equals(sport, club.sport) &&
                clubAccess == club.clubAccess &&
                Objects.equals(messageList, club.messageList) &&
                Objects.equals(memberList, club.memberList) &&
                Objects.equals(image, club.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isDeleted, name, description, city, country, sport,
                            administratorId, clubAccess, messageList, memberList, image
                           );
    }
}
