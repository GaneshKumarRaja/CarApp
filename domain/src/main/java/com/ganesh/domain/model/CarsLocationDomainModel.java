package com.ganesh.domain.model;

public class CarsLocationDomainModel {
    private String zipCode;

    private String vehicleTypeId;

    private String fuelLevel;

    private String address;

    private String reservationState;

    private String distance;

    private String vehicleStateId;

    private String city;

    private String licencePlate;

    private String lon;

    private String title;

    private String pricingTime;

    private String carId;

    private String isClean;

    private String pricingParking;

    private String locationId;

    private String lat;

    private String isDamaged;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getVehicleTypeId() {
        return vehicleTypeId;
    }

    public void setVehicleTypeId(String vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getFuelLevel() {
        return fuelLevel;
    }

    public void setFuelLevel(String fuelLevel) {
        this.fuelLevel = fuelLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReservationState() {
        return reservationState;
    }

    public void setReservationState(String reservationState) {
        this.reservationState = reservationState;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getVehicleStateId() {
        return vehicleStateId;
    }

    public void setVehicleStateId(String vehicleStateId) {
        this.vehicleStateId = vehicleStateId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPricingTime() {
        return pricingTime;
    }

    public void setPricingTime(String pricingTime) {
        this.pricingTime = pricingTime;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public String getIsClean() {
        return isClean;
    }

    public void setIsClean(String isClean) {
        this.isClean = isClean;
    }

    public String getPricingParking() {
        return pricingParking;
    }

    public void setPricingParking(String pricingParking) {
        this.pricingParking = pricingParking;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getIsDamaged() {
        return isDamaged;
    }

    public void setIsDamaged(String isDamaged) {
        this.isDamaged = isDamaged;
    }

    @Override
    public String toString() {
        return "ClassPojo [zipCode = " + zipCode + ", vehicleTypeId = " + vehicleTypeId + ", fuelLevel = " + fuelLevel + ", address = " + address + ", reservationState = " + reservationState + ", distance = " + distance + ", vehicleStateId = " + vehicleStateId + ", city = " + city + ", licencePlate = " + licencePlate + ", lon = " + lon + ", title = " + title + ", pricingTime = " + pricingTime + ", carId = " + carId + ", isClean = " + isClean + ", pricingParking = " + pricingParking + ", locationId = " + locationId + ", lat = " + lat + ", isDamaged = " + isDamaged + "]";
    }
}