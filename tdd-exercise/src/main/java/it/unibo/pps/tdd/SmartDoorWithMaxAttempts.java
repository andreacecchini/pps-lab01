package it.unibo.pps.tdd;

/**
 * A {@link SmartDoorLockDecorator} for a {@link SmartDoorLock} that adds
 * a maximum number of attempts.
 */
public final class SmartDoorWithMaxAttempts extends SmartDoorLockDecorator {
    private boolean isBlocked = false;
    private int numberOfAttempts = 0;
    private final int maxNumberOfAttempts;

    SmartDoorWithMaxAttempts(SmartDoorLock wrapped, int maxNumberOfAttempts) {
        super(wrapped);
        this.maxNumberOfAttempts = maxNumberOfAttempts;
    }

    @Override
    public void unlock(int pin) {
        if (!isBlocked()) {
            super.unlock(pin);
            if (isOpen()) {
                resetNumberOfAttempts();
            } else {
                increaseNumberOfAttempts();
            }
        }
    }

    @Override
    public boolean isBlocked() {
        return this.isBlocked;
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
        super.reset();
        resetNumberOfAttempts();
        unblock();
    }

    private void increaseNumberOfAttempts() {
        this.numberOfAttempts++;
        if (this.numberOfAttempts == this.maxNumberOfAttempts) {
            block();
        }
    }

    private void resetNumberOfAttempts() {
        this.numberOfAttempts = 0;
    }

    private boolean isOpen() {
        return !isBlocked && !isLocked();
    }

    private void block() {
        this.isBlocked = true;
    }

    private void unblock() {
        this.isBlocked = false;
    }
}
