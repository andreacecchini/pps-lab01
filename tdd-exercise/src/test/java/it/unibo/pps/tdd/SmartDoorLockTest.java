package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoorLockUnderTest;

    @BeforeEach
    void beforeEach() {
        this.smartDoorLockUnderTest = new SmartDoorLockImpl();
    }

    @Test
    void testShouldBeOpenAtFirst() {
        assertFalse(this.smartDoorLockUnderTest.isLocked());
        assertFalse(this.smartDoorLockUnderTest.isBlocked());
    }

    @Test
    void testShouldBePossibleToSetPinAfterInitialization() {
        assertDoesNotThrow(() -> this.smartDoorLockUnderTest.setPin(1234));
        assertFalse(this.smartDoorLockUnderTest.isLocked());
        assertFalse(this.smartDoorLockUnderTest.isBlocked());
    }
}
