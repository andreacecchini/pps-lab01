package it.unibo.pps.tdd;

public class BasicSmartDoorLockTest extends SmartDoorLockTest {
    @Override
    protected SmartDoorLock createSmartDoorLock() {
        return new BasicSmartDoorLock();
    }
}
