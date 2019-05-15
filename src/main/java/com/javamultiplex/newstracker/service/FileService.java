package com.javamultiplex.newstracker.service;

import com.javamultiplex.newstracker.dto.News;
import com.opencsv.CSVWriter;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService {

    /**
     * @param news
     * @throws IOException
     */
    public void writeNewsUrlsToFile(final News news) throws IOException {
        List<String[]> articlesList = getArticlesList(news);
        Path path = Paths.get("news.csv");
        FileWriter fileWriter = new FileWriter(path.toFile());
        CSVWriter csvWriter = new CSVWriter(fileWriter);
        csvWriter.writeAll(articlesList);
    }

    /**
     * @param news
     * @throws IOException
     */
    public void writeNewsUrlsToFileWithKeyword(final News news, final String keyword) throws IOException {
        List<String[]> articlesList = getArticlesList(news,keyword);
        Path path = Paths.get("news.csv");
        FileWriter fileWriter = new FileWriter(path.toFile(), true);
        CSVWriter csvWriter = new CSVWriter(fileWriter, ',',
                CSVWriter.DEFAULT_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        csvWriter.writeAll(articlesList);
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
        PrintWriter printWriter = new PrintWriter("news.csv");
        printWriter.print("");
        printWriter.close();
    }

    private List<String[]> getArticlesList(News news) {
        List<String[]> articlesList = new ArrayList<>();
        articlesList.add(new String[]{"TITLE", "AUTHOR", "DATE", "DESCRIPTION", "CONTENT", "URL"});
        news.getArticles().forEach(a -> {
            articlesList.add(new String[]{"'" + a.getTitle() + "'", "'" + a.getAuthor() + "'", "'" + a.getPublishedAt() + "'", "'" + a.getDescription() + "'", "'" + a.getContent() + "'", "'" + a.getUrl() + "'"});
        });
        return articlesList;
    }

    private List<String[]> getArticlesList(News news, String keyword) {
        List<String[]> articlesList = new ArrayList<>();
        articlesList.add(new String[]{"KEYWORD","TITLE", "AUTHOR", "DATE", "DESCRIPTION", "CONTENT", "URL"});
        news.getArticles().forEach(a -> {
            articlesList.add(new String[]{keyword,"'" + a.getTitle() + "'", "'" + a.getAuthor() + "'", "'" + a.getPublishedAt() + "'", "'" + a.getDescription() + "'", "'" + a.getContent() + "'", "'" + a.getUrl() + "'"});
        });
        return articlesList;
    }
}
