package com.bsuir.labs.demo.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;


public class Models {
    private final float aValue;
    private final float bValue;
    private final float cValue;
    private static final Logger logger = LogManager.getLogger(Models.class);

    public Models(Float a, Float b, Float c) {
        logger.info("Set parameters");
        try {
            this.aValue = a;
            //validateParams(aValue);
            this.bValue = b;
            //validateParams(bValue);
            this.cValue = c;
            //validateParams(cValue);

        } catch (NumberFormatException exp) {
            logger.error("Parsing error");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exp.getMessage() + " parsing error");
        }
    }


    public float getA() {
        return aValue;
    }

    public float getB() {
        return bValue;
    }

    public float getC() {
        return cValue;
    }


}
