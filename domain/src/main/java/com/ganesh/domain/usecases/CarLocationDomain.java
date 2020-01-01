package com.ganesh.domain.usecases;


import com.ganesh.domain.repository.CarsLocationRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CarLocationDomain<T> extends UseCase implements CarsLocationUseCase<T> {
    CarsLocationRepository repository;

    public CarLocationDomain(CarsLocationRepository repository) {
        super(Schedulers.newThread(), AndroidSchedulers.mainThread());
        this.repository = repository;
    }

    @Override
    public void createObservableUseCase(DisposableObserver<T> disposableObserver) {
        execute(disposableObserver, repository.getLocationList());
    }

    @Override
    public void obsetvaableCarDetails(DisposableObserver<T> disposableObserver, String carID) {
        execute(disposableObserver, repository.getCarDetails(carID));
    }
}
