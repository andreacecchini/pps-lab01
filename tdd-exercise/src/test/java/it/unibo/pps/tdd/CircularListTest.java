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
    void testEnqueuingShouldOverwriteFrontIfFull() {
        fillTheQueue();
        int expectedFirst = 1;
        int overwritingValue = CAPACITY;
        this.circularQueueUnderTest.enqueue(overwritingValue);
        assertEquals(Optional.of(expectedFirst), this.circularQueueUnderTest.peek());
    }

    @Test
    void testShouldRemoveInFifoOrder() {
        fillTheQueue();
        for (int i = 0; i < CAPACITY; i++) {
            assertEquals(Optional.of(i), this.circularQueueUnderTest.dequeue());
        }
    }

    @Test
    void testNewElementShouldBeLastIfOverwritesFront() {
        fillTheQueue();
        int overwritingValue = CAPACITY;
        this.circularQueueUnderTest.enqueue(overwritingValue);
        dequeueElements(CAPACITY - 1);
        assertEquals(Optional.of(overwritingValue), this.circularQueueUnderTest.peek());
    }

    @Test
    void testShouldBeEmptyAfterRemovingAllElements() {
        fillTheQueue();
        dequeueElements(CAPACITY);
        assertEquals(0, this.circularQueueUnderTest.size());
        assertTrue(this.circularQueueUnderTest.isEmpty());
        assertEquals(Optional.empty(), this.circularQueueUnderTest.peek());
        assertEquals(Optional.empty(), this.circularQueueUnderTest.dequeue());
    }

    @Test
    void testOverwriteShouldNotIncreaseSize() {
        fillTheQueue();
        int overwritingValue = CAPACITY;
        this.circularQueueUnderTest.enqueue(overwritingValue);
        assertEquals(CAPACITY, this.circularQueueUnderTest.size());
    }

    @Test
    void testSizeShouldNotBeNegative() {
        this.circularQueueUnderTest.dequeue();
        assertEquals(0, this.circularQueueUnderTest.size());
    }

    @Test
    void testShouldMaintainFifoOrderAfterOverwriteAndInterleavedOperations() {
        fillTheQueue();
        int overwritingValue = CAPACITY;
        this.circularQueueUnderTest.enqueue(overwritingValue);
        this.circularQueueUnderTest.dequeue();
        int subsequentValue = CAPACITY + 1;
        this.circularQueueUnderTest.enqueue(subsequentValue);
        int lastOriginalValue = CAPACITY - 1;
        dequeueElements(CAPACITY - 3);
        assertEquals(Optional.of(lastOriginalValue), this.circularQueueUnderTest.dequeue());
        assertEquals(Optional.of(overwritingValue), this.circularQueueUnderTest.dequeue());
        assertEquals(Optional.of(subsequentValue), this.circularQueueUnderTest.dequeue());
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
