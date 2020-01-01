package com.ganesh.myapplication.view.base;

import com.ganesh.myapplication.model.MarkerModel;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashSet;
import java.util.List;

public abstract class BaseMapMarkerFragment
        extends MapBaseFragment
        implements GoogleMap.OnMarkerClickListener {


    HashSet<Marker> addedMarker = new HashSet<Marker>();

    protected abstract void onMarkerSelected();

    protected void addMarker(List<MarkerModel> markerModelList) {
        gmap.clear();

        for (MarkerModel markerModel : markerModelList) {
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng ny = new LatLng(markerModel.latitude, markerModel.longitude);
            markerOptions.position(ny);
            markerOptions.title(ny.latitude + " : " + ny.longitude);
            Marker marker = gmap.addMarker(markerOptions);
            marker.setTag(false);
            addedMarker.add(marker);
        }

        gmap.setOnMarkerClickListener(this);
    }

    @Override
    public boolean onMarkerClick(final Marker marker) {

        Boolean status = (Boolean) marker.getTag();
        marker.showInfoWindow();

        if (status != null) {

            if (status) {
                onMarkerSelected();
            } else {
                // dis appear all markers
                removeAllMarker(marker);
                marker.setTag(true);
            }
        }

        return true;
    }


    private void removeAllMarker(Marker marker) {
        addedMarker.remove(marker);


        for (Marker m : addedMarker) {
            m.remove();
        }
    }

}
