package com.bsuir.labs.demo.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Models {
    private float a_value;
    private float b_value;
    private float c_value;
    private static final Logger logger = LogManager.getLogger(Models.class);

    public Models(Float a, Float b, Float c) {
        logger.info("Set parameters");
        a_value = a;
        b_value = b;
        c_value = c;
    }

    public float getA() {
        return a_value;
    }

    public float getB() {
        return b_value;
    }

    public float getC() {
        return c_value;
    }

    public float checkMax() {
        logger.info("Math calculation...");
        return Math.max(a_value, Math.max(b_value, c_value));
    }

    public boolean validateParams(Float paramA) throws NumberFormatException {
        logger.info("validating param");
        return paramA < Float.MIN_VALUE || paramA > Float.MAX_VALUE;
    }
}
