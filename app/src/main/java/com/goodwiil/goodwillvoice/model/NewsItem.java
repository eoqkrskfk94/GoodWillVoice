package com.goodwiil.goodwillvoice.model;

public class NewsItem {

    private String thumbnail;
    private String newsTitle;
    private String newsDescription;

    public NewsItem(String thumbnail, String newsTitle, String newsDescription) {
        this.thumbnail = thumbnail;
        this.newsTitle = newsTitle;
        this.newsDescription = newsDescription;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }
}
