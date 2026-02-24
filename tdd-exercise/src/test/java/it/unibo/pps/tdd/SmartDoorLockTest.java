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

    @Test
    void testShouldBePossibleToLockAfterPinIsSet() {
        this.smartDoorLockUnderTest.setPin(PIN);
        assertDoesNotThrow(() -> this.smartDoorLockUnderTest.lock());
        assertTrue(this.smartDoorLockUnderTest.isLocked());
    }

    @Test
    void testAttemptsShouldBeZeroAfterLock() {
        this.smartDoorLockUnderTest.setPin(PIN);
        this.smartDoorLockUnderTest.lock();
        assertEquals(0, this.smartDoorLockUnderTest.getFailedAttempts());
    }

    @Test
    void testShouldBePossibleToUnlockWithTheRightPin() {
        this.smartDoorLockUnderTest.setPin(PIN);
        this.smartDoorLockUnderTest.lock();
        this.smartDoorLockUnderTest.unlock(PIN);
        assertIsOpen();
    }
    
    private void assertIsOpen() {
        assertFalse(this.smartDoorLockUnderTest.isLocked());
        assertFalse(this.smartDoorLockUnderTest.isBlocked());
    }
}
