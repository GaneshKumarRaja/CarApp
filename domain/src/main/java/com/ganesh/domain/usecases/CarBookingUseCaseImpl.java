package com.ganesh.domain.usecases;

import com.ganesh.domain.model.CarBookingDomainModel;

import com.ganesh.domain.repository.CarBookingRepository;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

import io.reactivex.observers.DisposableObserver;


public class CarBookingUseCaseImpl extends UseCase<CarBookingDomainModel, HashMap<String, Integer>>
        implements CarBookingUseCase {

    private CarBookingRepository repository;

    @Inject
    public CarBookingUseCaseImpl(Scheduler executorThread, Scheduler uiThread, CarBookingRepository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    Observable<CarBookingDomainModel> buildUseCaseObservable(HashMap<String, Integer> map) {
        return repository.doCarBooking(map);
    }

    @Override
    public void bookCar(DisposableObserver<CarBookingDomainModel> disposableObserver, HashMap<String, Integer> carInfo) {
        execute(disposableObserver, carInfo);
    }
}
