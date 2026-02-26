package it.unibo.pps.tdd;

/**
 * A functional interface which abstracts the concept of pin validator.
 */
@FunctionalInterface
public interface PinValidator {
    /**
     *
     * @param pin
     *         the pin to validate.
     * @return whether pis is valid.
     */
    boolean validate(int pin);
}
