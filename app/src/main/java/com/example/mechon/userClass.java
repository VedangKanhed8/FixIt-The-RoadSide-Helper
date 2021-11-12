package com.example.mechon;

import java.io.Serializable;

public class userClass implements Serializable
{
    String name,email,address,phone,uuid;

    public userClass() {

    }

    public userClass(String mName, String mEmail, String mAddr, String mPhone,String uuid) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.uuid = phone;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
}

