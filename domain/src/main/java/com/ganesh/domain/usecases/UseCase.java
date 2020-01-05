package com.ganesh.domain.usecases;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;


abstract class UseCase<T, Params> {
    private final CompositeDisposable compositeDisposable;
    private final Scheduler executorThread;
    private final Scheduler uiThread;

    UseCase(Scheduler executorThread, Scheduler uiThread) {
        this.executorThread = executorThread;
        this.uiThread = uiThread;
        compositeDisposable = new CompositeDisposable();
    }

    abstract Observable<T> buildUseCaseObservable(Params params);

    public void execute(DisposableObserver<T> disposableObserver
            , Params params
    ) {
        Observable<T> ons = this.buildUseCaseObservable(params).subscribeOn(executorThread)
                .observeOn(uiThread);
        DisposableObserver observer = ons.subscribeWith(disposableObserver);
        compositeDisposable.add(observer);
    }


    public void dispose() {
        if (!compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
    }


}