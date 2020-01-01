package com.ganesh.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;


import com.ganesh.myapplication.di.components.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class CarBookingApp extends Application implements HasActivityInjector {
    private static CarBookingApp sInstance;


    public static CarBookingApp getAppContext() {
        return sInstance;
    }


    private static synchronized void setInstance(CarBookingApp app) {
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
