package com.example.socialtrackingapp.model.entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class PublicMessage implements Serializable {

    private long id;
    private String text;
    private long senderId;
    private long submissionDateTime;

    public PublicMessage( String text, long senderId ) {
        this.id = new Random().nextLong();
        this.text = text;
        this.senderId = senderId;
        this.submissionDateTime = System.currentTimeMillis();
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText( String text ) {
        this.text = text;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId( long senderId ) {
        this.senderId = senderId;
    }

    public long getSubmissionDateTime() {
        return submissionDateTime;
    }

    public void setSubmissionDateTime( long submissionDateTime ) {
        this.submissionDateTime = submissionDateTime;
    }

    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        PublicMessage that = (PublicMessage)o;
        return id == that.id &&
                senderId == that.senderId &&
                submissionDateTime == that.submissionDateTime &&
                Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, senderId, submissionDateTime);
    }
}


