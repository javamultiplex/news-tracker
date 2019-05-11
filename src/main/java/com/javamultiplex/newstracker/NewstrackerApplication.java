package com.javamultiplex.newstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;
import java.util.Scanner;

@SpringBootApplication
public class NewstrackerApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(NewstrackerApplication.class, args);
        System.out.println("******************************** NEWS TRACKER ***********************************");
        System.out.println("1. Get news urls based on keywords.");
        System.out.println("2. Get news urls based on keywords and date.");
        System.out.println("3. Get news urls based on keywords present in keywords.txt file");
        System.out.println("Enter your option to continue : ");
        Scanner input = new Scanner(System.in);
        int option = input.nextInt();
        CommandInvoker bean = run.getBean(CommandInvoker.class);
        try {
            bean.invokeCommand(option);
            System.out.println("News urls are successfully written to news.txt file.");
        } catch (IOException e) {
            System.out.println("Exception occurred while writing new urls to news.txt file.");
            e.printStackTrace();
        }
    }

}
