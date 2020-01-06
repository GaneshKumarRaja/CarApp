package com.ganesh.domain.usecases;
//
// Created by Ganesh on 2020-01-05.
//



import com.ganesh.domain.model.CarsLocationDomainModel;
import com.ganesh.domain.repository.CarsLocationRepository;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.Scheduler;



public class CarLocationUseCase extends UseCase<List<CarsLocationDomainModel>, Void> {

    private CarsLocationRepository repository;

    @Inject
    public CarLocationUseCase(Scheduler executorThread, Scheduler uiThread,CarsLocationRepository repository) {
        super(executorThread, uiThread);
        this.repository = repository;
    }


    @Override
    Observable<List<CarsLocationDomainModel>> buildUseCaseObservable(Void aVoid) {
        return repository.getLocationList();
    }
}
