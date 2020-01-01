package com.ganesh.myapplication.view.booking;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ganesh.myapplication.R;
import com.ganesh.myapplication.databinding.FragmentBookingBinding;
import com.ganesh.myapplication.databinding.FragmentCarsListMapBinding;
import com.ganesh.myapplication.model.CarDetailsData;
import com.ganesh.myapplication.view.base.BaseFragment;
import com.ganesh.myapplication.view.carlist.CarLocationViewModel;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookingFragment extends BaseFragment implements AdapterCallback {

    FragmentBookingBinding binding;
    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CarBookingViewModel viewModel;

    @Inject
    CarDetailsAdapter adapter;

    public BookingFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentBookingBinding.inflate(inflater, container, false);
            //setUpMap(savedInstanceState, binding.map);
            binding.setLifecycleOwner(this);
            binding.setAdapter(adapter);
            binding.setCallback(this);
            setUpObserver();
            viewModel.getCarDetails();
        } else {
            ((ViewGroup) container.getParent()).removeView(binding.getRoot());
        }

        return binding.getRoot();
    }


    private void setUpObserver() {
        viewModel.getCarDetailsDataMutableLiveData().observe(this, new Observer<CarDetailsData>() {
            @Override
            public void onChanged(CarDetailsData carDetailsData) {
                binding.getAdapter().updateData(carDetailsData.carDtatLits);
                Picasso.with(getActivity()).load(carDetailsData.imageURL).into(binding.imgCarImage);
            }
        });
    }


    @Override
    public void onBookingClikced() {
        System.out.println("zczczc");
    }
}
