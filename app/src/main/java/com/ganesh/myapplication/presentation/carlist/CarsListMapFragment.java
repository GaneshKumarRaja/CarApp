package com.ganesh.myapplication.presentation.carlist;


import android.Manifest;

import android.content.pm.PackageManager;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;


import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.ganesh.myapplication.R;
import com.ganesh.myapplication.base.BaseMapMarkerFragment;
import com.ganesh.myapplication.databinding.FragmentCarsListMapBinding;
import com.ganesh.myapplication.model.MarkerModel;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;


public class CarsListMapFragment extends BaseMapMarkerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CarLocationViewModel viewModel;

    private FragmentCarsListMapBinding binding;

    private int REQUEST_CODE = 100;

    public CarsListMapFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpViewModelObserver();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (binding == null) {
            binding = FragmentCarsListMapBinding.inflate(inflater, container, false);
            checkPermissions();
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpMap(savedInstanceState, binding.map);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /**
     * when the map is ready to add markers
     */
    @Override
    public void onMapReady() {
        viewModel.getMarkerData();
    }

    /**
     * when user click second time on a marker, this method is invoked
     */
    @Override
    protected void onMarkerSelected(MarkerModel markerModel) {
        Bundle bundle = new Bundle();
        bundle.putString("carID", markerModel.carID.toString());
        NavHostFragment.findNavController(this).navigate(R.id.nav_booking_fragment, bundle);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            viewModel.enableLocationUpdate();
        }
    }

    /**
     * observers for viewmodel's liveData
     */
    private void setUpViewModelObserver() {

        viewModel.getMarkerLiveData().observe(this, this::addMarker);

        viewModel.locationMutableLiveData.observe(this, this::updateUserLocation);
    }


    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION);

        List<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_FINE_LOCATION);

            ActivityCompat.requestPermissions(getActivity(),
                    listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), REQUEST_CODE);


        } else {
            viewModel.enableLocationUpdate();
        }

    }


}
