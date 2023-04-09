package com.bsuir.labs.demo;


import com.bsuir.labs.demo.models.Models;
import com.bsuir.labs.demo.services.Services;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class DemoApplicationTests {
    Services eq;

    @Test
    void testMaxCorrect() {
        eq = new Services(1.0F, 2.2F, 1F);
        assertEquals(2.2F, eq.checkMax());
    }

    @Test
    void testMaxUncorrect() {
        Exception exception = assertThrows(ResponseStatusException.class, () -> new Models(-5F, 2F, 3F));
        Assertions.assertEquals(ResponseStatusException.class, exception.getClass());
    }

}
