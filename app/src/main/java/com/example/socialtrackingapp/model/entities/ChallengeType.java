package com.example.socialtrackingapp.model.entities;

public enum ChallengeType {
    NONE(""),
    VOLUME("Volum"),
    DISTANCE("Distanță"),
    CLIMBING("Diferență de nivel");

    private final String challengeTypeAsString;

    ChallengeType( String challengeTypeAsString ) {
        this.challengeTypeAsString = challengeTypeAsString;
    }

    public static ChallengeType convertStringToChallengeType( String challengeTypeAsString ) {
        for(ChallengeType challengeType : ChallengeType.values()) {
            if(challengeType.challengeTypeAsString.equals(challengeTypeAsString)) {
                return challengeType;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return challengeTypeAsString;
    }
}
