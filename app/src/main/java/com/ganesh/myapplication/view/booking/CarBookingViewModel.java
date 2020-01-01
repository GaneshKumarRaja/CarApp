package com.ganesh.myapplication.view.booking;

import androidx.lifecycle.MutableLiveData;

import com.ganesh.domain.model.CarBookingDomainModel;
import com.ganesh.domain.usecases.CarBookingUseCase;
import com.ganesh.myapplication.mapper.CarDetailsDataMapper;
import com.ganesh.myapplication.model.CarDetailsData;
import com.ganesh.myapplication.view.base.BaseViewModel;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

public class CarBookingViewModel extends BaseViewModel {
    private CarBookingUseCase locationUseCases;
    private CarDetailsDataMapper mapper;

    private final MutableLiveData<CarDetailsData> carDetailsDataMutableLiveData;

    @Inject
    public CarBookingViewModel(CarBookingUseCase useCase, CarDetailsDataMapper mapper) {
        this.locationUseCases = useCase;
        this.mapper = mapper;
        carDetailsDataMutableLiveData = new MutableLiveData<>();
    }

    void getCarDetails() {
        canShowLoading.postValue(true);

        locationUseCases.createObservableUseCase(new DisposableObserver<CarBookingDomainModel>() {
            @Override
            public void onNext(CarBookingDomainModel o) {
                System.out.println(o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        }, 16);

//        locationUseCases.obsetvaableCarDetails(new DisposableObserver<CarDetailsDomainModel>() {
//            @Override
//            public void onNext(CarDetailsDomainModel carDetailsDomainModel) {
//                CarDetailsData result = mapper.mapFrom(carDetailsDomainModel);
//                System.out.println(result);
//                carDetailsDataMutableLiveData.postValue(result);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                errorMessage.postValue(e != null ? e.getMessage() : "");
//            }
//
//            @Override
//            public void onComplete() {
//                canShowLoading.postValue(false);
//            }
//        }, "1");
    }

    public MutableLiveData<CarDetailsData> getCarDetailsDataMutableLiveData() {
        return carDetailsDataMutableLiveData;
    }
}
