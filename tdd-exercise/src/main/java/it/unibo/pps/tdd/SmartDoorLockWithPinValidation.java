package it.unibo.pps.tdd;

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
