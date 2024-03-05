package com.example.springcoffelogin;
import java.util.Objects;

public class Transaction {

    private int id;
    private String transaction_type;
    private double amount;

    public Transaction(int id, String transaction_type, double amount) {
        this.id = id;
        this.transaction_type = transaction_type;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTransaction_type() {
        return transaction_type;
    }

    public void setTransaction_type(String transaction_type) {
        this.transaction_type = transaction_type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}