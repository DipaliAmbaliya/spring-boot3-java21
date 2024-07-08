package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordTest {
    record User(Integer id, String name) {}

    @Test
    void records() throws Exception {
        var event = new User(1,"Test");
        Assertions.assertEquals("Test",event.name());
        System.out.println(event);
    }
}
