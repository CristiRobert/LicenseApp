package com.example.socialtrackingapp.model.entities;

public enum ClubAccess {
    PUBLIC("Public"),
    PRIVATE("Privat");

    private final String clubAccessAsString;

    ClubAccess( String clubAccessAsString ) {
        this.clubAccessAsString = clubAccessAsString;
    }

    public static ClubAccess convertStringToClubAccess( String clubAccessAsString ) {
        for(ClubAccess clubAccess : ClubAccess.values()) {
            if(clubAccess.clubAccessAsString.equals(clubAccessAsString)) {
                return clubAccess;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return clubAccessAsString;
    }
}
