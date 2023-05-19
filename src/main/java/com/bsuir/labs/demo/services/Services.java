package com.bsuir.labs.demo.services;

import com.bsuir.labs.demo.database.FindingMax;
import com.bsuir.labs.demo.models.Models;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class Services {

    private final FindingMax findingMax;
    private static final Logger logger = LogManager.getLogger(Services.class);
    private Models mod;
    private final float aValue;
    private final float bValue;
    private final float cValue;



    public Services(FindingMax findingMax, float a,float b, float c) {
        this.findingMax = findingMax;
        //mod = new Models(a, b, c);
        aValue = mod.getaValue();
        bValue = mod.getbValue();
        cValue = mod.getcValue();
    }


    public ArrayList<Float> printParams() {
        logger.info("add parameters to list");
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
            return Math.max(mod.getaValue(), Math.max(mod.getbValue(), mod.getcValue()));
        } catch (Exception exp) {
            logger.error("Error");
            throw new NumberFormatException();
        }
    }

    public float checkMaxLambda() {
        logger.info("finding max value");
        List<Float> floats = Arrays.asList(aValue,bValue,cValue);
        Float result;
        return result = floats.stream()
                .reduce(Float.NEGATIVE_INFINITY, Math::max);
    }
    public float checkMaxLambda(float fValue,float sValue,float tValue) {
        logger.info("finding max value");
        List<Float> floats = Arrays.asList(fValue,sValue,tValue);
        Float result;
        return result = floats.stream()
                .reduce(Float.NEGATIVE_INFINITY, Math::max);
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

    @Transactional
    public void save(Models models){
        findingMax.save(models);
    }

    @Transactional
    public Models findOne(int id){
        Optional<Models> foundModels = findingMax.findById(id);
        return foundModels.orElse(null);
    }

}
