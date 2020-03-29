package com.example.myapplication;

public class Place  {
    private String image;
    private String name;
    private int planetId;
    private String category;

    public Place(String image, String name, int planetId, String category) {
        this.image = image;
        this.name = name;
        this.planetId = planetId;
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public int getPlanetId() {
        return planetId;
    }
}
