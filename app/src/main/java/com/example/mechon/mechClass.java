package com.example.mechon;

import java.io.Serializable;

public class mechClass implements Serializable
{
    String name,email,address,phone,lati,longi,prolink,liclink,muid;

    public mechClass(String name, String email, String address, String phone, String lati, String longi, String prolink, String liclink, String muid) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.lati = lati;
        this.longi = longi;
        this.prolink = prolink;
        this.liclink = liclink;
        this.muid = muid;
    }

    public mechClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLati() {
        return lati;
    }

    public void setLati(String lati) {
        this.lati = lati;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getProlink() {
        return prolink;
    }

    public void setProlink(String prolink) {
        this.prolink = prolink;
    }

    public String getLiclink() {
        return liclink;
    }

    public void setLiclink(String liclink) {
        this.liclink = liclink;
    }

    public String getMuid() {
        return muid;
    }

    public void setMuid(String muid) {
        this.muid = muid;
    }
}
