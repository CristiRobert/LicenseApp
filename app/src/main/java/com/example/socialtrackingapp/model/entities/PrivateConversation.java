package com.example.socialtrackingapp.model.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class PrivateConversation implements Serializable {

    private long id;
    private long user1Id;
    private long user2Id;
    private List<PrivateMessage> messageList;

    public PrivateConversation( long user1Id, long user2Id ) {
        this.id = new Random().nextLong();
        this.user1Id = user1Id;
        this.user2Id = user2Id;
    }

    public PrivateConversation() {}

    public long getId() {
        return id;
    }

    public void setId( long id ) {
        this.id = id;
    }

    public long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id( long user1Id ) {
        this.user1Id = user1Id;
    }

    public long getUser2Id() {
        return user2Id;
    }

    public void setUser2Id( long user2Id ) {
        this.user2Id = user2Id;
    }

    public List<PrivateMessage> getMessageList() {
        return messageList;
    }

    public void setMessageList( List<PrivateMessage> messageList ) {
        this.messageList = messageList;
    }

    @Override
    public boolean equals( Object o ) {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        PrivateConversation that = (PrivateConversation)o;
        return id == that.id &&
                user1Id == that.user1Id &&
                user2Id == that.user2Id &&
                Objects.equals(messageList, that.messageList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user1Id, user2Id, messageList);
    }
}
