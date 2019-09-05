package com.perosa.bello;

import com.perosa.bello.server.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BalancerApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(BalancerApp.class);

    public static void main(String[] args) {

        new Listener().setUp();

        LOGGER.info("Bello is up!");
    }
}
