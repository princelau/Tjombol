package com.example.tjombol;


import android.app.Activity;
import android.app.Application;

import com.example.tjombol.Dagg.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class TjombolApp extends Application implements HasActivityInjector {

    private static TjombolApp sInstance;
    public static TjombolApp getAppContext() {
        return sInstance;
    }
    private static synchronized void setInstance(TjombolApp app) {
        sInstance = app;
    }
    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
        setInstance(this);
    }

    private void initializeComponent() {

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingInjector;
    }
}
