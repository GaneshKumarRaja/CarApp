package com.ganesh.domain.usecases;

import com.ganesh.domain.model.CarBookingDomainModel;
import com.ganesh.domain.model.CarsLocationDomainModel;

import java.util.HashMap;

import io.reactivex.observers.DisposableObserver;

public interface CarBookingUseCase {
    void bookCar(DisposableObserver<CarBookingDomainModel> disposableObserver, HashMap<String, Integer> carInfo);
}
