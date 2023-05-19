package com.bsuir.labs.demo.models;

import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Entity
@Table(name = "max_value")
public class Models {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "a_value")
    private float aValue;
    @Column(name = "b_value")
    private float bValue;
    @Column(name = "c_value")
    private float cValue;

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

    public Models(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getaValue() {
        return aValue;
    }

    public void setaValue(float aValue) {
        this.aValue = aValue;
    }

    public float getbValue() {
        return bValue;
    }

    public void setbValue(float bValue) {
        this.bValue = bValue;
    }

    public float getcValue() {
        return cValue;
    }

    public void setcValue(float cValue) {
        this.cValue = cValue;
    }
}
