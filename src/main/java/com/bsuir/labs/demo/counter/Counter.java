package com.bsuir.labs.demo.counter;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.awt.geom.RectangularShape;
import java.util.ArrayList;
import java.util.List;

@Component
public class Counter extends Thread {

    public Counter() {
        super();
        start();
    }

    private static final Logger logger = LogManager.getLogger(Counter.class);
    private int countSunc = 0;
    private int countUnsunc = 0;
    private final Object lock = new Object();

//    public void increment() {
//        logger.info("increment counter");
//        synchronized (lock) {
//            countSunc++;
//        }
//    }


    public void incrementUnsunc() {
        countUnsunc++;
    }

    public ResponseEntity<?> getCount() throws JSONException {
        logger.info("return amount of requests");
        JSONObject response = new JSONObject();
        response.put("sync", countSunc);
        response.put("unsync", countUnsunc);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
}
