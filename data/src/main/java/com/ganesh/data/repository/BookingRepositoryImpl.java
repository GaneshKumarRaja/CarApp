package com.ganesh.data.repository;

import com.ganesh.data.datasource.CarBookingHttpClient;
import com.ganesh.domain.model.CarBookingDomainModel;
import com.ganesh.domain.repository.CarBookingRepository;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;


public class BookingRepositoryImpl implements CarBookingRepository {
    private CarBookingHttpClient client;

    @Inject
    public BookingRepositoryImpl(CarBookingHttpClient client) {
        this.client = client;
    }


    @Override
    public Observable<CarBookingDomainModel> doCarBooking(HashMap<String, Integer> params) {
        return client.carBooking("Bearer df7c313b47b7ef87c64c0f5f5cebd6086bbb0fa",params);
    }
}
