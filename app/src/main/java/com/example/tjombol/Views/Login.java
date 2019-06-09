package com.example.tjombol.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tjombol.R;

public class Login extends AppCompatActivity {

    Button loginButton;
    Button forgotPasswordButton;
    Button goToSignupButton;
    EditText email;
    EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        forgotPasswordButton = findViewById(R.id.forgotPasswordButton);
        goToSignupButton = findViewById(R.id.goToSignupButton);
        email = findViewById(R.id.emailLoginEditText);
        password = findViewById(R.id.passwordLoginEditText);

        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        goToSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        loginButton  = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        String text = "<font color=#ffffff>Don't have an account? </font> <font color=#67d6d3>Sign Up</font>";
        goToSignupButton.setText(Html.fromHtml(text));

    }
}