package com.example.segproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculationsTest {
    @Test
    @DisplayName("Correct distances for takeoff")
    public void testTakeOff() {
        var calculator = new Calculations("01", "Takeoff", 3884, 3884, 3962, 3884, 12, 3646, "North", 0, 0, 240, 60, 300);
        assertEquals(2986, calculator.getNewTORA(), "TORA is incorrect");
        assertEquals(2986, calculator.getNewASDA(), "ASDA is incorrect");
        assertEquals(2986, calculator.getNewTODA(), "TODA is incorrect");
    }

    @Test
    @DisplayName("Correct distances for landing")
    public void testLanding() {
        var calculator = new Calculations("01", "Landing", 3884, 3884, 3962, 3884, 12, 3646, "North", 0, 0, 240, 60, 300);
        assertEquals(3346, calculator.getNewLDA(), "LDA is incorrect");
    }
}
