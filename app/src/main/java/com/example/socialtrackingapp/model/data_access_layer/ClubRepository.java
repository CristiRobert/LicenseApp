package com.example.socialtrackingapp.model.data_access_layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.MutableLiveData;

import com.example.socialtrackingapp.model.business_logic_layer.BitmapToBytesConverter;
import com.example.socialtrackingapp.model.entities.Club;
import com.example.socialtrackingapp.model.entities.Country;
import com.example.socialtrackingapp.model.entities.Sport;
import com.example.socialtrackingapp.model.entities.SportType;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ClubRepository {

    private static ClubRepository clubRepository;
    private final int ONE_MEGA_BYTE = 1024 * 1024;
    private final Firebase firebase;
    private final FirebaseStorage storage;

    private ClubRepository() {
        firebase = new Firebase("https://socialtrackingapp-a4737-default-rtdb.firebaseio" +
                                        ".com/Clubs");
        storage = FirebaseStorage.getInstance("gs://socialtrackingapp-a4737.appspot.com");
    }

    public synchronized static ClubRepository getInstance() {
        if(clubRepository == null) {
            clubRepository = new ClubRepository();
        }
        return clubRepository;
    }

    public MutableLiveData<List<Club>> getAllClubs() {
        MutableLiveData<List<Club>> mutableClubs = new MutableLiveData<>();
        Query query = firebase.orderByChild("isActive").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                if(dataSnapshot.exists()) {
                    List<Club> clubs = new ArrayList<>();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Club club = snapshot.getValue(Club.class);
                        if(!club.isDeleted()) {
                            clubs.add(club);
                            setClubProfileImage(club, mutableClubs);
                            setClubSportImage(club, mutableClubs);
                        }
                    }
                    mutableClubs.setValue(clubs);
                } else {
                    mutableClubs.setValue(null);
                }
            }

            @Override
            public void onCancelled( FirebaseError firebaseError ) {

            }
        });
        return mutableClubs;
    }

    public MutableLiveData<List<Club>> getClubsByNameSportCityAndCountry(
            String name,
            SportType sportType, String city, Country country
                                                                        ) {
        MutableLiveData<List<Club>> mutableClubs = new MutableLiveData<>();
        Query query = firebase.orderByChild("isActive").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                if(dataSnapshot.exists()) {
                    List<Club> clubs = new ArrayList<>();
                    String nameToLowerCase = name.toLowerCase();
                    String cityToLowerCase = city.toLowerCase();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Club club = snapshot.getValue(Club.class);
                        if(
                                ( !club.isDeleted() ) &&
                                        ( name.isEmpty() || club.getName().toLowerCase().contains(nameToLowerCase) ) &&
                                        ( sportType == SportType.NONE || club.getSport().getSportType() == sportType ) &&
                                        ( city.isEmpty() || club.getCity().toLowerCase().contains(cityToLowerCase) ) &&
                                        ( country == Country.NONE || club.getCountry() == country )
                        ) {
                            clubs.add(club);
                            setClubSportImage(club, mutableClubs);
                            setClubProfileImage(club, mutableClubs);
                        }
                    }
                    mutableClubs.setValue(clubs);
                } else {
                    mutableClubs.setValue(null);
                }
            }

            @Override
            public void onCancelled( FirebaseError firebaseError ) {

            }
        });
        return mutableClubs;
    }

    public void saveClub( Club club ) {
        firebase.child(String.valueOf(club.getId())).setValue(club);
        storage.getReference("Clubs").child(String.valueOf(club.getId())).child("ProfileImage")
               .putBytes(BitmapToBytesConverter.convert(club.getImage()));
    }

    public void deleteClub( Club club ) {
        club.setDeleted(true);
        firebase.child(String.valueOf(club.getId())).setValue(club);
    }

    private void setClubProfileImage( Club club, MutableLiveData<List<Club>> mutableClubs ) {
        storage.getReference("Clubs").child(String.valueOf(club.getId()))
               .child("ProfileImage").getBytes(ONE_MEGA_BYTE).addOnSuccessListener(bytes -> {
            Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            club.setImage(image);
            mutableClubs.setValue(mutableClubs.getValue());
        });
    }

    private void setClubSportImage( Club club, MutableLiveData<List<Club>> mutableClubs ) {
        Sport sport = club.getSport();
        storage.getReference("SportImages").child(sport.getSportType().convertToString()).getBytes(ONE_MEGA_BYTE).addOnSuccessListener(bytes -> {
            Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            club.getSport().setImage(image);
            mutableClubs.setValue(mutableClubs.getValue());
        });
    }

}
