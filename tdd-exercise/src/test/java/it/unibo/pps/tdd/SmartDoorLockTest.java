package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private static final int PIN = 1234;
    private SmartDoorLock smartDoorLockUnderTest;

    @BeforeEach
    void beforeEach() {
        this.smartDoorLockUnderTest = new SmartDoorLockImpl();
    }

    @Test
    void testShouldBeOpenAfterInitialization() {
        assertIsOpen();
    }

    @Test
    void testShouldBePossibleToSetPinAfterInitialization() {
        assertDoesNotThrow(() -> this.smartDoorLockUnderTest.setPin(PIN));
        assertIsOpen();
    }

    private void assertIsOpen() {
        assertFalse(this.smartDoorLockUnderTest.isLocked());
        assertFalse(this.smartDoorLockUnderTest.isBlocked());
    }
}
