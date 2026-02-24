package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public abstract class SmartDoorLockTest {
    private static final int PIN = 1234;
    private static final int WRONG_PIN = 0;
    protected SmartDoorLock smartDoorLockUnderTest;

    @BeforeEach
    void beforeEach() {
        this.smartDoorLockUnderTest = createSmartDoorLock();
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
    void testShouldNotBePossibleToSetPinAfterLocking() {
        final var newPin = 4321;
        lockSmartDoor();
        this.smartDoorLockUnderTest.setPin(newPin);
        this.smartDoorLockUnderTest.unlock(newPin);
        assertTrue(this.smartDoorLockUnderTest.isLocked());
        this.smartDoorLockUnderTest.unlock(PIN);
        assertIsOpen();
    }

    @Test
    void testShouldBePossibleToLockAfterPinIsSet() {
        this.smartDoorLockUnderTest.setPin(PIN);
        assertDoesNotThrow(() -> this.smartDoorLockUnderTest.lock());
        assertTrue(this.smartDoorLockUnderTest.isLocked());
    }

    @Test
    void testShouldBePossibleToUnlockWithTheRightPin() {
        lockSmartDoor();
        this.smartDoorLockUnderTest.unlock(PIN);
        assertIsOpen();
    }

    @Test
    void testShouldNotBePossibleToUnlockWithTheWrongPin() {
        lockSmartDoor();
        this.smartDoorLockUnderTest.unlock(WRONG_PIN);
        assertTrue(this.smartDoorLockUnderTest.isLocked());
    }

    @Test
    void testPinShouldNotBeSetAfterReset() {
        this.smartDoorLockUnderTest.setPin(PIN);
        this.smartDoorLockUnderTest.reset();
        assertThrows(IllegalStateException.class, () -> this.smartDoorLockUnderTest.lock());
    }

    protected abstract SmartDoorLock createSmartDoorLock();

    protected void assertIsOpen() {
        assertFalse(this.smartDoorLockUnderTest.isLocked());
        assertFalse(this.smartDoorLockUnderTest.isBlocked());
    }

    protected void lockSmartDoor() {
        this.smartDoorLockUnderTest.setPin(PIN);
        this.smartDoorLockUnderTest.lock();
    }
}
