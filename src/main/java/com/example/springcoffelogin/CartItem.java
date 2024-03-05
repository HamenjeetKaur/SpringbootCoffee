package com.example.springcoffelogin;
import java.util.Objects;
public class CartItem {

    private String p_name;
    private double pp;
    private String image;
    private int quantity;

    public CartItem(String p_name, double pp, String image) {
        this.p_name = p_name;
        this.pp = pp;
        this.image = image;
        this.quantity = 1;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItem cartItem = (CartItem) o;
        return Objects.equals(p_name, cartItem.p_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(p_name);
    }
}
