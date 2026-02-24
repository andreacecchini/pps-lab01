package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private static final int PIN = 1234;
    private static final int WRONG_PIN = 0;
    private static final int MAX_ATTEMPTS = 10;
    private SmartDoorLock smartDoorLockUnderTest;

    @BeforeEach
    void beforeEach() {
        this.smartDoorLockUnderTest = new SmartDoorLockImpl(MAX_ATTEMPTS);
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

    @Test
    void testShouldNotBePossibleToUnlockWithTheWrongPin() {
        this.smartDoorLockUnderTest.setPin(PIN);
        this.smartDoorLockUnderTest.lock();
        this.smartDoorLockUnderTest.unlock(WRONG_PIN);
        assertTrue(this.smartDoorLockUnderTest.isLocked());
    }

    @Test
    void testFailedAttemptsShouldIncreaseWhenPinIsWrong() {
        this.smartDoorLockUnderTest.setPin(PIN);
        this.smartDoorLockUnderTest.lock();
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            this.smartDoorLockUnderTest.unlock(WRONG_PIN);
            assertEquals(i, this.smartDoorLockUnderTest.getFailedAttempts());
        }
    }

    @Test
    void testShouldBeBlockedAfterReachingMaximumAttempts() {
        this.smartDoorLockUnderTest.setPin(PIN);
        this.smartDoorLockUnderTest.lock();
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            this.smartDoorLockUnderTest.unlock(WRONG_PIN);
        }
        assertTrue(this.smartDoorLockUnderTest.isBlocked());
    }

    private void assertIsOpen() {
        assertFalse(this.smartDoorLockUnderTest.isLocked());
        assertFalse(this.smartDoorLockUnderTest.isBlocked());
    }
}
