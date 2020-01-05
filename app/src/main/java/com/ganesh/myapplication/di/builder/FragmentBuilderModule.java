package com.ganesh.myapplication.di.builder;


import com.ganesh.myapplication.presentation.booking.BookingFragment;
import com.ganesh.myapplication.presentation.carlist.CarsListMapFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentBuilderModule {

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract CarsListMapFragment contributeListFragment();

    @SuppressWarnings("unused")
    @ContributesAndroidInjector
    abstract BookingFragment contributeBookingFragment();


}
