package com.example.socialtrackingapp.model.entities;

public enum PrivateMessageState {

    SEEN("Văzut"),
    NOT_SEEN("Nevăzut");

    private final String privateMessageStateAsString;

    PrivateMessageState( String privateMessageStateAsString ) {
        this.privateMessageStateAsString = privateMessageStateAsString;
    }

    public static PrivateMessageState convertStringToPrivateMessageState( String privateMessageStateAsString ) {
        for(PrivateMessageState privateMessageState : PrivateMessageState.values()) {
            if(privateMessageState.privateMessageStateAsString.equals(privateMessageStateAsString)) {
                return privateMessageState;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return privateMessageStateAsString;
    }
}
