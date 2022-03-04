package com.example.segproject.components;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;

/**
 * An input for Runway Designators with format of an
 * integer from 01 to 36 then optionally a
 * choice from L, C or R
 * <p>
 * e.g. 14R, 35, 01L
 */
public class RunwayDesignatorInput extends HBox {
    Spinner<Integer> spinner;
    ChoiceBox<String> positionChoice;

    public RunwayDesignatorInput() {
        spinner = new Spinner<Integer>(1, 36, 1);
        getChildren().add(spinner);

        positionChoice = new ChoiceBox<>();
        positionChoice.getItems().addAll(" ", "L", "C", "R");
        getChildren().add(positionChoice);
    }

    /**
     * Return the entered input for the runway designator
     * in String form
     * @return runway designator string
     */
    public String getText() {
        String num = this.spinner.getValue().toString();
        if (num.length()==1) {
            num = "0".concat(num);
        }
        return num.concat(positionChoice.getValue());
    }
}
