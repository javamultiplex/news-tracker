package com.javamultiplex.newstracker.service;

import com.javamultiplex.newstracker.dto.Article;
import com.javamultiplex.newstracker.dto.News;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {

    /**
     * @param news
     * @throws IOException
     */
    public void writeNewsUrlsToFile(final News news) throws IOException {
        List<Article> articles = news.getArticles();
        List<String> urls = articles.stream().map(Article::getUrl).collect(Collectors.toList());
        Path path = Paths.get("news.txt");
        Files.write(path, urls, Charset.defaultCharset());
    }

    /**
     * @param news
     * @throws IOException
     */
    public void writeNewsUrlsToFileWithKeyword(final News news, final String keyword) throws IOException {
        List<Article> articles = news.getArticles();
        List<String> urls = articles.stream().map(Article::getUrl).collect(Collectors.toList());
        Path path = Paths.get("news.txt");
        Files.write(path, Arrays.asList("*************************", keyword, "******************************************"), Charset.defaultCharset(), StandardOpenOption.APPEND);
        Files.write(path, urls, Charset.defaultCharset(), StandardOpenOption.APPEND);
    }


    /**
     * @return
     * @throws IOException
     */
    public List<String> readFile() throws IOException {
        Path path = Paths.get("keywords.txt");
        return Files.readAllLines(path);
    }

    public void clearFileContents() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("news.txt");
        printWriter.print("");
        printWriter.close();
    }

}
