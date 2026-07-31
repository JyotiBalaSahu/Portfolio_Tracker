package com.portfolio.crypto_microservice;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Crypto {
    @Id
    private String asset;
    private String name;
    private double price;

    // Constructors
    public Crypto() {}
    public Crypto(String asset, String name, double price) {
        this.asset = asset;
        this.name = name;
        this.price = price;
    }

    // Getters & Setters
    public String getAsset() { return asset; }
    public void setAsset(String asset) { this.asset = asset; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}