package com.example.myapplication;

public class Planet {

    private String name;
    private String image;
    private int id;

    public Planet(String name, String image, int id) {
        this.name = name;
        this.image = image;
        this.id = id;
    }

    String getName() {
        return name;
    }

    String getImage() {
        return image;
    }

    int getId() { return id; }


}
