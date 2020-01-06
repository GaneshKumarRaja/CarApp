package com.ganesh.myapplication.presentation.carlist;


import android.Manifest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;

import android.location.LocationManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.ganesh.myapplication.R;
import com.ganesh.myapplication.base.BaseMapMarkerFragment;
import com.ganesh.myapplication.databinding.FragmentCarsListMapBinding;
import com.ganesh.myapplication.model.MarkerModel;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;


import java.util.ArrayList;

import java.util.Objects;

import javax.inject.Inject;


public class CarsListMapFragment extends BaseMapMarkerFragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    @Inject
    CarLocationViewModel viewModel;

    private FragmentCarsListMapBinding binding;

    private static final int RNTIME_REQUEST_CODE = 100;

    private static final int REQUEST_CHECK_SETTINGS = 101;

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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int permissionLocation = ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionLocation == PackageManager.PERMISSION_GRANTED) enableGPS();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // Check for the integer request code originally supplied to startResolutionForResult().
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case Activity.RESULT_OK:
                    enableGPS();
                    break;
                case Activity.RESULT_CANCELED:

                    break;
            }
        }
    }

    /**
     * observers for viewmodel's liveData
     */
    private void setUpViewModelObserver() {

        viewModel.getMarkerLiveData().observe(this, this::addMarker);

        viewModel.locationMutableLiveData.observe(this, this::updateUserLocation);

        viewModel.getErrorMessage().observe(this, this::showMessage);
    }


    private void checkPermissions() {
        int permissionLocation = ContextCompat.checkSelfPermission(Objects.requireNonNull(getActivity()),
                android.Manifest.permission.ACCESS_FINE_LOCATION);

        ArrayList<String> listPermissionsNeeded = new ArrayList<>();

        if (permissionLocation == PackageManager.PERMISSION_GRANTED) {
            enableGPS();
        } else {
            listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissions(
                    listPermissionsNeeded.toArray(new String[0]), RNTIME_REQUEST_CODE);

        }


    }

    private void displayLocationSettingsRequest() {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(Objects.requireNonNull(getActivity()))
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(
                // Show the dialog by calling startResolutionForResult(), and check the result
                this::onResult);
    }


    private void enableGPS() {

        LocationManager service = (LocationManager) Objects.requireNonNull(getActivity()).getSystemService(Context.LOCATION_SERVICE);

        assert service != null;
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Check if enabled and if not send user to the GPS settings
        if (enabled) {
            viewModel.enableLocationUpdate();
        } else {
            displayLocationSettingsRequest();
        }


    }


    private void onResult(LocationSettingsResult result1) {
        final Status status = result1.getStatus();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                enableGPS();
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    // Show the dialog by calling startResolutionForResult(), and check the result
                    // in onActivityResult().
                    status.startResolutionForResult(getActivity(), REQUEST_CHECK_SETTINGS);
                } catch (IntentSender.SendIntentException e) {
                    //Log.i(TAG, "PendingIntent unable to execute request.");
                }
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                // Log.i(TAG, "Location settings are inadequate, and cannot be fixed here. Dialog not created.");
                break;
        }
    }
}
