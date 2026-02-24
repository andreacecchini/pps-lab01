package it.unibo.pps.tdd;

public final class BasicSmartDoorLock implements SmartDoorLock {
    private SmartDoorState state = SmartDoorState.UNLOCKED;
    private boolean isPinSet = false;
    private int pin;

    @Override
    public void setPin(int pin) {
        if (isOpen()) {
            this.pin = pin;
            this.isPinSet = true;
        }
    }

    @Override
    public void unlock(int pin) {
        if (isLocked()) {
            if (this.pin == pin) {
                unlockSmartDoor();
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
        return false;
    }

    @Override
    public int getMaxAttempts() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {
        unlockSmartDoor();
    }

    private boolean isOpen() {
        return this.state == SmartDoorState.UNLOCKED;
    }

    private void unlockSmartDoor() {
        this.state = SmartDoorState.UNLOCKED;
        this.isPinSet = false;
    }
}
