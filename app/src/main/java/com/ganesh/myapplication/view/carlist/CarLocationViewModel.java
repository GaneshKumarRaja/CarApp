package com.ganesh.myapplication.view.carlist;


import androidx.lifecycle.MutableLiveData;

import com.ganesh.domain.model.CarsLocationDomainModel;
import com.ganesh.domain.usecases.CarsLocationUseCase;
import com.ganesh.myapplication.mapper.DomainToAppDataMapper;
import com.ganesh.myapplication.model.MarkerModel;
import com.ganesh.myapplication.view.base.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


import io.reactivex.observers.DisposableObserver;

public class CarLocationViewModel extends BaseViewModel {

    private CarsLocationUseCase locationUseCases;
    private DomainToAppDataMapper mapper;

    public MutableLiveData<List<MarkerModel>> markerLiveData = new MutableLiveData();


    @Inject
    public CarLocationViewModel(CarsLocationUseCase useCase, DomainToAppDataMapper mapper) {
        this.locationUseCases = useCase;
        this.mapper = mapper;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        locationUseCases.dispose();
    }

    void getMarkerData() {
        canShowLoading.postValue(true);

        locationUseCases.createObservableUseCase(new DisposableObserver<List<CarsLocationDomainModel>>() {
            @Override
            public void onNext(List<CarsLocationDomainModel> carsLocationModels) {
                handleResult(carsLocationModels);
            }

            @Override
            public void onError(Throwable e) {
                handleError(e);
            }

            @Override
            public void onComplete() {
                canShowLoading.postValue(true);
            }
        });


    }

    private void handleResult(List<CarsLocationDomainModel> domainModelList) {


        List<MarkerModel> appModelList = new ArrayList<>();

        for (CarsLocationDomainModel domainModel : domainModelList) {
            appModelList.add(mapper.mapFrom(domainModel));
        }


        markerLiveData.postValue(appModelList);
    }

    private void handleError(Throwable e) {
        if (e != null)
            errorMessage.postValue(e.getMessage());
    }
}
