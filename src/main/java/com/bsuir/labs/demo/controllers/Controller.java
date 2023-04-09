package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.cache.Cache;
import com.bsuir.labs.demo.counter.Counter;
import com.bsuir.labs.demo.models.Models;

import com.bsuir.labs.demo.services.Services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;


@RestController
public class Controller {
    public Cache maxValueCache = new Cache();
    public Counter counter = new Counter();
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @GetMapping(value = "/get")
    public String maxValue(@RequestParam(name = "A") Float aValue,
                           @RequestParam(name = "B") Float bValue,
                           @RequestParam(name = "C") Float cValue) throws Exception {
        Services mod = new Services(aValue, bValue, cValue);
        logger.info("Validate params");
        mod.validateAllParams(aValue,bValue,cValue);
        JSONObject response = new JSONObject();
        logger.info("founding max value");
        response.put("maxValue", mod.checkMax());
        float maxValue = mod.checkMax();
        maxValueCache.add(mod.printParams(), maxValue);
        counter.increment();
        return response.toString(); 
    }
    @GetMapping(value = "/count")
    public Integer count(){
        return counter.getCount();
    }
}