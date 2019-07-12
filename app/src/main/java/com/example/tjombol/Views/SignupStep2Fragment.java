package com.example.tjombol.Views;

<<<<<<< HEAD:app/src/main/java/com/example/tjombol/Views/SignupStep2Fragment.java
import android.os.Bundle;

=======
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
>>>>>>> master:app/src/main/java/com/example/tjombol/SignupStep2Fragment.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.tjombol.R;

public class SignupStep2Fragment extends Fragment {


    Button signup2Button;
    Button backButtonSignup2;
    EditText mobileNumberSignupEditText;
    EditText nricSignupEditText;
    EditText bankNumberSignupEditText;
    EditText companyIdSignupEditText;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.fragment_signup_step2, container, false);

        mobileNumberSignupEditText = view.findViewById(R.id.mobileNumberSignupEditText);
        nricSignupEditText = view.findViewById(R.id.nricSignupEditText);
        bankNumberSignupEditText = view.findViewById(R.id.bankNumberSignupEditText);
        companyIdSignupEditText = view.findViewById(R.id.companyIdSignupEditText);

        signup2Button = view.findViewById(R.id.signup2Button);
        signup2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkDataEntered()) {
                    String[] dataArray = new String[] {mobileNumberSignupEditText.getText().toString(),nricSignupEditText.getText().toString(), bankNumberSignupEditText.getText().toString(), companyIdSignupEditText.getText().toString()};
                    dataPasser.onDataPass2(dataArray);
                    ViewPager vp = getActivity().findViewById(R.id.nonSwipeViewPager);
                    vp.setCurrentItem(vp.getCurrentItem() + 1);
                }
            }
        });

        backButtonSignup2 = view.findViewById(R.id.backButtonSignup2);
        backButtonSignup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewPager vp = getActivity().findViewById(R.id.nonSwipeViewPager);
                vp.setCurrentItem(vp.getCurrentItem()-1);
            }
        });




        return view;
    }




    OnDataPass2 dataPasser;

    public interface OnDataPass2 {
        public void onDataPass2(String[] data);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (OnDataPass2) context;
    }



    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isMobileNumber(EditText text) {
        CharSequence mobileNumber = text.getText().toString();
        return (android.util.Patterns.PHONE.matcher(mobileNumber).matches() && !isEmpty(text));
    }

    boolean isDigits(EditText text) {
        String digitString = text.getText().toString();
        return (digitString.matches("[0-9]+") && !isEmpty(text));
    }

    boolean checkDataEntered() {
        boolean ableToLogIn = true;

        if (!isMobileNumber(mobileNumberSignupEditText)) {
            mobileNumberSignupEditText.setError("Please enter a valid mobile number!");
            ableToLogIn = false;
        }

        if (isEmpty(nricSignupEditText)) {
            nricSignupEditText.setError("Please enter a valid NRIC");
            ableToLogIn = false;
        }

        if (!isDigits(bankNumberSignupEditText)) {
            bankNumberSignupEditText.setError("Please enter a valid bank account number!");
            ableToLogIn = false;
        }

        if (!isDigits(companyIdSignupEditText)) {
            companyIdSignupEditText.setError("Please enter a valid company ID!");
            ableToLogIn = false;
        }
        return ableToLogIn;
    }
}

