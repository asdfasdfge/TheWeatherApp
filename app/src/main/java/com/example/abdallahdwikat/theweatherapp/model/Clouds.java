package com.example.abdallahdwikat.theweatherapp.model;

public class Clouds {
    private String all;

    public Clouds(String all) {
        this.all = all;
    }

    public Clouds() {
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "ClassPojo [all = " + all + "]";
    }
}
