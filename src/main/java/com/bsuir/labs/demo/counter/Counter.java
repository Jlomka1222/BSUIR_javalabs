package com.bsuir.labs.demo.counter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class Counter extends Thread {

    public Counter() {
        super();
        start();
    }

    private static final Logger logger = LogManager.getLogger(Counter.class);
    private int count = 0;
    private final Object lock = new Object();

    public void increment() {
        logger.info("increment counter");
        synchronized (lock) {
            count++;
        }
    }

    public int getCount() {
        logger.info("return amount of requests");
        synchronized (lock) {
            return count;
        }
    }
}
