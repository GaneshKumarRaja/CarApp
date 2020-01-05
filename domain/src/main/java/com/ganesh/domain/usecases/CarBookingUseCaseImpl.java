package com.ganesh.domain.usecases;

import com.ganesh.domain.model.CarBookingDomainModel;
import com.ganesh.domain.model.CarsLocationDomainModel;
import com.ganesh.domain.repository.CarBookingRepository;
import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class CarBookingUseCaseImpl extends UseCase<CarBookingDomainModel, HashMap<String, Integer>>
        implements CarBookingUseCase {

    private CarBookingRepository repository;

    @Inject
    public CarBookingUseCaseImpl(CarBookingRepository repository) {
        super(Schedulers.newThread(), AndroidSchedulers.mainThread());
        this.repository = repository;
    }

    @Override
    Observable<CarBookingDomainModel> buildUseCaseObservable(HashMap<String, Integer> map) {
        return repository.doCarBooking(map);
    }

    @Override
    public void bookCar(DisposableObserver<CarBookingDomainModel> disposableObserver, HashMap<String, Integer> carInfo) {
        execute(disposableObserver,carInfo);
    }
}
