package com.example.gowithit;

public class Hotels {
    private String HotelName;
    private String PhoneNo;
    private String Type;
    private String Place;

    public Hotels(String hotelName, String phoneNo, String type,String place) {
        HotelName = hotelName;
        PhoneNo = phoneNo;
        Type = type;
        Place=place;
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public Hotels() {
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
