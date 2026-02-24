package it.unibo.pps.tdd;

import java.util.Optional;

public class SmartDoorWithMaxAttempts implements SmartDoorLock {
    private static final int MAX_PIN_LENGTH = 4;
    private SmartDoorState state = SmartDoorState.UNLOCKED;
    private Optional<Integer> pin;
    private int numberOfAttempts = 0;
    private final int maxNumberOfAttempts;

    public SmartDoorWithMaxAttempts(int maxNumberOfAttempts) {
        this.maxNumberOfAttempts = maxNumberOfAttempts;
    }

    private enum SmartDoorState {
        UNLOCKED,
        LOCKED,
        BLOCKED,
    }

    @Override
    public void setPin(int pin) {
        if (this.state == SmartDoorState.UNLOCKED) {
            if (!hasRightLength(pin)) {
                throw new IllegalArgumentException("Invalid pin: should have 4 digits.");
            }
            this.pin = Optional.of(pin);
        }
    }

    @Override
    public void unlock(int pin) {
        if (isUnlockingAllowed()) {
            if (this.pin.get() == pin) {
                this.state = SmartDoorState.UNLOCKED;
            } else {
                increaseNumberOfAttempts();
            }
        }
    }

    @Override
    public void lock() {
        this.state = this.pin
                .map(_ -> SmartDoorState.LOCKED)
                .orElseThrow(IllegalStateException::new);
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
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return this.numberOfAttempts;
    }

    @Override
    public void reset() {
        this.state = SmartDoorState.UNLOCKED;
        this.pin = Optional.empty();
    }

    private void increaseNumberOfAttempts() {
        this.numberOfAttempts++;
        if (this.numberOfAttempts == this.maxNumberOfAttempts) {
            this.state = SmartDoorState.BLOCKED;
        }
    }

    private boolean isUnlockingAllowed() {
        return this.state == SmartDoorState.LOCKED && this.pin.isPresent();
    }

    private boolean hasRightLength(int pin) {
        return pin >= 1000 && pin <= 9999;
    }
}
