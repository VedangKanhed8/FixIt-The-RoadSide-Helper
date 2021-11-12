package com.example.mechon;

public class feedClass
{
    String rating,caption;
    userClass user;
    mechClass mech;

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public userClass getUser() {
        return user;
    }

    public void setUser(userClass user) {
        this.user = user;
    }

    public mechClass getMech() {
        return mech;
    }

    public void setMech(mechClass mech) {
        this.mech = mech;
    }

    public feedClass() {
    }

    public feedClass(String rating, String caption, userClass user, mechClass mech) {
        this.rating = rating;
        this.caption = caption;
        this.user = user;
        this.mech = mech;
    }
}
