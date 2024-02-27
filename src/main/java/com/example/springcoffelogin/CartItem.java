package com.example.springcoffelogin;

import java.util.Objects;

public class CartItem {

    private String p_name;
    private double pp;
    private String image;


    public CartItem(String item, double pp, String image) {
        this.p_name = p_name;
        this.pp = pp;
        this.image = image;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public double getPp() {
        return pp;
    }

    public void setPp(double pp) {
        this.pp = pp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}