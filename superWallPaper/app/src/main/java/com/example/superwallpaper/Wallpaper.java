package com.example.superwallpaper;

import java.util.List;

public class Wallpaper {
    private int total;
    private int totalHits;
    private List<WallPaperItem> hits;

    public Wallpaper() {
    }

    public Wallpaper(int total, int totalHits, List<WallPaperItem> hits) {
        this.total = total;
        this.totalHits = totalHits;
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<WallPaperItem> getHits() {
        return hits;
    }

    public void setHits(List<WallPaperItem> hits) {
        this.hits = hits;
    }
}
