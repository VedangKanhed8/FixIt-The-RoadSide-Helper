package com.example.mechon;

import java.io.Serializable;

public class orders implements Serializable
{
    String lati,longi,flag,oid,oid2;
    mechClass mechanical;
    userClass user;

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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOid2() {
        return oid2;
    }

    public void setOid2(String oid2) {
        this.oid2 = oid2;
    }

    public mechClass getMechanical() {
        return mechanical;
    }

    public void setMechanical(mechClass mechanical) {
        this.mechanical = mechanical;
    }

    public userClass getUser() {
        return user;
    }

    public void setUser(userClass user) {
        this.user = user;
    }

    public orders(String lati, String longi, String flag, String oid, String oid2, mechClass mechanical, userClass user) {
        this.lati = lati;
        this.longi = longi;
        this.flag = flag;
        this.oid = oid;
        this.oid2 = oid2;
        this.mechanical = mechanical;
        this.user = user;
    }

    public orders() {
    }
}
