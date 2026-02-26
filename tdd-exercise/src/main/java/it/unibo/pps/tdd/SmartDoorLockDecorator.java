package it.unibo.pps.tdd;

public abstract class SmartDoorLockDecorator implements SmartDoorLock {
    protected SmartDoorLock decorated;

    public SmartDoorLockDecorator(SmartDoorLock decorated) {
        this.decorated = decorated;
    }

    @Override
    public void setPin(int pin) {
        this.decorated.setPin(pin);
    }

    @Override
    public void unlock(int pin) {
        this.decorated.unlock(pin);
    }

    @Override
    public void lock() {
        this.decorated.lock();
    }

    @Override
    public boolean isLocked() {
        return this.decorated.isLocked();
    }

    @Override
    public boolean isBlocked() {
        return this.decorated.isBlocked();
    }

    @Override
    public int getMaxAttempts() {
        return this.decorated.getMaxAttempts();
    }

    @Override
    public int getFailedAttempts() {
        return this.decorated.getFailedAttempts();
    }

    @Override
    public void reset() {
        this.decorated.reset();
    }
}
