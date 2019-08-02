package com.example.tjombol.Dagg.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.tjombol.viewModels.HomeFragmentViewModel;
import com.example.tjombol.viewModels.TransactionListViewModel;
import com.example.tjombol.viewModels.ViewModelFactory;
import com.example.tjombol.views.HomeFragment;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

/**
 * Alloes us to inject dependencies via constructor injection
 * <p>
 * Author: Lajesh D
 * Email: lajeshds2007@gmail.com
 * Created: 7/24/2018
 * Modified: 7/24/2018
 */
@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(TransactionListViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindsTransactionListViewModel(TransactionListViewModel transactionListViewModel);

    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
