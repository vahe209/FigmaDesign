package com.example.figmadesign.model;

public class MainFood {
    private final String name;
    private final String price;
    private final Integer imageUrl;

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public MainFood(String name, String price, Integer imageUrl) {
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }
}

