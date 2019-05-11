package com.javamultiplex.newstracker.service;

import com.javamultiplex.newstracker.client.NewsApiClient;
import com.javamultiplex.newstracker.dto.News;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NewsApiService {

    private final NewsApiClient newsApiClient;

    @Autowired
    public NewsApiService(NewsApiClient newsApiClient) {
        this.newsApiClient = newsApiClient;
    }

    /**
     * @param keyword
     * @return
     */
    public News getNewsByKeyword(final String keyword) {
        return newsApiClient.getNewsByKeyword(keyword);
    }

    /**
     * @param keyword
     * @param fromDate
     * @param toDate
     * @return
     */
    public News getNewsByKeywordAndDate(final String keyword, final String fromDate, final String toDate) {
        return newsApiClient.getNewsByKeywordAndDate(keyword, fromDate, toDate);
    }

}
