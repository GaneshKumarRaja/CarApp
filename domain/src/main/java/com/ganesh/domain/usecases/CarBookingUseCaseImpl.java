package com.ganesh.domain.usecases;

import com.ganesh.domain.repository.CarBookingRepository;
import com.ganesh.domain.repository.CarsLocationRepository;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CarBookingUseCaseImpl<T> extends UseCase implements CarBookingUseCase<T> {

    CarBookingRepository repository;

    public CarBookingUseCaseImpl(CarBookingRepository repository) {
        super(Schedulers.newThread(), AndroidSchedulers.mainThread());
        this.repository = repository;
    }

    @Override
    public void createObservableUseCase(DisposableObserver disposableObserver, Integer carID) {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("carId", carID);
        execute(disposableObserver,
                repository.doCarBooking(map));
    }
}
