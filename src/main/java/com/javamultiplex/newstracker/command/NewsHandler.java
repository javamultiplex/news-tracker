package com.javamultiplex.newstracker.command;

import com.javamultiplex.newstracker.dto.News;

import java.io.IOException;

public interface NewsHandler {

    void handle() throws IOException;
}
