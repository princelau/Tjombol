package com.example.tjombol.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tjombol.R;

public class ProfileFragment extends Fragment {

    Button updateEmailButton;
    Button changePasswordButton;
    Button changePaymentSchemeButton;
    Button updateDetailsButton;
    Button changeCompanyButton;
    Button changeSalaryButton;
    Button viewTermsAndConditionsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_profile, container, false);


        updateEmailButton = view.findViewById(R.id.updateEmailButton);
        changePasswordButton = view.findViewById(R.id.changePasswordButton);
        changePaymentSchemeButton = view.findViewById(R.id.changePaymentSchemeButton);
        updateDetailsButton = view.findViewById(R.id.updateDetailsButton);
        changeCompanyButton = view.findViewById(R.id.changeCompanyButton);
        changeSalaryButton = view.findViewById(R.id.changeSalaryButton);
        viewTermsAndConditionsButton = view.findViewById(R.id.termsAndConditionsButton);


        updateEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeEmail.class);
                startActivity(intent);
            }
        });

        changePasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangePassword.class);
                startActivity(intent);
            }
        });


        updateDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ChangeDetails.class);
                startActivity(intent);
            }
        });





        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
