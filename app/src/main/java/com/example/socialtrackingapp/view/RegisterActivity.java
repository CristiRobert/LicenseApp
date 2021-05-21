package com.example.socialtrackingapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.socialtrackingapp.R;
import com.example.socialtrackingapp.databinding.ActivityRegisterBinding;
import com.example.socialtrackingapp.model.entities.Activity;
import com.example.socialtrackingapp.view_model.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;
    private EditText firstName;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        registerViewModel = new RegisterViewModel();
        binding.setRegisterViewModel(registerViewModel);
        firstName = findViewById(R.id.firstName);
    }


}