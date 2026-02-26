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
        int value = 1;
        this.circularQueueUnderTest.enqueue(value);
        assertFalse(this.circularQueueUnderTest.isEmpty());
    }

    @Test
    void testPeekShouldReturnsTheLastElementEnqueuedWithoutRemovingIt() {
        int firstValue = 1;
        int secondValue = 2;
        this.circularQueueUnderTest.enqueue(firstValue);
        this.circularQueueUnderTest.enqueue(secondValue);
        int previousSize = this.circularQueueUnderTest.size();
        assertEquals(Optional.of(firstValue), this.circularQueueUnderTest.peek());
        assertEquals(previousSize, this.circularQueueUnderTest.size());
    }

    @Test
    void testDequeueShouldReturnsTheLastElementEnqueuedRemovingIt() {
        int firstValue = 1;
        int secondValue = 2;
        this.circularQueueUnderTest.enqueue(firstValue);
        this.circularQueueUnderTest.enqueue(secondValue);
        int previousSize = this.circularQueueUnderTest.size();
        assertEquals(Optional.of(firstValue), this.circularQueueUnderTest.dequeue());
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
        int newValue = 10;
        this.circularQueueUnderTest.enqueue(newValue);
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
        dequeueElements(CAPACITY - 1);
        assertEquals(Optional.of(newValue), this.circularQueueUnderTest.peek());
    }

    @Test
    void testShouldBeEmptyAfterQueueingAllElements() {
        fillTheQueue();
        dequeueElements(CAPACITY);
        assertEquals(0, this.circularQueueUnderTest.size());
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

    private void dequeueElements(int numOfElementsToRemove) {
        for (int i = 0; i < numOfElementsToRemove; i++) {
            this.circularQueueUnderTest.dequeue();
        }
    }
}
