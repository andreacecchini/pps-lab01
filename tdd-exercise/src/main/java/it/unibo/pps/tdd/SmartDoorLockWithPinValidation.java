package it.unibo.pps.tdd;

/**
 * A {@link SmartDoorLockDecorator} for a {@link SmartDoorLock} that adds
 * pin validation.
 */
final class SmartDoorLockWithPinValidation extends SmartDoorLockDecorator {
    private final PinValidator pinValidator;

    SmartDoorLockWithPinValidation(SmartDoorLock wrapped, PinValidator pinValidator) {
        super(wrapped);
        this.pinValidator = pinValidator;
    }

    @Override
    public void setPin(int pin) {
        if (!pinValidator.validate(pin)) {
            throw new IllegalArgumentException();
        }
        super.setPin(pin);
    }
}
