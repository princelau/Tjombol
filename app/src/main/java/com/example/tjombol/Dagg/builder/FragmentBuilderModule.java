package com.example.tjombol.Dagg.builder;

import com.example.tjombol.Views.TransactionListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


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
}
