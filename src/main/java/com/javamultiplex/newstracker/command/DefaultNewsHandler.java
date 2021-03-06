package com.javamultiplex.newstracker.command;

import com.javamultiplex.newstracker.error.InvalidOptionException;
import org.springframework.stereotype.Component;

@Component
public class DefaultNewsHandler implements NewsHandler {

    @Override
    public void handle() {
        throw new InvalidOptionException("Option that you have entered is not valid, please enter valid Option!");
    }
}
