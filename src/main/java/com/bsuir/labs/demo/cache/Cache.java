package com.bsuir.labs.demo.cache;

import com.bsuir.labs.demo.models.Models;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;


public class Cache {
    private static final Logger logger = LogManager.getLogger(Cache.class);


    private static HashMap<ArrayList, Float> solutions = new HashMap<>();


    public void add(ArrayList params, Float root) {
        if (!solutions.containsKey(params)) {
            solutions.put(params, root);
            logger.info("Value " + params + " @ " + root + " added to the cache!");
        }

    }

    public Float find(Models params) {
        if (solutions.containsKey(params))
            return solutions.get(params);
        logger.warn("Value " + params + " was not found in cache!");
        return null;
    }
}


