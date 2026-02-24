package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockWithMaxAttemptsTest extends SmartDoorLockTest {
    private static final int MAX_PIN_LENGTH = 4;
    private static final int PIN = 1234;
    private static final int WRONG_PIN = 0;
    private static final int MAX_ATTEMPTS = 10;

    @Override
    protected SmartDoorLock createSmartDoorLock() {
        final PinValidator lengthValidator = pin -> pin >= Math.powExact(10, MAX_PIN_LENGTH - 1) && pin <= Math.powExact(10, MAX_PIN_LENGTH) - 1;
        return new SmartDoorWithMaxAttempts(MAX_ATTEMPTS, lengthValidator);
    }

    @Test
    void testMaxNumberOfAttemptsShouldCorrectlySet() {
        assertEquals(MAX_ATTEMPTS, this.smartDoorLockUnderTest.getMaxAttempts());
    }


    @Test
    void testAttemptsShouldBeZeroAfterLock() {
        lockSmartDoor();
        assertEquals(0, this.smartDoorLockUnderTest.getFailedAttempts());
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
