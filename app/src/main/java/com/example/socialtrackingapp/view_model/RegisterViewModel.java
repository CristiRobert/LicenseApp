package com.example.socialtrackingapp.view_model;

import android.app.DatePickerDialog;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.EditText;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.InverseBindingAdapter;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.ViewModel;

import com.example.socialtrackingapp.model.entities.Country;
import com.example.socialtrackingapp.model.entities.Gender;
import com.example.socialtrackingapp.model.entities.User;

import java.util.Observable;

public class RegisterViewModel extends ViewModel {

    private User user;

    public void init()
    {
        user = new User();
    }

    public User getUser()
    {
        return user;
    }
}
