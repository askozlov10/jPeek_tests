package org.jpeek.patterns.example5;

public class NewsChannel implements Channel {
    private String news;

    @Override
    public void update(Object news) {
        this.setNews((String) news);
    }

    public void setNews(String news) {
        this.news = news;
    }
}
