package it.unibo.pps.tdd;

public class SmartDoorLockImpl implements SmartDoorLock {
    private SmartDoorState state = SmartDoorState.UNLOCKED;

    private enum SmartDoorState {
        UNLOCKED,
        LOCKED,
        BLOCKED,
    }

    @Override
    public void setPin(int pin) {

    }

    @Override
    public void unlock(int pin) {

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
