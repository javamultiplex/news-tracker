package com.javamultiplex.newstracker.command;

import com.javamultiplex.newstracker.dto.News;
import com.javamultiplex.newstracker.service.FileService;
import com.javamultiplex.newstracker.service.NewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class NewsByKeywordAndDateHandler implements NewsHandler {

    private final NewsApiService newsApiService;

    private final FileService fileService;

    @Autowired
    public NewsByKeywordAndDateHandler(NewsApiService newsApiService, FileService fileService) {
        this.newsApiService = newsApiService;
        this.fileService = fileService;
    }

    @Override
    public void handle() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter keyword.");
        String keyword = input.next();
        System.out.println("Enter fromDate in yyyy-MM-dd or yyyy-MM-ddTHH:mm:ss format :");
        String fromDate = input.next();
        System.out.println("Enter ToDate in yyyy-MM-dd or yyyy-MM-ddTHH:mm:ss format :");
        String toDate = input.next();
        News newsByKeywordAndDate = newsApiService.getNewsByKeywordAndDate(keyword, fromDate, toDate);
        fileService.writeNewsUrlsToFile(newsByKeywordAndDate);
    }
}
