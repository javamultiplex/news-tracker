package com.javamultiplex.newstracker.client;

import com.javamultiplex.newstracker.dto.News;
import com.javamultiplex.newstracker.error.handler.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NewsApiClient {

    private static final String PAGE_SIZE = "100";

    private static String NEWS_API_ENDPOINT_PREFIX = "https://newsapi.org/v2/everything";

    private static String NEWS_API_KEY = "588f220b11204dc1b89a43ca2022f84f";

    private final RestTemplate restTemplate;

    @Autowired
    public NewsApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    /**
     * @param keyword
     * @return
     */
    public News getNewsByKeyword(final String keyword) {
        String endpoint = NEWS_API_ENDPOINT_PREFIX
                + "?q=" + keyword
                + "&langauge=en"
                + "&pageSize=" + PAGE_SIZE
                + "&apiKey=" + NEWS_API_KEY;
        return getResponse(endpoint);
    }

    /**
     * @param keyword
     * @param fromDate
     * @param toDate
     * @return
     */
    public News getNewsByKeywordAndDate(final String keyword, final String fromDate, final String toDate) {
        String endpoint = NEWS_API_ENDPOINT_PREFIX
                + "?q=" + keyword
                + "&from=" + fromDate
                + "&to=" + toDate
                + "&langauge=en"
                + "&pageSize=" + PAGE_SIZE
                + "&apiKey=" + NEWS_API_KEY;
        return getResponse(endpoint);
    }

    private News getResponse(final String endPoint) {
        return restTemplate.getForObject(endPoint, News.class);
    }

}
