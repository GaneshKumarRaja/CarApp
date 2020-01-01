package com.ganesh.data.datasource;

import com.ganesh.domain.model.CarBookingDomainModel;

import java.util.HashMap;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CarBookingHttpClient {
    @POST("wunderfleet-recruiting-mobile-dev-quick-rental")
    public Observable<CarBookingDomainModel> carBooking(
            @Header("Authorization") String token,
            @Body HashMap<String, Integer> carID);
}
