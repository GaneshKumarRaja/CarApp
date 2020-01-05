package com.ganesh.data.datasource;

import com.ganesh.domain.model.CarDetailsDomainModel;
import com.ganesh.domain.model.CarsLocationDomainModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HttpClient {

    @GET("cars.json")
    public Observable<List<CarsLocationDomainModel>> getCarsList();

    @GET("cars/{id}")
    public Observable<CarDetailsDomainModel> carDetailsByID(@Path("id") Integer id);


}
