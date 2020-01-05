package com.ganesh.domain.usecases;
//
// Created by Ganesh on 2020-01-05.
//


import com.ganesh.domain.model.CarDetailsDomainModel;
import com.ganesh.domain.model.CarsLocationDomainModel;
import com.ganesh.domain.repository.CarsLocationRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@SuppressWarnings("unchecked")
public class CarDetailsUseCase extends UseCase<CarDetailsDomainModel, Integer> {
    private CarsLocationRepository repository;

    @Inject
    public CarDetailsUseCase(CarsLocationRepository repository) {
        super(Schedulers.newThread(), AndroidSchedulers.mainThread());
        this.repository = repository;
    }

    @Override
    Observable<CarDetailsDomainModel> buildUseCaseObservable(Integer id) {
        return repository.getCarDetails(id);
    }
}


