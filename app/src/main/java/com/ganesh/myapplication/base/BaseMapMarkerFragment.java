package com.ganesh.myapplication.base;


import android.location.Location;

import com.ganesh.myapplication.model.MarkerModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashSet;
import java.util.List;

public abstract class BaseMapMarkerFragment
        extends MapBaseFragment
        implements GoogleMap.OnMarkerClickListener {
    private HashSet<Marker> addedMarker = new HashSet<>();

    protected abstract void onMarkerSelected(MarkerModel markerModel);

    private Marker userMarker;

    protected void addMarker(List<MarkerModel> markerModelList) {
        getMap().clear();

        if (markerModelList != null) {
            if (markerModelList.size() > 0)
                zoom(markerModelList.get(0).latitude, markerModelList.get(0).longitude);
        }

        assert markerModelList != null;
        for (MarkerModel markerModel : markerModelList) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng latLng = new LatLng(markerModel.latitude, markerModel.longitude);
            markerOptions.position(latLng);
            markerOptions.title(markerModel.carName);
            Marker marker = getMap().addMarker(markerOptions);
            marker.setTag(markerModel);
            addedMarker.add(marker);
        }

        getMap().setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        MarkerModel selectedMarker = (MarkerModel) marker.getTag();
        marker.showInfoWindow();

        if (selectedMarker != null) {
            // if it is first time click, status = false. if it is second time click, status = true
            if (selectedMarker.status) {
                //callback to notify second time is clicked
                onMarkerSelected((MarkerModel) marker.getTag());
            } else {
                // changing the marker color
                marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                // dis appear all other markers
                removeAllMarker(marker);
                // marked it is clicked
                selectedMarker.status = true;
            }
        }

        return true;
    }

    private void zoom(Double latitude, Double longitude) {
        getMap().animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude, longitude), 15.0f));
    }

    protected void updateUserLocation(Location userLocation) {

        if (userMarker != null) {
            userMarker.remove();
        }

        MarkerOptions markerOptions = new MarkerOptions();
        LatLng ny = new LatLng(userLocation.getLatitude(), userLocation.getLongitude());
        markerOptions.position(ny);
        Marker marker = getMap().addMarker(markerOptions);
        marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
        userMarker = marker;
    }

    private void removeAllMarker(Marker marker) {
        addedMarker.remove(marker);

        for (Marker markerNeedToRemove : addedMarker) markerNeedToRemove.remove();
    }

}
