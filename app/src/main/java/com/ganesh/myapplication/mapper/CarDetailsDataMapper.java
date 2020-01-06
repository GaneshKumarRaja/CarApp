package com.ganesh.myapplication.mapper;

import com.ganesh.domain.common.Mapper;
import com.ganesh.domain.model.CarDetailsDomainModel;
import com.ganesh.myapplication.model.CarData;
import com.ganesh.myapplication.model.CarDetailsData;


import java.util.ArrayList;


public class CarDetailsDataMapper extends Mapper<CarDetailsDomainModel, CarDetailsData> {


    @Override
    public CarDetailsData mapFrom(CarDetailsDomainModel from) {
        CarDetailsData carData = new CarDetailsData();
        carData.carDtatLits = new ArrayList<>();
        carData.carDtatLits.add(new CarData("Address", from.getAddress()));
        carData.carDtatLits.add(new CarData("City", from.getCity()));
        carData.carDtatLits.add(new CarData("Damage Info", from.getDamageDescription()));
        carData.carDtatLits.add(new CarData("Hardware ID", from.getHardwareId()));
        carData.carDtatLits.add(new CarData("Licence Plate", from.getLicencePlate()));
        carData.carDtatLits.add(new CarData("Parking Charges", from.getPricingParking()));
        carData.carDtatLits.add(new CarData("Parking Time", from.getPricingTime()));
        carData.carDtatLits.add(new CarData("Title", from.getTitle()));
        carData.carDtatLits.add(new CarData("Zip Code", from.getZipCode()));
        carData.carDtatLits.add(new CarData("Car ID", from.getCarId().toString()));
        carData.carDtatLits.add(new CarData("Fuel Level", from.getFuelLevel().toString()));
        carData.carDtatLits.add(new CarData("Is Activated By Hardware", from.getIsActivatedByHardware().toString()));
        carData.carDtatLits.add(new CarData("Vehicle Type ID", from.getVehicleTypeId().toString()));
        carData.carDtatLits.add(new CarData("Is Clean", from.getIsClean() ? "YES" : "NO"));
        carData.carDtatLits.add(new CarData("Lattitude", from.getLat().toString()));
        carData.carDtatLits.add(new CarData("Longitude", from.getLon().toString()));
        carData.carDtatLits.add(new CarData("Location ID", from.getLocationId().toString()));
        carData.imageURL = from.getVehicleTypeImageUrl();
        return carData;
    }
}
