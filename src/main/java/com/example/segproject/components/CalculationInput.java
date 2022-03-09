package com.example.segproject.components;

import java.util.Arrays;
import java.util.List;

import com.example.segproject.Calculations;

import javafx.scene.control.Alert;
import com.example.segproject.events.CalculateButtonListener;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CalculationInput extends VBox {
    Calculations cal;

    private CalculateButtonListener buttonClickedListener;

    public CalculationInput(){
        Label nameLabel = new Label("Runway Designator:");
        HBox nameBox = new HBox();
        RunwayDesignatorInput nameText = new RunwayDesignatorInput();
        nameBox.getChildren().add(nameText);
        this.getChildren().add(nameLabel);
        this.getChildren().add(nameBox);

        Label statusLabel = new Label("Takeoff or Landing");
        HBox statusBox = new HBox();
        ChoiceBox<String> statusChoice = new ChoiceBox<String>();
        statusChoice.getItems().addAll("Takeoff", "Landing");
        statusBox.getChildren().add(statusChoice);
        this.getChildren().add(statusLabel);
        this.getChildren().add(statusBox);

        Label toraLabel = new Label("Original TORA:");
        HBox toraBox = new HBox();
        TextField toraText = new IntegerField();
        Label toraMeasure = new Label("Meters");
        toraBox.getChildren().add(toraText);
        toraBox.getChildren().add(toraMeasure);
        this.getChildren().add(toraLabel);
        this.getChildren().add(toraBox);

        Label asdaLabel = new Label("Original ASDA:");
        HBox asdaBox = new HBox();
        TextField asdaText = new IntegerField();
        Label asdaMeasure = new Label("Meters");
        asdaBox.getChildren().add(asdaText);
        asdaBox.getChildren().add(asdaMeasure);
        this.getChildren().add(asdaLabel);
        this.getChildren().add(asdaBox);

        Label todaLabel = new Label("Original TODA:");
        HBox todaBox = new HBox();
        TextField todaText = new IntegerField();
        Label todaMeasure = new Label("Meters");
        todaBox.getChildren().add(todaText);
        todaBox.getChildren().add(todaMeasure);
        this.getChildren().add(todaLabel);
        this.getChildren().add(todaBox);

        Label ldaLabel = new Label("Original LDA:");
        HBox ldaBox = new HBox();
        TextField ldaText = new IntegerField();
        Label ldaMeasure = new Label("Meters");
        ldaBox.getChildren().add(ldaText);
        ldaBox.getChildren().add(ldaMeasure);
        this.getChildren().add(ldaLabel);
        this.getChildren().add(ldaBox);

        Label obstacleHeightLabel = new Label("Obstacle Height:");
        HBox obstacleHeightBox = new HBox();
        TextField obstacleHeightText = new IntegerField();
        Label obstacleHeightMeasure = new Label("Meters");
        obstacleHeightBox.getChildren().add(obstacleHeightText);
        obstacleHeightBox.getChildren().add(obstacleHeightMeasure);
        this.getChildren().add(obstacleHeightLabel);
        this.getChildren().add(obstacleHeightBox);

        Label distanceLabel = new Label("Distance From Threshold to Obstacle:");
        HBox distanceBox = new HBox();
        TextField distanceText = new IntegerField();
        Label distanceMeasure = new Label("Meters");
        distanceBox.getChildren().add(distanceText);
        distanceBox.getChildren().add(distanceMeasure);
        this.getChildren().add(distanceLabel);
        this.getChildren().add(distanceBox);

        Label obstacleDistanceLabel = new Label("Obstacle Distance From Centerline:");
        HBox obstacleDistanceBox = new HBox();
        TextField obstacleDistanceText = new IntegerField();
        Label obstacleDistanceMeasure = new Label("Meters");
        obstacleDistanceBox.getChildren().add(obstacleDistanceText);
        obstacleDistanceBox.getChildren().add(obstacleDistanceMeasure);
        this.getChildren().add(obstacleDistanceLabel);
        this.getChildren().add(obstacleDistanceBox);

        Label directionLabel = new Label("Direction of Obstacle From Centerline:");
        HBox directionBox = new HBox();
        ChoiceBox<String> directionChoice = new ChoiceBox<String>();
        directionChoice.getItems().addAll("North", "East", "South", "West");
        directionBox.getChildren().add(directionChoice);
        this.getChildren().add(directionLabel);
        this.getChildren().add(directionBox);

        Label displacementLabel = new Label("Displacement Threshold:");
        HBox displacementBox = new HBox();
        TextField displacementText = new IntegerField();
        Label displacementMeasure = new Label("Meters");
        displacementBox.getChildren().add(displacementText);
        displacementBox.getChildren().add(displacementMeasure);
        this.getChildren().add(displacementLabel);
        this.getChildren().add(displacementBox);

        Label resaLabel = new Label("RESA:");
        HBox resaBox = new HBox();
        TextField resaText = new IntegerField();
        Label resaMeasure = new Label("Meters");
        resaBox.getChildren().add(resaText);
        resaBox.getChildren().add(resaMeasure);
        this.getChildren().add(resaLabel);
        this.getChildren().add(resaBox);

        Label stripEndLabel = new Label("Strip End Length:");
        HBox stripEndBox = new HBox();
        TextField stripEndText = new IntegerField();
        Label stripEndMeasure = new Label("Meters");
        stripEndBox.getChildren().add(stripEndText);
        stripEndBox.getChildren().add(stripEndMeasure);
        this.getChildren().add(stripEndLabel);
        this.getChildren().add(stripEndBox);

        Label blastProtectionLabel = new Label("Airplane Blast Protection Length:");
        HBox blastProtectionBox = new HBox();
        TextField blastProtectionText = new IntegerField();
        Label blastProtectionMeasure = new Label("Meters");
        blastProtectionBox.getChildren().add(blastProtectionText);
        blastProtectionBox.getChildren().add(blastProtectionMeasure);
        this.getChildren().add(blastProtectionLabel);
        this.getChildren().add(blastProtectionBox);

        //Label obstacleHeightDirectionLabel = new Label("Runway Designator To Which Obstacle Max Height Is Closer:");
        //HBox obstacleHeightDirectionBox = new HBox();
        //TextField obstacleHeightDirectionText = new TextField();
        //Label obstacleHeightDirectionMeasure = new Label("Meters");
        //obstacleHeightDirectionBox.getChildren().add(obstacleHeightDirectionText);
        //obstacleHeightDirectionBox.getChildren().add(obstacleHeightDirectionMeasure);
        //this.getChildren().add(obstacleHeightDirectionLabel);
        //this.getChildren().add(obstacleHeightDirectionBox);

        //Region blank = new Region();
        //this.setVgrow(blank, Priority.ALWAYS);
        //this.getChildren().add(blank);

        Button calculate = new Button("Calculate");
        this.getChildren().add(calculate);

        calculate.setOnAction(e -> {
            String name = nameText.getText();
            String status = statusChoice.getValue();
            String toraString = toraText.getText();
            String asdaString = asdaText.getText();
            String todaString = todaText.getText();
            String ldaString = ldaText.getText();
            String obHeightString = obstacleHeightText.getText();
            String distanceString = distanceText.getText();
            String direction = directionChoice.getValue();
            String obDistanceString = obstacleDistanceText.getText();
            String displacementString = displacementText.getText();
            String resaString = resaText.getText();
            String stripEndString = stripEndText.getText();
            String blastProtString = blastProtectionText.getText();

            //check none of the parameters are blank
            List<String> paramList = Arrays.asList(name, status, toraString,asdaString,todaString,ldaString,obHeightString,distanceString,direction,obDistanceString,displacementString,resaString,stripEndString,blastProtString);
            if (paramList.stream().anyMatch(str -> str.isBlank())) {
                new Alert(AlertType.NONE, "Please enter values for all fields.", ButtonType.OK).showAndWait();
                return;
            }

            int tora = Integer.parseInt(toraString);
            int asda = Integer.parseInt(asdaString);
            int toda = Integer.parseInt(todaString);
            int lda = Integer.parseInt(ldaString);
            int obHeight = Integer.parseInt(obHeightString);
            int distance = Integer.parseInt(distanceString);
            int obDistance = Integer.parseInt(obDistanceString);
            int displacement = Integer.parseInt(displacementString);
            int resa = Integer.parseInt(resaString);
            int stripEnd = Integer.parseInt(stripEndString);
            int blastProtection = Integer.parseInt(blastProtString);

            if (Integer.parseInt(ldaString) > Integer.parseInt(toraString)) {
                new Alert(AlertType.NONE, "LDA cannot exceed TORA", ButtonType.OK).showAndWait();
            } else if (Integer.parseInt(displacementString) > Integer.parseInt(toraString)) {
                new Alert(AlertType.NONE, "Displacement Threshold cannot exceed TORA", ButtonType.OK).showAndWait();
            } else if (Integer.parseInt(toraString) > Integer.parseInt(todaString)) {
                new Alert(AlertType.NONE, "TORA cannot exceed TODA", ButtonType.OK).showAndWait();
            } else if (Integer.parseInt(toraString) > Integer.parseInt(asdaString)) {
                new Alert(AlertType.NONE, "TORA cannot exceed ASDA", ButtonType.OK).showAndWait();
            } else {
                cal = new Calculations(name, status, tora, asda, toda, lda, obHeight, distance, direction, obDistance, displacement, resa, stripEnd, blastProtection);
                buttonClicked(cal, e);
            }
            //String o = obstacleHeightDirectionText.getText();
            //System.out.println(a);
        });
    }

    /**
     * Set the listener for when the calculate button is clicked
     * @param listener the listener to be set
     */
    public void setOnButtonClicked(CalculateButtonListener listener) {
        this.buttonClickedListener = listener;
    }

    /**
     * Calls the attached listener
     * @param cal new calculated values
     * @param event the button click event
     */
    private void buttonClicked(Calculations cal, ActionEvent event) {
        if (buttonClickedListener != null) {
            buttonClickedListener.calculateButtonClicked(cal, event);
        }
    }
}
