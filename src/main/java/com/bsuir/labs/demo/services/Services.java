package com.bsuir.labs.demo.services;

import com.bsuir.labs.demo.models.Models;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;

public class Services {
    private Models mod;
    private final Float aValue;
    private Float bValue;
    private Float cValue;
    public Services(Float a, Float b, Float c) {
        mod = new Models(a, b, c);
        aValue = mod.getA();
        bValue = mod.getB();
        cValue = mod.getC();
    }

    private static final Logger logger = LogManager.getLogger(Services.class);




    public ArrayList<Float> printParams() {
        ArrayList<Float> params = new ArrayList<>();
        params.add(aValue);
        params.add(bValue);
        params.add(cValue);
        Collections.sort(params);
        return params;
    }

    public float checkMax() {
        logger.info("finding max value");
        try {
            return Math.max(mod.getA(), Math.max(mod.getB(), mod.getC()));
        } catch (Exception exp) {
            logger.error("Error");
            throw new NumberFormatException();
        }
    }

    public static boolean validateParams(Float paramA) throws NumberFormatException {
        logger.info("validating param");
        if (paramA < 0) {
            logger.error("BAD PARAM");
            throw new NumberFormatException();
        }
        return true;
    }

    public static boolean validateAllParams(Float paramA, Float paramB, Float paramC) throws NumberFormatException {
        logger.info("validating params");
        if (paramA < 0) {
            logger.error("BAD PARAM A");
            throw new NumberFormatException();
        } else if (paramB < 0) {
            logger.error("BAD PARAM B");
            throw new NumberFormatException();
        } else if (paramC < 0) {
            logger.error("BAD PARAM C");
            throw new NumberFormatException();
        } else {
            logger.info("All parameters good");
        }
        return true;
    }
}
