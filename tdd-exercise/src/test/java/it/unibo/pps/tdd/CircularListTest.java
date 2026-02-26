package it.unibo.pps.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
    void testShouldBeEmptyAfterInitialization() {
        assertTrue(this.circularQueueUnderTest.isEmpty());
    }

    @Test
    void testSizeShouldBeZeroAfterInitialization() {
        assertEquals(0, this.circularQueueUnderTest.size());
    }

    @Test
    void testShouldBePossibleToGetTheCapacity() {
        assertEquals(CAPACITY, this.circularQueueUnderTest.getCapacity());
    }

    @Test
    void testPeekShouldReturnAnEmptyOptionalWhenEmpty() {
        assertEquals(Optional.empty(), this.circularQueueUnderTest.peek());
    }

    @Test
    void testShouldBeNotEmptyAfterEnqueue() {
        int valueToEnqueue = 1;
        this.circularQueueUnderTest.enqueue(valueToEnqueue);
        assertFalse(this.circularQueueUnderTest.isEmpty());
    }

    @Test
    void testPeekShouldReturnsTheLastElementEnqueuedWithoutRemovingIt() {
        int firstValueToEnqueue = 1;
        int secondValueToEnqueue = 1;
        this.circularQueueUnderTest.enqueue(firstValueToEnqueue);
        int previousSize = this.circularQueueUnderTest.size();
        assertEquals(Optional.of(secondValueToEnqueue), this.circularQueueUnderTest.peek());
        assertEquals(previousSize, this.circularQueueUnderTest.size());
    }
}
