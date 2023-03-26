package com.bsuir.labs.demo;

import com.bsuir.labs.demo.models.Models;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class DemoApplicationTests {
    Models eq;
    @Test
    void testMax1() {
        eq = new Models(1.0F, 2.2F, 1F);
        Assertions.assertEquals(2.2F, eq.checkMax());
    }
    @Test
    void testMax2() {
        Exception exception = assertThrows(ResponseStatusException.class, () -> new Models(-5F, 2F, 3F));
        Assertions.assertEquals(ResponseStatusException.class, exception.getClass());
    }
}
