package com.example.superwallpaper;

public class Category {
    private String name;
    private int cover;

    public Category(String name, int cover) {
        this.name = name;
        this.cover = cover;
    }

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCover() {
        return cover;
    }

    public void setCover(int cover) {
        this.cover = cover;
    }
}
