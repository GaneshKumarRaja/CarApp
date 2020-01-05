package com.ganesh.domain.usecases;


import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

@SuppressWarnings("unchecked")
public class CarsUseCaseImpl implements CarUseCase {
    private CarLocationUseCase locationUseCase;
    private CarDetailsUseCase carDetailsUseCase;

    @Inject
    public CarsUseCaseImpl(CarLocationUseCase locationUseCase, CarDetailsUseCase carDetailsUseCase) {
        this.locationUseCase = locationUseCase;
        this.carDetailsUseCase = carDetailsUseCase;
    }

    @Override
    public void carsList(DisposableObserver disposableObserver) {
        locationUseCase.execute(disposableObserver, null);
    }

    @Override
    public void carDetailsByID(DisposableObserver disposableObserver, Integer carID) {
        carDetailsUseCase.execute(disposableObserver,carID);
    }

    @Override
    public void dispose() {
        locationUseCase.dispose();
        carDetailsUseCase.dispose();
    }
}
