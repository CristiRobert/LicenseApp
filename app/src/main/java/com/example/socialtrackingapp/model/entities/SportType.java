package com.example.socialtrackingapp.model.entities;

public enum SportType {
    NONE(""),
    RUNNING("Alergare"),
    CYCLING("Ciclism"),
    SWIMMING("Înot");

    private final String sportTypeAsString;

    SportType( String sportTypeAsString ) {
        this.sportTypeAsString = sportTypeAsString;
    }

    public static SportType convertStringToSportType( String sportTypeAsString ) {
        for(SportType sportType : SportType.values()) {
            if(sportType.sportTypeAsString.equals(sportTypeAsString)) {
                return sportType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return sportTypeAsString;
    }
}
