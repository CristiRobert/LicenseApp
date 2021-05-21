package com.example.socialtrackingapp.model.entities;

public enum Gender {
    MALE("Masculin"),
    FEMALE("Feminin");

    private final String genderAsString;

    Gender( String genderAsString ) {
        this.genderAsString = genderAsString;
    }

    public static Gender convertStringToGender( String genderAsString ) {
        for(Gender gender : Gender.values()) {
            if(gender.genderAsString.equals(genderAsString)) {
                return gender;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return genderAsString;
    }
}
