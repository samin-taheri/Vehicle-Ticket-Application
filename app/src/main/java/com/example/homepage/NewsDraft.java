package com.example.homepage;

public class NewsDraft {
    private String newsTitle, news, date;
    public NewsDraft() {
    }
    public NewsDraft(String newsTitle, String news, String date) {
        this.newsTitle = newsTitle;
        this.news = news;
        this.date = date;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
