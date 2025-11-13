package com.app.process;

import java.io.IOException;
import java.util.logging.*;

public class LoggerExample {
    private static final Logger logger = Logger.getLogger(LoggerExample.class.getName());

    public static void main(String[] args) {
        try {
            FileHandler fileHandler = new FileHandler("app.log", true); 
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.info("Application started");
            logger.warning("This is a warning");
            logger.severe("Critical error occurred");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

