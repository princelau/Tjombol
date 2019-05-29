package com.example.tjombol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.Arrays;

public class SignupActivity extends FragmentActivity implements SignupStep1Fragment.OnDataPass, SignupStep2Fragment.OnDataPass2, SignupStep3Fragment.OnDataPass3, SignupStep4Fragment.OnDataPass4 {

    private static final int NUM_PAGES = 5;

    private String name;
    private String email;
    private String password;
    private String mobileNumber;
    private String nric;
    private String bankAccountNumber;
    private String companyId;
    private String paymentScheme;
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
    }

    @Override
    public void onDataPass2(String[] data) {
        mobileNumber = data[0];
        nric = data[1];
        bankAccountNumber = data[2];
        companyId = data[3];
    }

    @Override
    public void onDataPass3(String[] data) {
        paymentScheme = data[0];
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

    }

}
