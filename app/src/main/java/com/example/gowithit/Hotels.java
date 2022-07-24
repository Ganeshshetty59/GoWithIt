package com.example.gowithit;

public class Hotels {
    private String HotelName;
    private String PhoneNo;
    private String Type;

    public Hotels(String hotelName, String phoneNo, String type) {
        HotelName = hotelName;
        PhoneNo = phoneNo;
        Type = type;
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
