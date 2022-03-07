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
            String a = nameText.getText();
            String b = toraText.getText();
            String c = asdaText.getText();
            String d = todaText.getText();
            String f = ldaText.getText();
            String g = obstacleHeightText.getText();
            String h = distanceText.getText();
            String i = directionChoice.getValue();
            String j = obstacleDistanceText.getText();
            String k = displacementText.getText();

            String l = resaText.getText();
            String m = stripEndText.getText();
            String n = blastProtectionText.getText();

            //check none of the parameters are blank
            List<String> paramList = Arrays.asList(a,b,c,d,f,g,h,i,j,k,l,m,n);
            if (paramList.stream().anyMatch(str -> str.isBlank())) {
                new Alert(AlertType.NONE, "Please enter values for all fields.", ButtonType.OK).showAndWait();
            } else {
                cal = new Calculations(a,b,c,d,f,g,h,i,j,k,l,m,n);
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
