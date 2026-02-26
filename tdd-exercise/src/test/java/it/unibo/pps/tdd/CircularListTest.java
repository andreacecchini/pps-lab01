package it.unibo.pps.tdd;

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
    void testShouldNotBeFullAfterInitialization() {
        assertFalse(this.circularQueueUnderTest.isFull());
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
    void testDequeueShouldReturnsAnEmptyOptionalWhenEmpty() {
        assertEquals(Optional.empty(), this.circularQueueUnderTest.dequeue());
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
        int secondValueToEnqueue = 2;
        this.circularQueueUnderTest.enqueue(firstValueToEnqueue);
        this.circularQueueUnderTest.enqueue(secondValueToEnqueue);
        int previousSize = this.circularQueueUnderTest.size();
        assertEquals(Optional.of(firstValueToEnqueue), this.circularQueueUnderTest.peek());
        assertEquals(previousSize, this.circularQueueUnderTest.size());
    }

    @Test
    void testDequeueShouldReturnsTheLastElementDequeuedRemovingIt() {
        int firstValueToDequeue = 1;
        int secondValueToDequeue = 2;
        this.circularQueueUnderTest.enqueue(firstValueToDequeue);
        this.circularQueueUnderTest.enqueue(secondValueToDequeue);
        int previousSize = this.circularQueueUnderTest.size();
        assertEquals(Optional.of(firstValueToDequeue), this.circularQueueUnderTest.dequeue());
        assertEquals(previousSize - 1, this.circularQueueUnderTest.size());
    }

    @Test
    void testShouldBeFullAfterReachingCapacity() {
        fillTheQueue();
        assertTrue(this.circularQueueUnderTest.isFull());
    }

    @Test
    void testEnqueuingShouldOverwriteTheElderElementIfFull() {
        fillTheQueue();
        int expectedFirst = 1;
        int newElement = 10;
        this.circularQueueUnderTest.enqueue(newElement);
        assertEquals(Optional.of(expectedFirst), this.circularQueueUnderTest.peek());
    }

    @Test
    void testShouldQueueingInCorrectOrder() {
        fillTheQueue();
        for (int i = 0; i < CAPACITY; i++) {
            assertEquals(Optional.of(i), this.circularQueueUnderTest.dequeue());
        }
    }

    @Test
    void testNewElementShouldBeLastIfOverwritesElder() {
        fillTheQueue();
        int newValue = CAPACITY;
        this.circularQueueUnderTest.enqueue(newValue);
        for (int i = 0; i < CAPACITY - 1; i++) {
            this.circularQueueUnderTest.dequeue();
        }
        assertEquals(Optional.of(newValue), this.circularQueueUnderTest.peek());
    }

    @Test
    void testShouldBeEmptyAfterQueueingAllElements() {
        fillTheQueue();
        for (int i = 0; i < CAPACITY; i++) {
            this.circularQueueUnderTest.dequeue();
        }
        assertTrue(this.circularQueueUnderTest.isEmpty());
        assertEquals(Optional.empty(), this.circularQueueUnderTest.peek());
        assertEquals(Optional.empty(), this.circularQueueUnderTest.dequeue());
    }

    @Test
    void testSizeShouldNotIncreaseTheCapacity() {
        fillTheQueue();
        int newValue = 10;
        this.circularQueueUnderTest.enqueue(newValue);
        assertEquals(CAPACITY, this.circularQueueUnderTest.size());
    }

    @Test
    void testSizeShouldNotBeNegative() {
        this.circularQueueUnderTest.dequeue();
        assertEquals(0, this.circularQueueUnderTest.size());
    }

    private void fillTheQueue() {
        for (int i = 0; i < CAPACITY; i++) {
            this.circularQueueUnderTest.enqueue(i);
        }
    }
}
