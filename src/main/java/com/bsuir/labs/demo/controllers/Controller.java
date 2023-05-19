package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.cache.Cache;
import com.bsuir.labs.demo.counter.Counter;

import com.bsuir.labs.demo.database.FindingMax;
import com.bsuir.labs.demo.models.Models;
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
    private FindingMax findingMax;

    public Cache maxValueCache = new Cache();
    public Counter counter = new Counter();
    private static final Logger logger = LogManager.getLogger(Controller.class);

    @GetMapping(value = "/get")
    public String maxValue(@RequestParam(name = "A") Float aValue,
                           @RequestParam(name = "B") Float bValue,
                           @RequestParam(name = "C") Float cValue,
                           @ModelAttribute("models") Models models) throws Exception {

        Services mod = new Services(findingMax, aValue, bValue, cValue);
        logger.info("Validate params");
        Services.validateAllParams(aValue, bValue, cValue);
        JSONObject response = new JSONObject();
        logger.info("founding max value");
        response.put("maxValue", mod.checkMaxLambda());
        float maxValue = mod.checkMax();
        maxValueCache.add(mod.printParams(), maxValue);
        //counter.increment();
        counter.incrementUnsunc();

        models.setaValue(aValue);
        models.setbValue(bValue);
        models.setcValue(cValue);


        return response.toString();
    }

    @GetMapping(value = "/count")
    public ResponseEntity<?> count() throws JSONException {
        return counter.getCount();
    }

    @PostMapping
    @RequestMapping(value = "/post",
            method = RequestMethod.POST,
            produces = "application/json")
    public ResponseEntity<?> bulkEndpoint(
            @RequestBody List<Map<String, Integer>> request) throws JSONException {
        logger.info("POST");
        JSONObject response = new JSONObject();
        List<Map<String, Float>> answers = new ArrayList<>();
        for(int i = 0; i < request.size(); i++) {
            answers.add(new HashMap<>());
            answers.get(i).put("A", Float.valueOf(request.get(i).get("A")));
            answers.get(i).put("B", Float.valueOf(request.get(i).get("B")));
            answers.get(i).put("C", Float.valueOf(request.get(i).get("C")));
        }
        List<Float> params = new ArrayList<>();
        List<Float> ansBulk = new ArrayList<>();
        for (Map<String, Float> answer : answers) {
            Float aValue = answer.get("A");
            Float bValue = answer.get("B");
            Float cValue = answer.get("C");
            Services checkObj = new Services(findingMax, aValue,bValue,cValue);
            Float resultValue = checkObj.checkMaxLambda(aValue,bValue,cValue);
            ansBulk.add(resultValue);
            params.add(aValue);
            params.add(bValue);
            params.add(cValue);
            response.put("bulkResult", ansBulk);
            response.put("argsProvided", (long) params.size());
            response.put("min", params.stream().min(Float::compare).orElse(null));
            response.put("max", params.stream().max(Float::compare).orElse(null));
            Map<Float, Long> frequency = new HashMap<>();
            params.forEach(element -> frequency.put(element, params.stream()
                    .filter(el -> Objects.equals(el, element))
                    .count()));
            response.put("argsFrequency", frequency);
        }
        return new ResponseEntity<>(response.toString(), HttpStatus.OK);
    }


}
