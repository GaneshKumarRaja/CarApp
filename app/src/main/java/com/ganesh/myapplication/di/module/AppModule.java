package com.ganesh.myapplication.di.module;

import android.app.Application;

import com.ganesh.data.datasource.CarBookingHttpClient;
import com.ganesh.data.datasource.HttpClient;
import com.ganesh.data.repository.BookingRepositoryImpl;
import com.ganesh.data.repository.CarsLocationRepositoryImpl;

import com.ganesh.domain.repository.CarBookingRepository;
import com.ganesh.domain.repository.CarsLocationRepository;
import com.ganesh.domain.usecases.CarBookingUseCase;
import com.ganesh.domain.usecases.CarBookingUseCaseImpl;
import com.ganesh.domain.usecases.CarDetailsUseCase;
import com.ganesh.domain.usecases.CarLocationUseCase;
import com.ganesh.domain.usecases.CarsUseCaseImpl;
import com.ganesh.domain.usecases.CarUseCase;

import com.ganesh.myapplication.BuildConfig;
import com.ganesh.myapplication.mapper.CarDetailsDataMapper;
import com.ganesh.myapplication.mapper.DomainToAppDataMapper;

import com.ganesh.myapplication.presentation.booking.CarDetailsAdapter;
import com.google.android.gms.location.LocationRequest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The application module which provides app wide instances of various components
 */
@Module(includes = ViewModelModule.class)
public class AppModule {
//    Application app;
//
//    public AppModule(Application app) {
//        this.app = app;
//    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    HttpClient provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_CAR_INFO)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(HttpClient.class);
    }

    @Provides
    @Singleton
    CarBookingHttpClient provideCarBookingRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.URL_BOOKING)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(CarBookingHttpClient.class);
    }


    @Provides
    @Singleton
    CarsLocationRepository provideCarsRepo(HttpClient client) {
        return new CarsLocationRepositoryImpl(client);
    }

    @Provides
    @Singleton
    CarUseCase provideLocationUseCases(CarLocationUseCase locationUseCase, CarDetailsUseCase detailsUseCase) {
        return new CarsUseCaseImpl(locationUseCase, detailsUseCase);
    }

    @Provides
    @Singleton
    CarBookingRepository provideBookingClient(CarBookingHttpClient repo) {
        return new BookingRepositoryImpl(repo);
    }


    @Provides
    @Singleton
    DomainToAppDataMapper provideDomainToAppDataMapper() {
        return new DomainToAppDataMapper();
    }


    @Provides
    @Singleton
    CarDetailsDataMapper provideDomainToCarDetailsData() {
        return new CarDetailsDataMapper();
    }


    @Provides
    @Singleton
    CarDetailsUseCase provideCarDetailsUseCase(CarsLocationRepository repository) {
        return new CarDetailsUseCase(repository);
    }


    @Provides
    @Singleton
    CarLocationUseCase provideCarLocationUseCase(CarsLocationRepository repository) {
        return new CarLocationUseCase(repository);
    }

    @Provides
    @Singleton
    CarBookingUseCase provideCarBookingUseCase(CarBookingRepository repository) {
        return new CarBookingUseCaseImpl(repository);
    }


    @Provides
    @Singleton
    CarDetailsAdapter adapter() {
        return new CarDetailsAdapter();
    }

    @Provides
    @Singleton
    LocationRequest provideLocationRequest() {
        return LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
                .setInterval(1000);
    }


    @Provides
    @Singleton
    ReactiveLocationProvider providerReactiveLocationProvider(Application app) {
        return new ReactiveLocationProvider(app);
    }

}
