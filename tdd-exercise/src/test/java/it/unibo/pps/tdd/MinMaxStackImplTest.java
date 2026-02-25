package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private MinMaxStack stackUnderTest;

    @BeforeEach
    void beforeEach() {
        this.stackUnderTest = new MinMaxStackImpl();
    }

    @Test
    void testShouldBeEmptyAfterInitialization() {
        assertTrue(this.stackUnderTest.isEmpty());
    }

    @Test
    void testShouldBePossibleToPush() {
        int valueToPush = 1;
        this.stackUnderTest.push(valueToPush);
        assertEquals(valueToPush, this.stackUnderTest.peek());
    }
}
