package com.ganesh.myapplication.di.module;


import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ganesh.myapplication.di.builder.ViewModelFactory;
import com.ganesh.myapplication.view.booking.CarBookingViewModel;
import com.ganesh.myapplication.view.carlist.CarLocationViewModel;

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
    @ViewModelKey(CarLocationViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindCarLocation(CarLocationViewModel carLocationViewModel);


    @Binds
    @IntoMap
    @ViewModelKey(CarBookingViewModel.class)
    @SuppressWarnings("unused")
    abstract ViewModel bindBooking(CarBookingViewModel carBookingViewModel);


    //
    @Binds
    @SuppressWarnings("unused")
    abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelFactory viewModelFactory);
}
