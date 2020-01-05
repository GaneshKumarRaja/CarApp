package com.ganesh.myapplication.presentation.booking;

import androidx.lifecycle.MutableLiveData;

import com.ganesh.domain.model.CarBookingDomainModel;
import com.ganesh.domain.model.CarDetailsDomainModel;
import com.ganesh.domain.usecases.CarBookingUseCase;
import com.ganesh.domain.usecases.CarUseCase;
import com.ganesh.myapplication.mapper.CarDetailsDataMapper;
import com.ganesh.myapplication.model.CarDetailsData;
import com.ganesh.myapplication.base.BaseViewModel;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class CarBookingViewModel extends BaseViewModel {
    private CarBookingUseCase carBookingUseCase;
    private CarUseCase carDetailsUseCase;
    private CarDetailsDataMapper mapper;
    private Integer carID;
    private final MutableLiveData<CarDetailsData> carDetailsDataMutableLiveData;


    @Inject
    public CarBookingViewModel(
            CarBookingUseCase carBookingUseCase,
            CarUseCase carDetailsUseCase,
            CarDetailsDataMapper mapper) {
        this.carBookingUseCase = carBookingUseCase;
        this.carDetailsUseCase = carDetailsUseCase;
        this.mapper = mapper;
        carDetailsDataMutableLiveData = new MutableLiveData<>();
    }


    @SuppressWarnings("unchecked")
    void getCarDetails() {
        canShowLoading.postValue(true);

        carDetailsUseCase.carDetailsByID(new DisposableObserver<CarDetailsDomainModel>() {
            @Override
            public void onNext(CarDetailsDomainModel carDetailsDomainModel) {
                CarDetailsData result = mapper.mapFrom(carDetailsDomainModel);
                System.out.println(result);
                carDetailsDataMutableLiveData.postValue(result);
            }

            @Override
            public void onError(Throwable e) {
                handleError(e);
            }

            @Override
            public void onComplete() {
                canShowLoading.postValue(false);
            }
        }, carID);
    }


    void bookCar() {
        canShowLoading.postValue(true);

        HashMap<String, Integer> carInfoInput = new HashMap<>();
        carInfoInput.put("carId", carID);

        carBookingUseCase.bookCar(new DisposableObserver<CarBookingDomainModel>() {
            @Override
            public void onNext(CarBookingDomainModel carsLocationDomainModel) {
                errorMessage.postValue(carsLocationDomainModel.getMessage() == null ?
                        "Booked Successfully" :
                        carsLocationDomainModel.getMessage());
            }

            @Override
            public void onError(Throwable e) {
                handleError(e);
            }

            @Override
            public void onComplete() {
                canShowLoading.postValue(false);
            }
        }, carInfoInput);


    }

    private void handleError(Throwable e) {
        errorMessage.postValue(e != null ? e.getMessage() : "");
    }

    MutableLiveData<CarDetailsData> getCarDetailsDataMutableLiveData() {
        return carDetailsDataMutableLiveData;
    }

    void setCarID(Integer carID) {
        this.carID = carID;
    }
}
