package com.ganesh.domain.usecases;
//
// Created by Ganesh on 2020-01-05.
//


import com.ganesh.domain.model.CarDetailsDomainModel;

import com.ganesh.domain.repository.CarsLocationRepository;



import javax.inject.Inject;

import io.reactivex.Observable;

import io.reactivex.Scheduler;


public class CarDetailsUseCase extends UseCase<CarDetailsDomainModel, Integer> {
    private CarsLocationRepository repository;

    @Inject
    public CarDetailsUseCase(Scheduler executorThread, Scheduler uiThread,CarsLocationRepository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }

    @Override
    Observable<CarDetailsDomainModel> buildUseCaseObservable(Integer id) {
        return repository.getCarDetails(id);
    }
}


