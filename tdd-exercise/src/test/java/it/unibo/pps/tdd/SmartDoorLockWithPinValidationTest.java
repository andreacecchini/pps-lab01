package it.unibo.pps.tdd;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SmartDoorLockWithPinValidationTest extends SmartDoorLockTest {
    private static final int MAX_PIN_LENGTH = 4;

    @Override
    protected SmartDoorLock createSmartDoorLock() {
        final PinValidator lengthValidator = pin -> pin >= Math.powExact(10, MAX_PIN_LENGTH - 1) && pin <= Math.powExact(10, MAX_PIN_LENGTH) - 1;
        return new SmartDoorLockWithPinValidation(new BasicSmartDoorLock(), lengthValidator);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 12, 123, 1234567, -123, -1234})
    void testPinShouldThrowExceptionForInvalidLength(int wrongLengthPin) {
        assertThrows(IllegalArgumentException.class,
                () -> this.smartDoorLockUnderTest.setPin(wrongLengthPin));
    }
}
