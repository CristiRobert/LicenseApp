package com.example.socialtrackingapp.model.entities;

public enum TypeEffort {

    HARD("Ridicat"),
    MEDIUM("Mediu"),
    EASY("Ușor");

    private final String typeEffortAsString;

    TypeEffort( String typeEffortAsString ) {
        this.typeEffortAsString = typeEffortAsString;
    }

    public static TypeEffort convertStringToTypeEffort( String typeEffortAsString ) {
        for(TypeEffort typeEffort : TypeEffort.values()) {
            if(typeEffort.typeEffortAsString.equals(typeEffortAsString)) {
                return typeEffort;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return typeEffortAsString;
    }
}
