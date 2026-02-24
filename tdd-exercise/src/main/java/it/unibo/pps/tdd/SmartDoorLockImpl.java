package it.unibo.pps.tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private SmartDoorState state = SmartDoorState.UNLOCKED;
    private int pin;

    private enum SmartDoorState {
        UNLOCKED,
        LOCKED,
        BLOCKED,
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin == pin) {
            this.state = SmartDoorState.UNLOCKED;
        }
    }

    @Override
    public void lock() {
        this.state = SmartDoorState.LOCKED;
    }

    @Override
    public boolean isLocked() {
        return this.state == SmartDoorState.LOCKED;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }
}
