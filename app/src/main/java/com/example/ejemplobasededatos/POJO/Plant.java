package com.example.ejemplobasededatos.POJO;

public class Plant {
    private long id;
    private String botanical;
    private double price;

    public float getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBotanical() {
        return botanical;
    }

    public void setBotanical(String botanical) {
        this.botanical = botanical;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", botanical='" + botanical + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
