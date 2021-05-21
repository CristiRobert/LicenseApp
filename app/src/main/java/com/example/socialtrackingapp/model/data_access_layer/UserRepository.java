package com.example.socialtrackingapp.model.data_access_layer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.MutableLiveData;

import com.example.socialtrackingapp.model.business_logic_layer.BitmapToBytesConverter;
import com.example.socialtrackingapp.model.entities.Activity;
import com.example.socialtrackingapp.model.entities.Notification;
import com.example.socialtrackingapp.model.entities.Sport;
import com.example.socialtrackingapp.model.entities.User;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserRepository {

    private static UserRepository userRepository;
    private final int ONE_MEGA_BYTE = 1024 * 1024;
    private final Firebase firebase;
    private final FirebaseStorage storage;

    private UserRepository() {
        firebase = new Firebase("https://socialtrackingapp-a4737-default-rtdb.firebaseio" +
                                        ".com/Users");
        storage = FirebaseStorage.getInstance("gs://socialtrackingapp-a4737.appspot.com");
    }

    public synchronized static UserRepository getInstance() {
        if(userRepository == null) {
            userRepository = new UserRepository();
        }
        return userRepository;
    }

    public MutableLiveData<List<User>> getAllUsers() {
        MutableLiveData<List<User>> mutableUsers = new MutableLiveData<>();
        Query query = firebase.orderByChild("deleted").equalTo(false);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                if(dataSnapshot.exists()) {
                    List<User> users = new ArrayList<>();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        setUserProfileImage(user, mutableUsers);
                        setUserActivitySportImages(user, mutableUsers);
                        setUserActivitySportImages(user, mutableUsers);
                        setUserNotificationImages(user, mutableUsers);
                        users.add(user);
                    }
                    mutableUsers.setValue(users);
                    LocalDate.now().toEpochDay();

                } else {
                    mutableUsers.setValue(null);
                }
            }

            @Override
            public void onCancelled( FirebaseError firebaseError ) {

            }
        });
        return mutableUsers;
    }

    public MutableLiveData<User> findUserByEmailAndPassword( String email, String password ) {
        MutableLiveData<User> mutableUser = new MutableLiveData<>();
        Query query = firebase.orderByChild("isActive").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                if(dataSnapshot.exists()) {
                    Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
                    DataSnapshot snapshot = iterator.next();
                    User user = snapshot.getValue(User.class);
                    if(user.getEmail().equals(email) && user.getPassword().equals(password)) {
                        mutableUser.setValue(user);
                        setUserProfileImage(user, mutableUser);
                        setUserActivityImages(user, mutableUser);
                        setUserActivitySportImages(user, mutableUser);
                        setUserNotificationImages(user, mutableUser);
                    }
                } else {
                    mutableUser.setValue(null);
                }
            }

            @Override
            public void onCancelled( FirebaseError firebaseError ) {

            }
        });
        return mutableUser;
    }

    public MutableLiveData<List<User>> getUsersWhichContainsText( String text ) {
        MutableLiveData<List<User>> mutableUsers = new MutableLiveData<>();
        Query query = firebase.orderByChild("isActive").equalTo(true);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange( DataSnapshot dataSnapshot ) {
                if(dataSnapshot.exists()) {
                    List<User> users = new ArrayList<>();
                    for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User user = snapshot.getValue(User.class);
                        if(user.getFullName().toLowerCase().contains(text.toLowerCase())) {
                            users.add(user);
                            setUserProfileImage(user, mutableUsers);
                            setUserActivityImages(user, mutableUsers);
                            setUserActivitySportImages(user, mutableUsers);
                            setUserNotificationImages(user, mutableUsers);
                        }
                    }
                    mutableUsers.setValue(users);
                } else {
                    mutableUsers.setValue(null);
                }
            }

            @Override
            public void onCancelled( FirebaseError firebaseError ) {

            }
        });
        return mutableUsers;
    }

    public void saveUser( User user ) {
        firebase.child(String.valueOf(user.getId())).setValue(user);
        storage.getReference("Users").child(String.valueOf(user.getId())).child("ProfileImage")
               .putBytes(BitmapToBytesConverter.convert(user.getProfileImage()));
        for(Activity activity : user.getActivityList()) {
            storage.getReference("Users").child(String.valueOf(user.getId())).child(
                    "ActivityImages")
                   .child(String.valueOf(activity.getId())).putBytes(BitmapToBytesConverter.convert(activity.getImage()));
        }
        for(Notification notification : user.getNotificationList()) {
            storage.getReference("Users").child(String.valueOf(user.getId())).child(
                    "NotificationImages")
                   .child(String.valueOf(notification.getId())).putBytes(BitmapToBytesConverter.convert(notification.getImage()));
        }
    }

    public void deleteUser( User user ) {

        user.setDeleted(true);
        firebase.child(String.valueOf(user.getId())).setValue(user);
    }

    private <T> void setUserProfileImage( User user, MutableLiveData<T> mutableObject ) {
        storage.getReference("Users").child(String.valueOf(user.getId())).child("ProfileImage").getBytes(ONE_MEGA_BYTE).addOnSuccessListener(bytes -> {
            Bitmap profileImage = BitmapFactory.decodeByteArray(bytes, 0,
                                                                bytes.length
                                                               );
            user.setProfileImage(profileImage);
            mutableObject.setValue(mutableObject.getValue());
        });
    }

    private <T> void setUserActivityImages( User user, MutableLiveData<T> mutableObject ) {
        for(Activity activity : user.getActivityList()) {
            storage.getReference("Users").child(String.valueOf(user.getId()))
                   .child("ActivityImage").child(String.valueOf(activity.getId()))
                   .getBytes(ONE_MEGA_BYTE).addOnSuccessListener(bytes -> {
                Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                activity.setImage(image);
                mutableObject.setValue(mutableObject.getValue());
            });
        }
    }

    private <T> void setUserActivitySportImages( User user, MutableLiveData<T> mutableObject ) {
        for(Activity activity : user.getActivityList()) {
            Sport sport = activity.getSport();
            storage.getReference("SportsImages").child(sport.getSportType().convertToString()).getBytes(ONE_MEGA_BYTE).addOnSuccessListener(bytes -> {
                Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                sport.setImage(image);
                mutableObject.setValue(mutableObject.getValue());
            });
        }
    }

    private <T> void setUserNotificationImages( User user, MutableLiveData<T> mutableObject ) {
        for(Notification notification : user.getNotificationList()) {
            storage.getReference("Users").child(String.valueOf(user.getId()))
                   .child("NotificationImages").child(String.valueOf(notification.getId()))
                   .getBytes(ONE_MEGA_BYTE).addOnSuccessListener(bytes -> {
                Bitmap image = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                notification.setImage(image);
                mutableObject.setValue(mutableObject.getValue());
            });
        }
    }
}
