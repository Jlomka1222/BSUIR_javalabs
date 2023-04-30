package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.cache.Cache;
import com.bsuir.labs.demo.counter.Counter;

import com.bsuir.labs.demo.services.Services;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


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
        Services.validateAllParams(aValue, bValue, cValue);
        JSONObject response = new JSONObject();
        logger.info("founding max value");
        response.put("maxValue", mod.checkMaxLambda());
        float maxValue = mod.checkMax();
        maxValueCache.add(mod.printParams(), maxValue);
        counter.increment();
        return response.toString();
    }

    @GetMapping(value = "/count")
    public Integer count() {
        return counter.getCount();
    }

    @PostMapping
    @RequestMapping(value = "/post",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity<?> bulkEndpoint(
            @RequestBody Map<String, Object> request) throws JSONException {
        Integer aValue = (Integer) request.get("A");
        Integer bValue = (Integer) request.get("B");
        Integer cValue = (Integer) request.get("C");
        logger.info("POST");
        List<Integer> params = new ArrayList<>();
        params.add(aValue);
        params.add(bValue);
        params.add(cValue);
        JSONObject response = new JSONObject();
        response.put("argsProvided", (long) params.size());
        response.put("min", params.stream().min(Integer::compare).orElse(null));
        response.put("max", params.stream().max(Integer::compare).orElse(null));
        Map<Integer, Long> frequency = new HashMap<>();
        params.forEach(element -> frequency.put(element, params.stream()
                .filter(el -> Objects.equals(el, element))
                .count()));
        response.put("argsFrequency", frequency);
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }
}
