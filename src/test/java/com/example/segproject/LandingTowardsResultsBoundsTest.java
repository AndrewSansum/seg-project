package com.example.segproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class LandingTowardsResultsBoundsTest {
    @Test
    public void LargerDistanceBoundsTest() {
        var calc = new Calculations("01", "Landing", 3600, 3600, 3600, 3600, 0, 4000, "North", 0, 0, 240, 60, 300);
        //should be 3600 not 3700 here
        assertEquals(3600, calc.getNewLDA());
    }

    @Test
    public void EqualDistanceBoundsTest() {
        var calc = new Calculations("01", "Landing", 3600, 3600, 3600, 3600, 0, 3900, "North", 0, 0, 240, 60, 300);
        //should be 3600 for both so no difference
        assertEquals(3600, calc.getNewLDA());
    }

    @Test
    public void SmallerDistanceBoundsTest() {
        var calc = new Calculations("01", "Landing", 3600, 3600, 3600, 3600, 0, 3600, "North", 0, 0, 240, 60, 300);
        //should be 3300 not 3600 here
        assertEquals(3300, calc.getNewLDA());
    }
}
