package com.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashSet;
import java.util.SequencedCollection;

class SequencedCollectionTest {

    @Test
    void ordering() throws Exception {
        var list = LinkedHashSet.<String>newLinkedHashSet(100);
        if (list instanceof SequencedCollection<String> sequencedCollection) {
            sequencedCollection.add("B");
            sequencedCollection.add("C");
            sequencedCollection.add("D");
            sequencedCollection.add("E");
            sequencedCollection.add("F");
            sequencedCollection.addFirst("A"); //<1>
            Assertions.assertEquals(sequencedCollection.getFirst(), "A"); // ②
        }
    }
}
