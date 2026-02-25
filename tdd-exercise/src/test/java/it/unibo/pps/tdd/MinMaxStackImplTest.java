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
    void testSizeShouldBeZeroAfterInitialization() {
        assertEquals(0, this.stackUnderTest.size());
    }

    @Test
    void testShouldBePossibleToPush() {
        int valueToPush = 1;
        this.stackUnderTest.push(valueToPush);
        assertEquals(valueToPush, this.stackUnderTest.peek());
    }

    @Test
    void testSizeShouldIncreaseAfterPushing() {
        int  valueToPush = 1;
        this.stackUnderTest.push(valueToPush);
        assertEquals(1, this.stackUnderTest.size());
    }

    @Test
    void testShouldNotBeEmptyAfterPushing() {
        int valueToPush = 1;
        this.stackUnderTest.push(valueToPush);
        assertFalse(this.stackUnderTest.isEmpty());
    }

    @Test
    void testShouldBePossibleToPopIfNotEmpty() {
        int valueToPop = 1;
        this.stackUnderTest.push(valueToPop);
        assertDoesNotThrow(() ->  this.stackUnderTest.pop());
    }

    @Test
    void testPopShouldRetrieveTheLastValuePushed() {
        int firstValueToPush = 1;
        int secondValueToPush = 2;
        this.stackUnderTest.push(firstValueToPush);
        this.stackUnderTest.push(secondValueToPush);
        int poppedValue = this.stackUnderTest.pop();
        assertEquals(secondValueToPush, poppedValue);
    }

    @Test
    void testPeekShouldRetrieveTheLastValuePushedWithoutRemovingIt() {
        int firstValueToPush = 1;
        int secondValueToPush = 2;
        this.stackUnderTest.push(firstValueToPush);
        this.stackUnderTest.push(secondValueToPush);
        int peekedValue = this.stackUnderTest.peek();
        assertEquals(secondValueToPush, peekedValue);
        assertEquals(2, this.stackUnderTest.peek());
    }

    @Test
    void testShouldThrowIllegalStateExceptionWhenPoppingFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> this.stackUnderTest.pop());
    }

    @Test
    void testShouldThrowIllegalStateExceptionWhenPeekingToEmptyStack() {
        assertThrows(IllegalStateException.class, () -> this.stackUnderTest.peek());
    }

    @Test
    void testShouldThrowIllegalStateExceptionWhenGettingMaxFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> this.stackUnderTest.getMax());
    }

    @Test
    void testShouldThrowIllegalStateExceptionWhenGettingMinFromEmptyStack() {
        assertThrows(IllegalStateException.class, () -> this.stackUnderTest.getMin());
    }

}
