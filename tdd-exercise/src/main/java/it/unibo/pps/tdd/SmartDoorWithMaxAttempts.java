package it.unibo.pps.tdd;

import java.util.Optional;

/**
 * A {@link SmartDoorLock} with a maximum number of attempts.
 */
public class SmartDoorWithMaxAttempts implements SmartDoorLock {
    private SmartDoorState state = SmartDoorState.UNLOCKED;
    private int pin;
    private boolean isPinSet = false;
    private int numberOfAttempts = 0;
    private final int maxNumberOfAttempts;
    private final PinValidator pinValidator;

    public SmartDoorWithMaxAttempts(int maxNumberOfAttempts, PinValidator pinValidator) {
        this.maxNumberOfAttempts = maxNumberOfAttempts;
        this.pinValidator = pinValidator;
    }

    private enum SmartDoorState {
        UNLOCKED,
        LOCKED,
        BLOCKED,
    }

    @Override
    public void setPin(int pin) {
        if (isOpen()) {
            if (!pinValidator.validate(pin)) {
                throw new IllegalArgumentException();
            }
            this.pin = pin;
            this.isPinSet = true;
        }
    }

    @Override
    public void unlock(int pin) {
        if (isLocked()) {
            if (this.pin == pin) {
                unlockSmartDoor();
            } else {
                increaseNumberOfAttempts();
            }
        }
    }

    @Override
    public void lock() {
        if (this.isPinSet) {
            this.state = SmartDoorState.LOCKED;
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean isLocked() {
        return this.state == SmartDoorState.LOCKED;
    }

    @Override
    public boolean isBlocked() {
        return this.state == SmartDoorState.BLOCKED;
    }

    @Override
    public int getMaxAttempts() {
        return this.maxNumberOfAttempts;
    }

    @Override
    public int getFailedAttempts() {
        return this.numberOfAttempts;
    }

    @Override
    public void reset() {
        unlockSmartDoor();
    }

    private void increaseNumberOfAttempts() {
        this.numberOfAttempts++;
        if (this.numberOfAttempts == this.maxNumberOfAttempts) {
            this.state = SmartDoorState.BLOCKED;
        }
    }

    private void unlockSmartDoor() {
        this.state = SmartDoorState.UNLOCKED;
        this.isPinSet = false;
        this.numberOfAttempts = 0;
    }

    private boolean isOpen() {
        return this.state == SmartDoorState.UNLOCKED;
    }
}
