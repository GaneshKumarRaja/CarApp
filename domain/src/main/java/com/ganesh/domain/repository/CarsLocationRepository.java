package com.ganesh.domain.repository;


import com.ganesh.domain.model.CarDetailsDomainModel;
import com.ganesh.domain.model.CarsLocationDomainModel;

import java.util.List;

import io.reactivex.Observable;

public interface CarsLocationRepository  {
     Observable<List<CarsLocationDomainModel>> getLocationList();

     Observable<CarDetailsDomainModel> getCarDetails(Integer carID);
}
