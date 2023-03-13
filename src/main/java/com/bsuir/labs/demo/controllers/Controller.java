package com.bsuir.labs.demo.controllers;

import com.bsuir.labs.demo.models.Models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;


@RestController
public class Controller {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    @GetMapping(value = "/get")
    public String maxValue(@RequestParam(name = "A") Float a_value,
                           @RequestParam(name = "B") Float b_value,
                           @RequestParam(name = "C") Float c_value) throws Exception {
        Models mod = new Models(a_value, b_value, c_value);
        logger.info("Validate params");
        JSONObject response = new JSONObject();
        logger.info("founding max value");
        response.put("max_value", mod.checkMax());
        return response.toString();
    }
}
