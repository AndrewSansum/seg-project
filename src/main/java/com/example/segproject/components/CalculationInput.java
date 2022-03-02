package com.example.segproject.components;

import com.example.segproject.Calculations;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class CalculationInput extends VBox {
    Calculations cal;

    public CalculationInput(){
        Label nameLabel = new Label("Runway Designator:");
        HBox nameBox = new HBox();
        TextField nameText = new TextField();
        nameBox.getChildren().add(nameText);
        this.getChildren().add(nameLabel);
        this.getChildren().add(nameBox);

        Label toraLabel = new Label("Original TORA:");
        HBox toraBox = new HBox();
        TextField toraText = new TextField();
        Label toraMeasure = new Label("Meters");
        toraBox.getChildren().add(toraText);
        toraBox.getChildren().add(toraMeasure);
        this.getChildren().add(toraLabel);
        this.getChildren().add(toraBox);

        Label asdaLabel = new Label("Original ASDA:");
        HBox asdaBox = new HBox();
        TextField asdaText = new TextField();
        Label asdaMeasure = new Label("Meters");
        asdaBox.getChildren().add(asdaText);
        asdaBox.getChildren().add(asdaMeasure);
        this.getChildren().add(asdaLabel);
        this.getChildren().add(asdaBox);

        Label todaLabel = new Label("Original TODA:");
        HBox todaBox = new HBox();
        TextField todaText = new TextField();
        Label todaMeasure = new Label("Meters");
        todaBox.getChildren().add(todaText);
        todaBox.getChildren().add(todaMeasure);
        this.getChildren().add(todaLabel);
        this.getChildren().add(todaBox);

        Label ldaLabel = new Label("Original LDA:");
        HBox ldaBox = new HBox();
        TextField ldaText = new TextField();
        Label ldaMeasure = new Label("Meters");
        ldaBox.getChildren().add(ldaText);
        ldaBox.getChildren().add(ldaMeasure);
        this.getChildren().add(ldaLabel);
        this.getChildren().add(ldaBox);

        Label obstacleHeightLabel = new Label("Obstacle Height:");
        HBox obstacleHeightBox = new HBox();
        TextField obstacleHeightText = new TextField();
        Label obstacleHeightMeasure = new Label("Meters");
        obstacleHeightBox.getChildren().add(obstacleHeightText);
        obstacleHeightBox.getChildren().add(obstacleHeightMeasure);
        this.getChildren().add(obstacleHeightLabel);
        this.getChildren().add(obstacleHeightBox);

        Label obstacleDistanceLabel = new Label("Obstacle Distance From Centerline:");
        HBox obstacleDistanceBox = new HBox();
        TextField obstacleDistanceText = new TextField();
        Label obstacleDistanceMeasure = new Label("Meters");
        obstacleDistanceBox.getChildren().add(obstacleDistanceText);
        obstacleDistanceBox.getChildren().add(obstacleDistanceMeasure);
        this.getChildren().add(obstacleDistanceLabel);
        this.getChildren().add(obstacleDistanceBox);

        Label directionLabel = new Label("Direction of Obstacle From Centerline:");
        HBox directionBox = new HBox();
        TextField directionText = new TextField();
        directionBox.getChildren().add(directionText);
        this.getChildren().add(directionLabel);
        this.getChildren().add(directionBox);

        Label distanceLabel = new Label("Distance From Threshold to Obstacle:");
        HBox distanceBox = new HBox();
        TextField distanceText = new TextField();
        Label distanceMeasure = new Label("Meters");
        distanceBox.getChildren().add(distanceText);
        distanceBox.getChildren().add(distanceMeasure);
        this.getChildren().add(distanceLabel);
        this.getChildren().add(distanceBox);

        Label displacementLabel = new Label("Displacement Threshold:");
        HBox displacementBox = new HBox();
        TextField displacementText = new TextField();
        Label displacementMeasure = new Label("Meters");
        displacementBox.getChildren().add(displacementText);
        displacementBox.getChildren().add(displacementMeasure);
        this.getChildren().add(displacementLabel);
        this.getChildren().add(displacementBox);

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
            String h = obstacleDistanceText.getText();
            String i = directionText.getText();
            String j = distanceText.getText();
            String k = displacementText.getText();
            cal = new Calculations(a,b,c,d,f,g,h,i,j,k);
            //System.out.println(a);
        });
    }
}
