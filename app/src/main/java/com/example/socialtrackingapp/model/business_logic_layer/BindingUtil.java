package com.example.socialtrackingapp.model.business_logic_layer;

import androidx.databinding.InverseMethod;

import com.example.socialtrackingapp.R;
import com.example.socialtrackingapp.model.entities.Country;
import com.example.socialtrackingapp.model.entities.Gender;


public class BindingUtil {

    @InverseMethod("buttonIdToGender")
    public static int genderToButtonId( Gender gender )
    {
        return gender == Gender.MALE ? R.id.male : R.id.female;
    }

    public static Gender buttonIdToGender(int buttonId)
    {
        return buttonId == R.id.male ? Gender.MALE : Gender.FEMALE;
    }

    @
    public static int countryToPosition( Country country )
    {
        return country.ordinal();
    }

    public static Country positionToCountry(int position)
    {
        return Country.values()[position];
    }
}
