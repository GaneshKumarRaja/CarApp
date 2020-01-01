package com.ganesh.domain.repository;


import com.ganesh.domain.model.CarDetailsDomainModel;
import com.ganesh.domain.model.CarsLocationDomainModel;

import java.util.List;

import io.reactivex.Observable;

public interface CarsLocationRepository {
    public Observable<List<CarsLocationDomainModel>> getLocationList();

    public Observable<CarDetailsDomainModel> getCarDetails(String carID);
}
