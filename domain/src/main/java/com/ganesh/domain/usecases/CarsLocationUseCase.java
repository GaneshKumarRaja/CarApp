package com.ganesh.domain.usecases;

import io.reactivex.observers.DisposableObserver;

public interface CarsLocationUseCase<T> {
    public void createObservableUseCase(DisposableObserver<T> disposableObserver);
    public void obsetvaableCarDetails(DisposableObserver<T> disposableObserver,String carID);
    public void dispose();
}
