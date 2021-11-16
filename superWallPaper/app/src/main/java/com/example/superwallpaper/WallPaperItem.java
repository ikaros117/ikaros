package com.example.superwallpaper;

public class WallPaperItem {

    private int id;
    private String webformatURL;
    private String largeImageURL;

    public WallPaperItem() {
    }

    public WallPaperItem(int id, String webformatURL, String largeImageURL) {
        this.id = id;
        this.webformatURL = webformatURL;
        this.largeImageURL = largeImageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebformatURL() {
        return webformatURL;
    }

    public void setWebformatURL(String webformatURL) {
        this.webformatURL = webformatURL;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }
}
