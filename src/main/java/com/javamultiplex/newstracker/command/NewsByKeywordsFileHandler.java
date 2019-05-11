package com.javamultiplex.newstracker.command;

import com.javamultiplex.newstracker.dto.News;
import com.javamultiplex.newstracker.service.FileService;
import com.javamultiplex.newstracker.service.NewsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class NewsByKeywordsFileHandler implements NewsHandler {

    private final NewsApiService newsApiService;

    private final FileService fileService;

    @Autowired
    public NewsByKeywordsFileHandler(NewsApiService newsApiService, FileService fileService) {
        this.newsApiService = newsApiService;
        this.fileService = fileService;
    }

    @Override
    public void handle() throws IOException {
        List<String> keywords = fileService.readFile();
        fileService.clearFileContents();
        for (String keyword : keywords) {
            News newsByKeyword = newsApiService.getNewsByKeyword(keyword);
            fileService.writeNewsUrlsToFileWithKeyword(newsByKeyword, keyword);
        }

    }
}
