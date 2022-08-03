package com.example.gowithit;

public class Hotels {
    private String HotelName;
    private String PhoneNo;
    private String Type;
    private String Place;

    public Hotels(String HotelName, String PhoneNo, String Type,String Place) {
        this.HotelName = HotelName;
        this.PhoneNo = PhoneNo;
        this.Type = Type;
        this.Place=Place;
    }
    public Hotels() {
    }

    public String getPlace() {
        return Place;
    }

    public void setPlace(String Place) {
       this.Place = Place;
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
