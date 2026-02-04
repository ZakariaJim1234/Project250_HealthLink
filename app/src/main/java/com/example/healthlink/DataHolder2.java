package com.example.healthlink;

public class DataHolder2 {
    String dname ,place ,special ,contact ;

    public DataHolder2()
    {

    }

    public DataHolder2(String dname , String place, String special, String contact) {
        this.dname = dname ;
        this.place = place;
        this.special = special ;
        this.contact = contact ;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
