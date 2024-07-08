package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

public class FutureTest {
    @Test
    void futureTest() throws Exception {
        try (var executor = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            var future = executor.submit(() -> "hello, world!");
            Thread.sleep(100);
            var result = switch (future.state()) {
                case CANCELLED, FAILED -> throw new IllegalStateException("couldn't finish the work!");
                case SUCCESS -> future.resultNow();
                default -> null;
            };
            Assertions.assertEquals( "hello, world!",result);
        }
    }
}
