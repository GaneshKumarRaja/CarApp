package com.ganesh.domain.usecases;

import io.reactivex.observers.DisposableObserver;

public interface CarBookingUseCase<T> {
    public void createObservableUseCase(DisposableObserver<T> disposableObserver, Integer carID);
}
