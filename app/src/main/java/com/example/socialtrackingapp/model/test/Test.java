/*package com.example.socialtrackingapp.model.test;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.socialtrackingapp.R;
import com.example.socialtrackingapp.model.data_access_layer.UserRepository;
import com.example.socialtrackingapp.model.entities.Country;
import com.example.socialtrackingapp.model.entities.Gender;
import com.example.socialtrackingapp.model.entities.User;
import com.firebase.client.Firebase;

import java.time.LocalDate;
import java.util.List;

public class Test extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Firebase.setAndroidContext(this);
        UserRepository userRepository = UserRepository.getInstance();
        User user = new User("Cristian", "Seretan", "cris90robert@yahoo.com", "biblioteca3",
                             LocalDate.now(), Gender.MALE, "Sfantu Gheorghe", Country.ALBANIA, null
        );
        userRepository.saveUser(user);
        MutableLiveData<List<User>> users = userRepository.getAllUsers();
        users.observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged( List<User> users ) {
                if(users == null) {
                    Log.e("Cristian", "Null");
                } else {
                    Log.e("Cristian", String.valueOf(users.size()));
                }
            }
        });

    }
}*/