package com.ganesh.myapplication.view.carlist;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.ganesh.myapplication.R;
import com.ganesh.myapplication.databinding.FragmentCarsListMapBinding;
import com.ganesh.myapplication.model.MarkerModel;
import com.ganesh.myapplication.view.base.BaseMapMarkerFragment;

import java.util.List;

import javax.inject.Inject;


public class CarsListMapFragment extends BaseMapMarkerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CarLocationViewModel viewModel;

    private FragmentCarsListMapBinding binding;

    public CarsListMapFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (binding == null) {
            binding = FragmentCarsListMapBinding.inflate(inflater, container, false);
            setUpMap(savedInstanceState, binding.map);
            setUpViewModelObserver();
        } else {
            ((ViewGroup) container.getParent()).removeView(binding.getRoot());
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /**
     * when the map is ready to add markers
     */
    @Override
    protected void onMapReady() {
        // collect car details from viewmoel
        viewModel.getMarkerData();

    }

    /**
     * when user click second time on a marker, this method is invoked
     */
    @Override
    protected void onMarkerSelected() {
        NavHostFragment.findNavController(this).navigate(R.id.nav_booking_fragment);
    }


    /**
     * observers for viewmodel's liveData
     */
    private void setUpViewModelObserver() {

        viewModel.markerLiveData.observe(getActivity(), new Observer<List<MarkerModel>>() {
            @Override
            public void onChanged(List<MarkerModel> markerModels) {
                addMarker(markerModels);
            }
        });
    }


}
