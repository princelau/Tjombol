package com.example.tjombol.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.tjombol.R;
import com.example.tjombol.remote.Status;
import com.example.tjombol.viewModels.LoginViewModel;
import com.example.tjombol.viewModels.RegisterViewModel;

public class SignupActivity extends FragmentActivity implements SignupStep1Fragment.OnDataPass, SignupStep2Fragment.OnDataPass2, SignupStep3Fragment.OnDataPass3, SignupStep4Fragment.OnDataPass4 {

    private static final int NUM_PAGES = 5;
    private static final String TAG = "RegisterView";
    private RegisterViewModel registerViewModel;

    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    private String nric;
    private String bankAccountNumber;
    private String companyId;
    private String paymentScheme;
    private final String scheme = "flatline";
    //private String companyPassword;
    private String monthlySalary;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_main);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.nonSwipeViewPager);
        pagerAdapter = new MyPageAdapter(getSupportFragmentManager());
        mPager.setAdapter(pagerAdapter);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        registerViewModel.init();

        //Transfer to MainActivity upon valid response
        registerViewModel.getRegisterDataObservable(null,null,null,null,null,null,null,null, null).observe(this, result -> {
            //loginMainBinding.progess.setVisibility(View.GONE);
            if(result!=null && result.status == Status.SUCCESS && result.data !=null) {
                Log.d(TAG, "onCreate: result status: "+result.status);
                //saveUserLoginData(result.data);
                //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //startActivity(intent);
                Toast.makeText(getApplicationContext(), String.format("Registration success, token:%s",result.data.getToken()), Toast.LENGTH_SHORT).show();
            } else{
                if(result!=null && result.status == Status.INITIALIZING){
                    Log.d(TAG, "onCreate: result: "+result.getMessage());
                }else {
                    Log.d(TAG, "onCreate: shit happens "+result.getMessage());
                    Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class MyPageAdapter extends FragmentPagerAdapter {
        public MyPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment selectedFragment = null;

            switch (position) {
                case 0:
                    selectedFragment = new SignupStep1Fragment();
                    break;
                case 1:
                    selectedFragment = new SignupStep2Fragment();
                    break;
                case 2:
                    selectedFragment = new SignupStep3Fragment();
                    break;
                case 3:
                    selectedFragment = new SignupStep4Fragment();
                    break;

                    default: selectedFragment = new SignupStep1Fragment();

            }
            return selectedFragment;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onDataPass(String[] data) {
        name = data[0];
        email = data[1];
        password = data[2];
        Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDataPass2(String[] data) {
        mobileNumber = data[0];
        nric = data[1];
        bankAccountNumber = data[2];
        companyId = data[3];
        Log.d(TAG, "onDataPass2: Registering");
    }

    @Override
    public void onDataPass3(String[] data) {
        paymentScheme = data[0];
        Log.d(TAG, "onDataPass3: Registering");
    }

    @Override
    public void onDataPass4(String[] data) {
        monthlySalary = data[0];
        /*Log.i("name",name);
        Log.i("email",email);
        Log.i("name",password);
        Log.i("name",mobileNumber);
        Log.i("name",nric);
        Log.i("name",bankAccountNumber);
        Log.i("name",companyId);
        Log.i("name",monthlySalary);*/
        Log.d(TAG, "onDataPass4: Registering");
        registerViewModel.getRegisterDataObservable(companyId,nric,name,email,bankAccountNumber,mobileNumber,scheme,password, monthlySalary);

    }

}
