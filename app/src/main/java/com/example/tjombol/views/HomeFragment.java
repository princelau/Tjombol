package com.example.tjombol.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.tjombol.Adapters.HomeAdapter;
import com.example.tjombol.R;
import com.example.tjombol.databinding.FragmentHomeBinding;
import com.example.tjombol.viewModels.HomeFragmentViewModel;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment View";
    private static final String SHARED_PREFS = "user_data";
    private static final String USER_ACCOUNT = "rAccount";
    private final int PROGRESSBAR_DURATION = 5000;
    private String amountWithdraw;
    private FragmentHomeBinding dataBinding;
    //private HomeFragmentViewModel homeFragmentViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        // Setup ViewPager
        final ViewPager viewPager = dataBinding.viewPager;
        setupViewPager(viewPager);
        // Set Tabs inside Toolbar
        dataBinding.tabLayout.setupWithViewPager(viewPager);
        // Get shared user account data from login activity - has problem
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("user_data",
                Context.MODE_PRIVATE);
        final String userAccount = sharedPreferences.getString(SHARED_PREFS,USER_ACCOUNT);
        // UI setup
        setupUI(sharedPreferences);
        dataBinding.claimSalaryButton.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Enter the amount you wish to withdraw");
            View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.text_input_password, (ViewGroup) getView(), false);
            final EditText amountWithdrawEditText = viewInflated.findViewById(R.id.amountWithdrawEditText);
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
                        FragmentTransaction fragmentTransaction = null;
                        if (fragmentManager != null) {
                            fragmentTransaction = fragmentManager.beginTransaction();
                        }
                        ClaimSalaryFragment claimSalaryFragment = new ClaimSalaryFragment();

                        // putting the tx amount into bundle and transfer to claim salary
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

        });

        // ViewModel
        //homeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        //homeFragmentViewModel.init();


        return dataBinding.getRoot();
    }

    /* Setups */
    // Initiate three progress bar in the main view
    private void setupUI(SharedPreferences sharedPreferences){
        /* ProgressBar Paid = money paid(green)/month salary */
        int progress = 10;
        String strProgress = progress + "%";
        dataBinding.progressBarPaidPercent.setText(strProgress);
        dataBinding.progressBarPaidNumber.setText(sharedPreferences.getString("mSalary", "")+"/"+ sharedPreferences.getString("wBalance", ""));
        ObjectAnimator animationPaid = ObjectAnimator.ofInt(dataBinding.progressBarPaid, "progress", 0, 100); // see this max value coming back here, we animate towards that value
        animationPaid.setDuration(PROGRESSBAR_DURATION); // in milliseconds
        animationPaid.setInterpolator(new DecelerateInterpolator());
        animationPaid.start();
        /* ProgressBar Drawable = requested(red num)/month salary*/
        dataBinding.progressBarDrawablePercent.setText(strProgress);
        dataBinding.progressBarDrawableNumber.setText(sharedPreferences.getString("mSalary", "")+"/"+ sharedPreferences.getString("wBalance", ""));
        ObjectAnimator animationDrawable = ObjectAnimator.ofInt(dataBinding.progressBarDrawable, "progress", 0, 500); // see this max value coming back here, we animate towards that value
        animationDrawable.setDuration(PROGRESSBAR_DURATION); // in milliseconds
        animationDrawable.setInterpolator(new DecelerateInterpolator());
        animationDrawable.start();
        /* ProgressBar Percentage = overtime (0/0) */
        dataBinding.progressBarOutstandingPercent.setText(strProgress);
        dataBinding.progressBarOutstandingNumber.setText(sharedPreferences.getString("mSalary", "")+"/"+ sharedPreferences.getString("wBalance", ""));
        ObjectAnimator animationOutstanding = ObjectAnimator.ofInt(dataBinding.progressBarOutstanding, "progress", 0, 500); // see this max value coming back here, we animate towards that value
        animationOutstanding.setDuration(PROGRESSBAR_DURATION); // in milliseconds
        animationOutstanding.setInterpolator(new DecelerateInterpolator());
        animationOutstanding.start();
    }
    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {
        HomeAdapter adapter = new HomeAdapter(getChildFragmentManager());
        adapter.addFragment(new RecentTransactionsFragment(), "Recent Transactions");
        adapter.addFragment(new UpcomingSalaryFragment(), "Upcoming Salary");
        viewPager.setAdapter(adapter);
    }
}
