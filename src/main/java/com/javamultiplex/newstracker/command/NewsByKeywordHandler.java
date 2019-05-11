package com.javamultiplex.newstracker.command;

import com.javamultiplex.newstracker.dto.News;
import com.javamultiplex.newstracker.service.FileService;
import com.javamultiplex.newstracker.service.NewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class NewsByKeywordHandler implements NewsHandler {

    private final NewsApiService newsApiService;

    private final FileService fileService;

    @Autowired
    public NewsByKeywordHandler(NewsApiService newsApiService, FileService fileService) {
        this.newsApiService = newsApiService;
        this.fileService = fileService;
    }

    @Override
    public void handle() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter keyword.");
        String keyword = input.next();
        News newsByKeyword = newsApiService.getNewsByKeyword(keyword);
        fileService.writeNewsUrlsToFile(newsByKeyword);
    }
}
