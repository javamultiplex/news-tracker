package com.javamultiplex.newstracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class News {

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "totalResults")
    private String totalResults;

    @JsonProperty(value = "articles")
    private List<Article> articles;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
