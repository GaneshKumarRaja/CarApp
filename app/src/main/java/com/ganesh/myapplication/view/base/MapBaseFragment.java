package com.ganesh.myapplication.view.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;

import dagger.android.support.AndroidSupportInjection;


public abstract class MapBaseFragment
        extends BaseFragment
        implements OnMapReadyCallback {

    protected GoogleMap gmap;
    private MapView map;
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        AndroidSupportInjection.inject(this);
//        super.onCreate(savedInstanceState);
//    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);

        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        map.onSaveInstanceState(mapViewBundle);
    }


    @Override
    public void onStart() {
        super.onStart();
        map.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        map.onStop();
    }

    @Override
    public void onPause() {
        map.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        map.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        map.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        onMapReady();
    }


    protected void setUpMap(Bundle savedInstanceState, MapView map) {
        Bundle mapViewBundle = null;
        this.map = map;

        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }

        map.onCreate(mapViewBundle);
        map.getMapAsync(this);
    }


    protected abstract void onMapReady();


}
