package com.example.socialtrackingapp.model.entities;

public enum ActivityAccess {

    EVERYBODY("Toată lumea"),
    FOLLOWERS("Urmăritori"),
    ONLY_ME("Doar eu");

    private final String activityAccessAsString;

    ActivityAccess( String activityAccessAsString ) {
        this.activityAccessAsString = activityAccessAsString;
    }

    public static ActivityAccess convertStringToActivityAccess( String activityAccessAsString ) {
        for(ActivityAccess activityAccess : ActivityAccess.values()) {
            if(activityAccess.activityAccessAsString.equals(activityAccessAsString)) {
                return activityAccess;
            }
        }
        return null;
    }

    public String convertToString() {
        return activityAccessAsString;
    }
}
