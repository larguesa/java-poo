package com.example.model;

public class HourPrice {
    private Integer id;
    private Double price;

    public HourPrice() {}

    public HourPrice(Integer id, Double price) {
        this.id = id;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "HourPrice{id=" + id + ", price=" + price + "}";
    }
}