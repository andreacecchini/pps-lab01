package it.unibo.pps.tdd;

public final class SmartDoorLockWithPinValidation extends SmartDoorLockDecorator {
    private final PinValidator pinValidator;

    public SmartDoorLockWithPinValidation(SmartDoorLock wrapped, PinValidator pinValidator) {
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
