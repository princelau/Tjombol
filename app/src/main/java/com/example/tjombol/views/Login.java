package com.example.tjombol.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.tjombol.R;
import com.example.tjombol.viewModels.LoginViewModel;
import com.example.tjombol.databinding.LoginMainBinding;

public class Login extends AppCompatActivity{

    private LoginViewModel loginViewModel;
    private LoginMainBinding loginMainBinding;
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginMainBinding = DataBindingUtil.setContentView(this, R.layout.login_main);
        loginViewModel = new LoginViewModel(this);
        loginMainBinding.setLoginview(loginViewModel);
        loginMainBinding.forgotPasswordButton.setOnClickListener(v -> {

        });

        loginMainBinding.setPresenter(new LoginPresenter() {
            @Override
            public void loginData() {
                final String name = loginMainBinding.emailLoginEditText.getText().toString();
                final String pass = loginMainBinding.passwordLoginEditText.getText().toString();
                if (checkDataEntered()) {
                    Log.d(TAG, "loginDataCheck: email&name checked, sending request");
                    loginViewModel.sendLoginRequest(name, pass);
                }else{
                    showToast("checkdata failed");
                    Log.d(TAG,"loginDataCheck: check failed");
                }
            }

            @Override
            public void onClickSignup() {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });

        String text = "<font color=#ffffff>Don't have an account? </font> <font color=#67d6d3>Sign Up</font>";
        loginMainBinding.goToSignupButton.setText(Html.fromHtml(text));

    }

    private boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    private boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private boolean checkDataEntered() {
        boolean ableToLogIn = true;
        if (!isEmail(loginMainBinding.emailLoginEditText)) {
            loginMainBinding.emailLoginEditText.setError("Enter a valid email!");
            ableToLogIn = false;
        }
        if (isEmpty(loginMainBinding.passwordLoginEditText)) {
            loginMainBinding.passwordLoginEditText.setError("Please enter your password!");
            ableToLogIn = false;
        }
        return ableToLogIn;
    }

    void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}
