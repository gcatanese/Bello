package com.perosa.bello.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerminationManager extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(TerminationManager.class);

    public void run() {
        LOGGER.info("Come back soon!");
    }
}
