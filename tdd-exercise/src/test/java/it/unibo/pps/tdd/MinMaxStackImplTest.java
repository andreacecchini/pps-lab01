package it.unibo.pps.tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private MinMaxStack stackUnderTest;

    @BeforeEach
    void beforeEach() {
        this.stackUnderTest = new MinMaxStackImpl();
    }
}
