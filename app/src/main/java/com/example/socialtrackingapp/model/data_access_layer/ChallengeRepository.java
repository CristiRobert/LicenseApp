package com.example.socialtrackingapp.model.data_access_layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.MutableLiveData;

import com.example.socialtrackingapp.model.business_logic_layer.BitmapToBytesConverter;
import com.example.socialtrackingapp.model.entities.Challenge;
import com.example.socialtrackingapp.model.entities.ChallengeType;
import com.example.socialtrackingapp.model.entities.Sport;
import com.example.socialtrackingapp.model.entities.SportType;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChallengeRepository {

    private static ChallengeRepository challengeRepository;
    private final int ONE_MEGA_BYTE = 1024 * 1024;
    private final Firebase firebase;
    private final FirebaseStorage storage;

    private ChallengeRepository() {
        firebase = new Firebase("https://socialtrackingapp-a4737-default-rtdb.firebaseio" +
                                        ".com/Challenges");
        storage = FirebaseStorage.getInstance("gs://socialtrackingapp-a4737.appspot.com");
    }

    public static synchronized ChallengeRepository getInstance() {
        if(challengeRepository == null) {
            challengeRepository = new ChallengeRepository();
        }
        return challengeRepository;
    }

    public MutableLiveData<List<Challenge>> getAllValidChallenges() {
        MutableLiveData<List<Challenge>> mutableChallenges = new MutableLiveData<>();
        LocalDate currentDate = LocalDate.now();
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                if(dataSnapshot.exists()) {
                    List<Challenge> challenges = new ArrayList<>();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Challenge challenge = snapshot.getValue(Challenge.class);
                        if(!challenge.isDeleted() && challenge.isValid()) {
                            challenges.add(challenge);
                            setChallengeImage(challenge, mutableChallenges);
                            setChallengeSportImage(challenge, mutableChallenges);
                        }
                    }
                    mutableChallenges.setValue(challenges);
                } else {
                    mutableChallenges.setValue(null);
                }
            }

            @Override
            public void onCancelled( FirebaseError firebaseError ) {

            }
        });
        return mutableChallenges;
    }

    public MutableLiveData<List<Challenge>> getChallengesBySportAndType(
            SportType sportType,
            ChallengeType type
                                                                       ) {
        MutableLiveData<List<Challenge>> mutableChallenges = new MutableLiveData<>();
        Query query = firebase.orderByChild("challengeType").equalTo(type.toString());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                List<Challenge> challenges = new ArrayList<>();
                if(dataSnapshot.exists()) {
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Challenge challenge = snapshot.getValue(Challenge.class);
                        if(challenge.isValid() &&
                                ( challenge.getSport().getSportType() == SportType.NONE || challenge.getSport().getSportType() == sportType )
                        ) {
                            challenges.add(challenge);
                            setChallengeSportImage(challenge, mutableChallenges);
                            setChallengeSportImage(challenge, mutableChallenges);
                        }
                    }
                    mutableChallenges.setValue(challenges);
                } else {
                    mutableChallenges.setValue(mutableChallenges.getValue());
                }
            }

            @Override
            public void onCancelled( FirebaseError firebaseError ) {

            }
        });
        return mutableChallenges;
    }

    public void saveChallenge( Challenge challenge ) {
        firebase.child(String.valueOf(challenge.getId())).setValue(challenge);
        storage.getReference("Challenges").child(String.valueOf(challenge.getId())).child(
                "ChallengeImage")
               .putBytes(BitmapToBytesConverter.convert(challenge.getImage()));
    }

    public void deleteChallenge( Challenge challenge ) {
        challenge.setDeleted(true);
        firebase.child(String.valueOf(challenge.getId())).setValue(challenge);
    }

    private void setChallengeImage(
            Challenge challenge,
            MutableLiveData<List<Challenge>> mutableChallenges
                                  ) {
        storage.getReference("Challenges").child(String.valueOf(challenge.getId())).child("Image")
               .getBytes(ONE_MEGA_BYTE).addOnSuccessListener(bytes -> {
            Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            challenge.setImage(image);
            mutableChallenges.setValue(mutableChallenges.getValue());
        });
    }

    private void setChallengeSportImage(
            Challenge challenge,
            MutableLiveData<List<Challenge>> mutableChallenges
                                       ) {
        Sport sport = challenge.getSport();
        storage.getReference("SportImages").child(sport.getSportType().convertToString())
               .getBytes(ONE_MEGA_BYTE).addOnSuccessListener(bytes -> {
            Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            sport.setImage(image);
            mutableChallenges.setValue(mutableChallenges.getValue());
        });
    }
}
