package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecordTest {
    record User(Integer id, String name) {}

    @Test
    void records() throws Exception {
        var event = new User(1,"Test");
        Assertions.assertEquals( event.name() , "Test");
        System.out.println(event);
    }
}
