package com.example.socialtrackingapp.model.data_access_layer;

import androidx.lifecycle.MutableLiveData;

import com.example.socialtrackingapp.model.entities.PrivateConversation;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

public class PrivateConversationRepository {

    private static PrivateConversationRepository privateConversationRepository;
    private final int ONE_MEGA_BYTE = 1024 * 1024;
    private final Firebase firebase;
    private final FirebaseStorage storage;

    private PrivateConversationRepository() {
        firebase = new Firebase("https://socialtrackingapp-a4737-default-rtdb.firebaseio" +
                                        ".com/Conversations");
        storage = FirebaseStorage.getInstance("gs://socialtrackingapp-a4737.appspot.com");
    }

    public static synchronized PrivateConversationRepository getInstance() {
        if(privateConversationRepository == null) {
            privateConversationRepository = new PrivateConversationRepository();
        }
        return privateConversationRepository;
    }

    public MutableLiveData<PrivateConversation> getConversationByUserIds(
            long user1Id,
            long user2Id
                                                                        ) {
        MutableLiveData<PrivateConversation> mutableConversation = new MutableLiveData<>();
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        PrivateConversation conversation =
                                snapshot.getValue(PrivateConversation.class);
                        if(( conversation.getUser1Id() == user1Id && conversation.getUser2Id() == user2Id ) ||
                                ( conversation.getUser1Id() == user2Id && conversation.getUser2Id() == user1Id )) {
                            mutableConversation.setValue(conversation);
                            break;
                        }
                    }
                } else {
                    mutableConversation.setValue(null);
                }
            }

            @Override
            public void onCancelled( FirebaseError firebaseError ) {

            }
        });
        return mutableConversation;
    }


}
