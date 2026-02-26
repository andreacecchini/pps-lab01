package it.unibo.pps.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private static final int CAPACITY = 10;
    private CircularQueue circularQueueUnderTest;

    @BeforeEach
    void beforeEach() {
        this.circularQueueUnderTest = new CircularList(CAPACITY);
    }

    @Test
    public void todo() {
        assertTrue(true);
    }
}
