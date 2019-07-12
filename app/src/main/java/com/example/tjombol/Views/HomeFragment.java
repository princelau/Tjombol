package com.example.tjombol.Views;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
<<<<<<< HEAD:app/src/main/java/com/example/tjombol/Views/HomeFragment.java
=======
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
>>>>>>> master:app/src/main/java/com/example/tjombol/HomeFragment.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.tjombol.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    Button claimSalaryButton;

    private int progress = 10;
    private TextView progressbarPaidPercentage;
    private TextView progressbarPaidNumber;
    private ProgressBar progressBarPaid;

    private TextView progressbarDrawablePercentage;
    private TextView progressbarDrawableNumber;
    private ProgressBar progressBarDrawable;

    private TextView progressbarOutstandingPercentage;
    private TextView progressbarOutstandingNumber;
    private ProgressBar progressBarOutstanding;


    String amountWithdraw;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        setupViewPager(viewPager);


        // Set Tabs inside Toolbar
        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabLayout);
        tabs.setupWithViewPager(viewPager);


        progressBarPaid = view.findViewById (R.id.progressBarPaid);
        progressbarPaidPercentage = view.findViewById(R.id.progressBarPaidPercent);
        progressbarPaidNumber = view.findViewById(R.id.progressBarPaidNumber);
        String strProgress = String.valueOf(progress) + "%";
        progressbarPaidPercentage.setText(strProgress);
        progressbarPaidNumber.setText("500/1000");
        ObjectAnimator animationPaid = ObjectAnimator.ofInt(progressBarPaid, "progress", 0, 100); // see this max value coming back here, we animate towards that value
        animationPaid.setDuration(5000); // in milliseconds
        animationPaid.setInterpolator(new DecelerateInterpolator());
        animationPaid.start();

        progressBarDrawable = view.findViewById (R.id.progressBarDrawable);
        progressbarDrawablePercentage = view.findViewById(R.id.progressBarDrawablePercent);
        progressbarDrawableNumber = view.findViewById(R.id.progressBarDrawableNumber);
        progressbarDrawablePercentage.setText(strProgress);
        progressbarDrawableNumber.setText("500/1000");
        ObjectAnimator animationDrawable = ObjectAnimator.ofInt(progressBarDrawable, "progress", 0, 500); // see this max value coming back here, we animate towards that value
        animationDrawable.setDuration(5000); // in milliseconds
        animationDrawable.setInterpolator(new DecelerateInterpolator());
        animationDrawable.start();

        progressBarOutstanding = view.findViewById (R.id.progressBarOutstanding);
        progressbarOutstandingPercentage = view.findViewById(R.id.progressBarOutstandingPercent);
        progressbarOutstandingNumber = view.findViewById(R.id.progressBarOutstandingNumber);
        progressbarOutstandingPercentage.setText(strProgress);
        progressbarOutstandingNumber.setText("500/1000");
        ObjectAnimator animationOutstanding = ObjectAnimator.ofInt(progressBarOutstanding, "progress", 0, 500); // see this max value coming back here, we animate towards that value
        animationOutstanding.setDuration(5000); // in milliseconds
        animationOutstanding.setInterpolator(new DecelerateInterpolator());
        animationOutstanding.start();

        claimSalaryButton = view.findViewById(R.id.claimSalaryButton);
        claimSalaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Enter the amount you wish to withdraw");
                View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.text_input_password, (ViewGroup) getView(), false);
                final EditText amountWithdrawEditText = (EditText) viewInflated.findViewById(R.id.amountWithdrawEditText);
                builder.setView(viewInflated);



                builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(amountWithdrawEditText.getText().toString())) {
                            Toast.makeText(getActivity(),"Please enter a number",Toast.LENGTH_LONG).show();
                        }
                        else if (amountWithdrawEditText.getText().toString().startsWith("0") || amountWithdrawEditText.getText().toString().contains(".")) {
                            Toast.makeText(getActivity(),"Please enter a valid whole number",Toast.LENGTH_LONG).show();
                        }
                        else {
                            dialog.dismiss();
                            amountWithdraw = amountWithdrawEditText.getText().toString();
                            FragmentManager fragmentManager = getFragmentManager();
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            ClaimSalaryFragment claimSalaryFragment = new ClaimSalaryFragment();
                            Bundle bundle = new Bundle();
                            bundle.putString("amountWithdraw", amountWithdraw);
                            claimSalaryFragment.setArguments(bundle);
                            fragmentTransaction.replace(R.id.fragment_container, claimSalaryFragment);
                            fragmentTransaction.commit();
                        }
                    }
                });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();

            }
        });

        return view;
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }



    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new RecentTransactionsFragment(), "Recent Transactions");
        adapter.addFragment(new UpcomingSalaryFragment(), "Upcoming Salary");
        viewPager.setAdapter(adapter);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
