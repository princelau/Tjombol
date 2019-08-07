package com.example.tjombol.Dagg.builder;

import com.example.tjombol.views.HomeFragment;
import com.example.tjombol.views.RecentTransactionsFragment;
import com.example.tjombol.views.TransactionListFragment;
import com.example.tjombol.views.UpcomingSalaryFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
//* This builder provides android injector service to fragments

@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract TransactionListFragment contributeTransactionListFragment();

    /*
    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract ArticleDetailFragment contributeArticleDetailFragment();
    */
    @ContributesAndroidInjector
    abstract UpcomingSalaryFragment contributeUpcomingSalaryFragment();

    @ContributesAndroidInjector
    abstract RecentTransactionsFragment contributeRecentTransactionsFragment();

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();
}

