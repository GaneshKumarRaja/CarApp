package com.ganesh.myapplication.presentation.booking;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ganesh.myapplication.binding.AdapterCallback;
import com.ganesh.myapplication.databinding.FragmentBookingBinding;
import com.ganesh.myapplication.model.CarDetailsData;
import com.ganesh.myapplication.base.BaseFragment;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends BaseFragment implements AdapterCallback {

    private FragmentBookingBinding binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CarBookingViewModel viewModel;

    @Inject
    CarDetailsAdapter adapter;

    public BookingFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id;

        if (getArguments() != null) {
            id = getArguments().getString("carID");
            if (id != null) viewModel.setCarID(Integer.parseInt(id));
        }

        setUpObserver();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (binding == null) {
            binding = FragmentBookingBinding.inflate(inflater, container, false);
            binding.setLifecycleOwner(this);
            binding.setAdapter(adapter);
            binding.setCallback(this);
            viewModel.getCarDetails();
        } else {
            ((ViewGroup) container.getParent()).removeView(binding.getRoot());
        }

        return binding.getRoot();
    }


    private void setUpObserver() {
        viewModel.getCarDetailsDataMutableLiveData().observe(this, this::onChanged);

        viewModel.getErrorMessage().observe(this, this::onChanged);

        viewModel.getCanShowLoading().observe(this, aBoolean -> binding.setVisibilites(aBoolean));

    }

    @Override
    public void onBookingClikced() {
        viewModel.bookCar();
    }

    private void onChanged(CarDetailsData carDetailsData) {
        binding.setParentVisibilites(true);
        if (carDetailsData.imageURL != null) binding.setImageURL(carDetailsData.imageURL);
        binding.getAdapter().updateData(carDetailsData.carDtatLits);
    }

    private void onChanged(String e) {
        if (e != null)
            showMessage(e);

        binding.setParentVisibilites(false);
    }


}
