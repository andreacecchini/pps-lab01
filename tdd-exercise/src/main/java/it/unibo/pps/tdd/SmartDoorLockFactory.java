package it.unibo.pps.tdd;

public class SmartDoorLockFactory {
    public static SmartDoorLock createFullFeaturedSmartDoorLock(PinValidator pinValidator, int maxAttempts) {
        return new SmartDoorWithMaxAttempts(
                new SmartDoorLockWithPinValidation(
                        new BasicSmartDoorLock(), pinValidator), maxAttempts);
    }
}
