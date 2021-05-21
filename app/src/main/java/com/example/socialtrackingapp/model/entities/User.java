package com.example.socialtrackingapp.model.entities;

import android.graphics.Bitmap;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.Random;

public class User extends BaseObservable implements Serializable {

    private long id;
    private boolean isDeleted;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private long birthDate;
    private Gender gender;
    private String city;
    private Country country;
    private Bitmap profileImage;
    private List<Long> followersIdList;
    private List<Activity> activityList;
    private List<Notification> notificationList;

    public User(
            String firstName, String lastName, String email, String password, long birthDate
            , Gender gender, String city, Country country, Bitmap profileImage
               ) {
        this.id = new Random().nextLong();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.birthDate = birthDate;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.profileImage = profileImage;
    }

    public User(){}

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

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName( String firstName ) {
        this.firstName = firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName( String lastName ) {
        this.lastName = lastName;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate( long birthDate ) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    @JsonIgnore
    public void setGender( Gender gender ) {
        this.gender = gender;
    }

    public void setGender( String genderAsString ) throws IllegalArgumentException {
        this.gender = Gender.valueOf(genderAsString);
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

    @JsonIgnore
    public Bitmap getProfileImage() {
        return profileImage;
    }

    @JsonIgnore
    public void setProfileImage( Bitmap profileImage ) {
        this.profileImage = profileImage;
    }

    public List<Long> getFollowersIdList() {
        return followersIdList;
    }

    public void setFollowersIdList( List<Long> followersIdList ) {
        this.followersIdList = followersIdList;
    }

    public List<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList( List<Activity> activityList ) {
        this.activityList = activityList;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList( List<Notification> notificationList ) {
        this.notificationList = notificationList;
    }

    @JsonIgnore
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        User user = (User)o;
        return id == user.id &&
                isDeleted == user.isDeleted &&
                birthDate == user.birthDate &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                gender == user.gender &&
                Objects.equals(city, user.city) &&
                country == user.country &&
                Objects.equals(profileImage, user.profileImage) &&
                Objects.equals(followersIdList, user.followersIdList) &&
                Objects.equals(activityList, user.activityList) &&
                Objects.equals(notificationList, user.notificationList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isDeleted, firstName, lastName, email, password, birthDate,
                            gender, city, country, profileImage, followersIdList, activityList,
                            notificationList
                           );
    }
}
