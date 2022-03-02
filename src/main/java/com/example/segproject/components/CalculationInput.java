package com.example.segproject.components;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class CalculationInput extends VBox {
    public CalculationInput(){
        Label toda = new Label("Please Input TODA:");
        this.getChildren().add(toda);
    }
}
