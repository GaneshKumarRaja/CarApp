package com.ganesh.data.repository;


import com.ganesh.data.datasource.HttpClient;
import com.ganesh.domain.model.CarDetailsDomainModel;
import com.ganesh.domain.model.CarsLocationDomainModel;
import com.ganesh.domain.repository.CarsLocationRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


public class CarsLocationRepositoryImpl implements CarsLocationRepository {
    private HttpClient client;


    @Inject
    public CarsLocationRepositoryImpl(HttpClient client) {
        this.client = client;
    }

    @Override
    public Observable<List<CarsLocationDomainModel>> getLocationList() {
        return client.getCarsList();
    }

    @Override
    public Observable<CarDetailsDomainModel> getCarDetails(Integer carID) {
        return client.carDetailsByID(carID);
    }

}
