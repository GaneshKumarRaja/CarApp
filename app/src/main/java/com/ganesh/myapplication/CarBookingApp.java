package com.ganesh.myapplication;

import android.app.Activity;
import android.app.Application;


import com.ganesh.myapplication.di.components.DaggerAppComponent;
import com.ganesh.myapplication.di.module.AppModule;
import com.google.android.gms.maps.MapsInitializer;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class CarBookingApp extends Application implements HasActivityInjector {
    private static CarBookingApp sInstance;

    private static synchronized void setInstance(CarBookingApp app) {
        sInstance = app;
    }

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        MapsInitializer.initialize(this);
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
