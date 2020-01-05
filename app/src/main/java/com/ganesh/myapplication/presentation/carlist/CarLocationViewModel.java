package com.ganesh.myapplication.presentation.carlist;


import android.location.Location;

import androidx.lifecycle.MutableLiveData;

import com.ganesh.domain.model.CarsLocationDomainModel;
import com.ganesh.domain.usecases.CarUseCase;
import com.ganesh.myapplication.mapper.DomainToAppDataMapper;
import com.ganesh.myapplication.model.MarkerModel;
import com.ganesh.myapplication.base.BaseViewModel;
import com.google.android.gms.location.LocationRequest;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider;

public class CarLocationViewModel extends BaseViewModel {

    private CarUseCase useCase;
    private DomainToAppDataMapper mapper;
    private LocationRequest locationRequest;
    private ReactiveLocationProvider locationProvider;
    MutableLiveData<Location> locationMutableLiveData = new MutableLiveData<>();
    private Disposable locationProvideDisposel;
    private MutableLiveData<List<MarkerModel>> markerLiveData;


    @Inject
    public CarLocationViewModel(CarUseCase useCase,
                                DomainToAppDataMapper mapper,
                                LocationRequest locationRequest,
                                ReactiveLocationProvider locationProvider) {
        this.useCase = useCase;
        this.mapper = mapper;
        this.locationRequest = locationRequest;
        this.locationProvider = locationProvider;
        markerLiveData = new MutableLiveData<>();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        useCase.dispose();
        if (locationProvideDisposel != null)
            locationProvideDisposel.dispose();
    }

    @SuppressWarnings("unchecked")
    void getMarkerData() {
        canShowLoading.postValue(true);

        useCase.carsList(new DisposableObserver<List<CarsLocationDomainModel>>() {
            @Override
            public void onNext(List<CarsLocationDomainModel> carsLocationDomainModels) {
                handleResult(carsLocationDomainModels);
            }

            @Override
            public void onError(Throwable e) {
                handleError(e);
            }

            @Override
            public void onComplete() {

            }
        });


    }

    /**
     * handling result of cars details
     * @param domainModelList cars location
     */
    private void handleResult(List<CarsLocationDomainModel> domainModelList) {
        List<MarkerModel> appModelList = new ArrayList<>();

        for (CarsLocationDomainModel domainModel : domainModelList) {
            appModelList.add(mapper.mapFrom(domainModel));
        }

        markerLiveData.postValue(appModelList);
    }

    MutableLiveData<List<MarkerModel>> getMarkerLiveData() {
        return markerLiveData;
    }


    /**
     * hanndling error on fetching data from domain layer
     * @param e error message
     */
    private void handleError(Throwable e) {
        if (e != null)
            errorMessage.postValue(e.getMessage());
    }

    /**
     * starting the location update
     */
    void enableLocationUpdate() {
        locationProvideDisposel = locationProvider.getUpdatedLocation(locationRequest).subscribe(
                location -> locationMutableLiveData.postValue(location));
    }
}
