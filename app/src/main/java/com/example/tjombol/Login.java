package com.example.tjombol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                if (checkDataEntered()) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }
        });

        String text = "<font color=#ffffff>Don't have an account? </font> <font color=#67d6d3>Sign Up</font>";
        goToSignupButton.setText(Html.fromHtml(text));

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean checkDataEntered() {
        boolean ableToLogIn = true;
        if (!isEmail(email)) {
            email.setError("Enter a valid email!");
            ableToLogIn = false;
        }
        if (isEmpty(password)) {
            password.setError("Please enter your password!");
            ableToLogIn = false;
        }
        return ableToLogIn;
    }
}
