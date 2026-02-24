package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private static final int PIN = 1234;
    private static final int WRONG_PIN = 0;
    private static final int MAX_ATTEMPTS = 10;
    private SmartDoorLock smartDoorLockUnderTest;

    @BeforeEach
    void beforeEach() {
        this.smartDoorLockUnderTest = new SmartDoorWithMaxAttempts(MAX_ATTEMPTS);
    }

    @Test
    void testMaxNumberOfAttemptsShouldCorrectlySet() {
        assertEquals(MAX_ATTEMPTS, this.smartDoorLockUnderTest.getMaxAttempts());
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

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 12, 123, 1234567, -123, -1234})
    void testPinShouldThrowExceptionForInvalidLength(int wrongLengthPin) {
        assertThrows(IllegalArgumentException.class,
                () -> this.smartDoorLockUnderTest.setPin(wrongLengthPin));
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
    void testAttemptsShouldBeZeroAfterLock() {
        lockSmartDoor();
        assertEquals(0, this.smartDoorLockUnderTest.getFailedAttempts());
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
    void testFailedAttemptsShouldIncreaseWhenPinIsWrong() {
        lockSmartDoor();
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            this.smartDoorLockUnderTest.unlock(WRONG_PIN);
            assertEquals(i, this.smartDoorLockUnderTest.getFailedAttempts());
        }
    }

    @Test
    void testShouldBeBlockedAfterReachingMaximumAttempts() {
        blockSmartDoor();
        assertTrue(this.smartDoorLockUnderTest.isBlocked());
    }

    @Test
    void testShouldNotBePossibleToAttemptIfBlocked() {
        blockSmartDoor();
        this.smartDoorLockUnderTest.unlock(PIN);
        assertTrue(this.smartDoorLockUnderTest.isBlocked());
    }

    @Test
    void testShouldBeOpenAfterResetWhenBlocked() {
        blockSmartDoor();
        this.smartDoorLockUnderTest.reset();
        assertIsOpen();
    }

    @Test
    void testPinShouldNotBeSetAfterReset() {
        this.smartDoorLockUnderTest.setPin(PIN);
        this.smartDoorLockUnderTest.reset();
        assertThrows(IllegalStateException.class, () -> this.smartDoorLockUnderTest.lock());
    }

    @Test
    void testNumberOfFailedAttemptsShouldBeZeroAfterReset() {
        blockSmartDoor();
        this.smartDoorLockUnderTest.reset();
        assertEquals(0, this.smartDoorLockUnderTest.getFailedAttempts());
    }

    @Test
    void testNumberOfFailedAttemptsShouldBeZeroAfterUnlocking() {
        failUnlockingUntilLastAttempt();
        this.smartDoorLockUnderTest.unlock(PIN);
        assertEquals(0, this.smartDoorLockUnderTest.getFailedAttempts());
    }

    private void assertIsOpen() {
        assertFalse(this.smartDoorLockUnderTest.isLocked());
        assertFalse(this.smartDoorLockUnderTest.isBlocked());
    }

    private void lockSmartDoor() {
        this.smartDoorLockUnderTest.setPin(PIN);
        this.smartDoorLockUnderTest.lock();
    }

    private void blockSmartDoor() {
        lockSmartDoor();
        for (int i = 1; i <= MAX_ATTEMPTS; i++) {
            this.smartDoorLockUnderTest.unlock(WRONG_PIN);
        }
    }

    private void failUnlockingUntilLastAttempt() {
        lockSmartDoor();
        for (int i = 1; i <= MAX_ATTEMPTS - 1; i++) {
            this.smartDoorLockUnderTest.unlock(WRONG_PIN);
        }
    }
}
