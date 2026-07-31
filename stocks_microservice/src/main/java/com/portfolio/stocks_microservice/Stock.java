package com.portfolio.stocks_microservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Stock {
    @Id
    private String ticker;
    private String name;
    private double price;

    // Constructors
    public Stock() {}
    public Stock(String ticker, String name, double price) {
        this.ticker = ticker;
        this.name = name;
        this.price = price;
    }

    // Getters & Setters
    public String getTicker() { return ticker; }
    public void setTicker(String ticker) { this.ticker = ticker; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}