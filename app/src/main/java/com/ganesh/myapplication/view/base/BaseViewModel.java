package com.ganesh.myapplication.view.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
   protected MutableLiveData<String>errorMessage= new MutableLiveData<>();
    protected  MutableLiveData<Boolean>canShowLoading= new MutableLiveData<>();

    public MutableLiveData<Boolean> getCanShowLoading() {
        return canShowLoading;
    }

    public MutableLiveData<String> getErrorMessage() {
        return errorMessage;
    }
}
