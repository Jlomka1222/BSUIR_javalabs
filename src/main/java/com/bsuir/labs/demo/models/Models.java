package com.bsuir.labs.demo.models;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Models {
    private float a_value;
    private float b_value;
    private float c_value;
    private static final Logger logger = LogManager.getLogger(Models.class);

    public Models(Float a, Float b, Float c) {
        logger.info("Set parameters");
      try {
          this.a_value = a;
          this.b_value = b;
          this.c_value = c;
      } catch (NumberFormatException exp)
      {
          logger.error("Parsing error");
          throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exp.getMessage() + " parsing error");
      }
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
        try {
            return Math.max(a_value, Math.max(b_value, c_value));
        }catch (Exception exp)
        {
            logger.error("Error");
            throw new NumberFormatException();
        }
    }

    public boolean validateParams(Float paramA) throws NumberFormatException {
        logger.info("validating param");
        if (paramA < 0)
        {
            logger.error("BAD PARAM");
            throw new NumberFormatException();
        }
        return true;
    }


}
