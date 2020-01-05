package com.ganesh.myapplication.model;

public class MarkerModel {
    public Double latitude,longitude;
    public Integer carID;
    public boolean status = false;
    public String carName;
    public MarkerModel(Double latitude,Double longitude,Integer carID,String carName){
        this.latitude = latitude;
        this.longitude=longitude;
        this.carID=carID;
        this.carName=carName;
    }

}
