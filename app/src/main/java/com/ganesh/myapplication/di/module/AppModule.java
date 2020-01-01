package com.ganesh.myapplication.di.module;

import com.ganesh.data.datasource.CarBookingHttpClient;
import com.ganesh.data.datasource.HttpClient;
import com.ganesh.data.repository.BookingRepositoryImpl;
import com.ganesh.data.repository.CarsLocationRepositoryImpl;

import com.ganesh.domain.repository.CarBookingRepository;
import com.ganesh.domain.repository.CarsLocationRepository;
import com.ganesh.domain.usecases.CarBookingUseCase;
import com.ganesh.domain.usecases.CarBookingUseCaseImpl;
import com.ganesh.domain.usecases.CarLocationDomain;
import com.ganesh.domain.usecases.CarsLocationUseCase;

import com.ganesh.myapplication.mapper.CarDetailsDataMapper;
import com.ganesh.myapplication.mapper.DomainToAppDataMapper;

import com.ganesh.myapplication.view.booking.CarDetailsAdapter;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The application module which provides app wide instances of various components
 */
@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        // okHttpClient.connectTimeout(ApiConstants.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
        //okHttpClient.readTimeout(ApiConstants.READ_TIMEOUT, TimeUnit.MILLISECONDS);
        //okHttpClient.writeTimeout(ApiConstants.WRITE_TIMEOUT, TimeUnit.MILLISECONDS);
        //okHttpClient.addInterceptor(new RequestInterceptor());
        okHttpClient.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY));
        return okHttpClient.build();
    }

    //
    @Provides
    @Singleton
    HttpClient provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://s3.eu-central-1.amazonaws.com/wunderfleet-recruiting-dev/")
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
                .baseUrl("https://4i96gtjfia.execute-api.eu-central-1.amazonaws.com/default/")
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
    CarsLocationUseCase provideLocationUseCases(CarsLocationRepository repo) {
        return new CarLocationDomain(repo);
    }

    @Provides
    @Singleton
    CarBookingRepository provideBookingClient(CarBookingHttpClient repo) {
        return new BookingRepositoryImpl(repo);
    }

    @Provides
    @Singleton
    CarBookingUseCase provideBookingUseCases(CarBookingRepository repo) {
        return new CarBookingUseCaseImpl(repo);
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
    CarDetailsAdapter adapter() {
        return new CarDetailsAdapter();
    }

}
