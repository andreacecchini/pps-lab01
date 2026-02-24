package it.unibo.pps.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    @Test
    public void testShouldBeOpenAtFirst() {
        final SmartDoorLock smartDoorLock = new SmartDoorLockImpl();
        assertFalse(smartDoorLock.isLocked());
        assertFalse(smartDoorLock.isBlocked());
    }
}
