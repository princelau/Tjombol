package com.example.tjombol.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tjombol.R;
import com.example.tjombol.remote.Models.LoginResponseModel;
import com.example.tjombol.remote.Status;
import com.example.tjombol.viewModels.LoginViewModel;
import com.example.tjombol.databinding.LoginMainBinding;

public class Login extends AppCompatActivity implements LoginPresenter{

    private static final String TAG = "LoginView";
    private static final String SHARED_PREFS = "user_data";
    private static final String TOKEN = "token";
    private static final String USER_ACCOUNT = "rAccount";
    private LoginViewModel loginViewModel;
    private LoginMainBinding loginMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DataBinding
        String text = getString(R.string.sign_up_text);
        loginMainBinding = DataBindingUtil.setContentView(this, R.layout.login_main);
        loginMainBinding.setLoginPresenter(this);
        loginMainBinding.goToSignupButton.setText(Html.fromHtml(text));
        loginMainBinding.progess.setVisibility(View.GONE);
        //ViewModel Setup
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        loginViewModel.init();

        //Transfer to MainActivity upon valid response
        loginViewModel.getUserDataObservable(null,null).observe(this, result -> {
            loginMainBinding.progess.setVisibility(View.GONE);
            if(result!=null && result.status == Status.SUCCESS && result.data !=null) {
                Log.d(TAG, "onCreate: result status: "+result.status);
                saveUserLoginData(result.data);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            } else{
                if(result!=null && result.status == Status.INITIALIZING){
                    Log.d(TAG, "onCreate: result status: "+result.getMessage());
                }else {
                    Log.d(TAG, "onCreate: shit happens "+result.getMessage());
                    Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveUserLoginData(LoginResponseModel loginResponseModel){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN,loginResponseModel.getToken());
        editor.putString(USER_ACCOUNT,loginResponseModel.getUserAccount());
        editor.apply();
    }

    // OnClick Callbacks
    @Override
    public void onClickLogin() {
        Log.d(TAG, "onClickLogin: Clicked");
        String user_email = loginMainBinding.emailLoginEditText.getText().toString();
        String user_password = loginMainBinding.passwordLoginEditText.getText().toString();

        // Sending login request when email and password are all in correct format
        if(isEmail(user_email)){
            if(!isEmpty(user_password)) {
                loginMainBinding.progess.setVisibility(View.VISIBLE);
                loginViewModel.getUserDataObservable(user_email, user_password);
            }else{
                Log.d(TAG, "onClickLogin: empty password");
                Toast.makeText(getApplicationContext(),R.string.login_password_error , Toast.LENGTH_SHORT).show();
            }
        }else {
            Log.d(TAG, "onClickLogin: wrong email format");
            Toast.makeText(getApplicationContext(),R.string.login_email_error , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClickSignUp() {
        Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
        startActivity(intent);
    }

    // Email & Password Check
    private boolean isEmpty(String text) {
        return TextUtils.isEmpty(text);
    }
    private boolean isEmail(String text) {
        return (!TextUtils.isEmpty(text) && Patterns.EMAIL_ADDRESS.matcher(text).matches());
    }
}
