package com.example.rsssn;

/**
 * Created by Amit on 24,November,2020
 */
class UserInformationMap {

    public String id;
    public String name;
    public double latitude;
    public double longitude;

    public UserInformationMap(){

    }

    public UserInformationMap(String id,String name, double latitude, double longitude) {
        this.name = name;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
