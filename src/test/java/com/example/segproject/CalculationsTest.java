package com.example.segproject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculationsTest {
    private static Stream<Arguments> calculationsTestParamProvider() {
        return Stream.of(
            Arguments.of(3884, 3884, 3962, 3884, 12, 3646, 0, 2986, 2986, 2986, 3346)
        );
    }

    @ParameterizedTest
    @MethodSource("calculationsTestParamProvider")
    @DisplayName("Correct distances for takeoff")
    public void testTakeOff(int tora, int asda, int toda, int lda, int obHeight, int obDistance, int displacement, int expTora, int expAsda, int expToda, int expLda) {
        var calculator = new Calculations("01", "Takeoff", tora, asda, toda, lda, obHeight, obDistance, "North", 0, displacement, 240, 60, 300);
        assertEquals(expTora, calculator.getNewTORA(), "TORA is incorrect");
        assertEquals(expAsda, calculator.getNewASDA(), "ASDA is incorrect");
        assertEquals(expToda, calculator.getNewTODA(), "TODA is incorrect");
    }

    @ParameterizedTest
    @MethodSource("calculationsTestParamProvider")
    @DisplayName("Correct distances for landing")
    public void testLanding(int tora, int asda, int toda, int lda, int obHeight, int obDistance, int displacement, int expTora, int expAsda, int expToda, int expLda) {
        var calculator = new Calculations("01", "Landing", tora, asda, toda, lda, obHeight, obDistance, "North", 0, displacement, 240, 60, 300);
        assertEquals(expLda, calculator.getNewLDA(), "LDA is incorrect");
    }
}