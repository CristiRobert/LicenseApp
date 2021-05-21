package com.example.socialtrackingapp.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

public class PrivateMessage implements Serializable {

    private long id;
    private long senderId;
    private long consigneeId;
    private String text;
    private long submissionDateTime;
    private PrivateMessageState privateMessageState;

    public PrivateMessage(
            long senderId, long consigneeId, String text,
            PrivateMessageState privateMessageState
                         ) {
        this.senderId = senderId;
        this.consigneeId = consigneeId;
        this.text = text;
        this.submissionDateTime = System.currentTimeMillis();
        this.privateMessageState = privateMessageState;
    }

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public long getSenderId() {
        return senderId;
    }

    public void setSenderId( long senderId ) {
        this.senderId = senderId;
    }

    public long getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId( long consigneeId ) {
        this.consigneeId = consigneeId;
    }

    public String getText() {
        return text;
    }

    public void setText( String text ) {
        this.text = text;
    }

    public long getSubmissionDateTime() {
        return submissionDateTime;
    }

    public void setSubmissionDateTime( long submissionDateTime ) {
        this.submissionDateTime = submissionDateTime;
    }

    public PrivateMessageState getPrivateMessageState() {
        return privateMessageState;
    }

    @JsonIgnore
    public void setPrivateMessageState( PrivateMessageState privateMessageState ) {
        this.privateMessageState = privateMessageState;
    }

    public void setPrivateMessageState( String privateMessageStateAsString ) throws IllegalArgumentException {
        this.privateMessageState = PrivateMessageState.valueOf(privateMessageStateAsString);
    }

    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        PrivateMessage that = (PrivateMessage)o;
        return id == that.id &&
                senderId == that.senderId &&
                consigneeId == that.consigneeId &&
                submissionDateTime == that.submissionDateTime &&
                Objects.equals(text, that.text) &&
                privateMessageState == that.privateMessageState;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderId, consigneeId, text, submissionDateTime,
                            privateMessageState);
    }
}
