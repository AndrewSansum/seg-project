package com.example.segproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TakeoffTowardsResultsBoundsTest {
    @Test
    public void LargerDistanceBoundsTest() {
        var calc = new Calculations("01", "Takeoff", 3600, 3600, 3600, 3600, 0, 4000, "North", 0, 0, 240, 60, 300);
        //should be 3600 not 3800
        var expected = 3600;
        assertEquals(expected, calc.getNewTORA());
        assertEquals(expected, calc.getNewTODA());
        assertEquals(expected, calc.getNewASDA());
    }

    @Test
    public void EqualDistanceBoundsTest() {
        var calc = new Calculations("01", "Takeoff", 3600, 3600, 3600, 3600, 0, 3900, "North", 0, 0, 240, 60, 300);
        //should be 3600 either way
        var expected = 3600;
        assertEquals(expected, calc.getNewTORA());
        assertEquals(expected, calc.getNewTODA());
        assertEquals(expected, calc.getNewASDA());
    }

    @Test
    public void SmallerDistanceBoundsTest() {
        var calc = new Calculations("01", "Takeoff", 3600, 3600, 3600, 3600, 0, 3800, "North", 0, 0, 240, 60, 300);
        //should be 3600 not 3800
        var expected = 3500;
        assertEquals(expected, calc.getNewTORA());
        assertEquals(expected, calc.getNewTODA());
        assertEquals(expected, calc.getNewASDA());
    }
}
