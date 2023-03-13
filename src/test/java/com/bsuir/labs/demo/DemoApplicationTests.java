package com.bsuir.labs.demo;

import com.bsuir.labs.demo.models.Models;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
class DemoApplicationTests {
    @Test
    void check() {

        Models eq = new Models(1.0F, 2.2F, 1F);
        Assertions.assertEquals(2.2F, eq.checkMax());
        eq = new Models(-5F, 2F, 3F);
        Assertions.assertEquals(3F, eq.checkMax());

    }
    @Test
    void check1() {

        Models eq = new Models(1.0F, 2.4F, 1F);
        Assertions.assertEquals(2.2F, eq.checkMax());
        eq = new Models(-5F, 2F, 3F);
        Assertions.assertEquals(3F, eq.checkMax());

    }
}
