package com.ganesh.domain.repository;

import com.ganesh.domain.model.CarBookingDomainModel;

import java.util.HashMap;

import io.reactivex.Observable;

public interface CarBookingRepository {
     Observable<CarBookingDomainModel> doCarBooking(HashMap<String, Integer> params);
}
