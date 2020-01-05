package com.ganesh.domain.usecases;

import io.reactivex.observers.DisposableObserver;

public interface CarUseCase<T> {
    void carsList(DisposableObserver<T> disposableObserver);

    void carDetailsByID(DisposableObserver<T> disposableObserver, Integer carID);

    void dispose();
}
