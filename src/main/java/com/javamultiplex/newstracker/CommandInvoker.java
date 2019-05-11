package com.javamultiplex.newstracker;

import com.javamultiplex.newstracker.command.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CommandInvoker implements InitializingBean {

    private Map<Integer, NewsHandler> map = new HashMap<>();

    private final NewsByKeywordHandler newsByKeywordHandler;

    private final NewsByKeywordAndDateHandler newsByKeywordAndDateHandler;

    private final NewsByKeywordsFileHandler newsByKeywordsFileHandler;

    private final DefaultNewsHandler defaultNewsHandler;

    @Autowired
    public CommandInvoker(NewsByKeywordHandler newsByKeywordHandler, NewsByKeywordAndDateHandler newsByKeywordAndDateHandler, NewsByKeywordsFileHandler newsByKeywordsFileHandler, DefaultNewsHandler defaultNewsHandler) {
        this.newsByKeywordHandler = newsByKeywordHandler;
        this.newsByKeywordAndDateHandler = newsByKeywordAndDateHandler;
        this.newsByKeywordsFileHandler = newsByKeywordsFileHandler;
        this.defaultNewsHandler = defaultNewsHandler;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        map.put(1, newsByKeywordHandler);
        map.put(2, newsByKeywordAndDateHandler);
        map.put(3, newsByKeywordsFileHandler);
    }

    public void invokeCommand(final int option) throws IOException {
        if (map.containsKey(option)) {
            map.get(option).handle();
        } else {
            defaultNewsHandler.handle();
        }
    }

}
