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
        Label nameLabel = new Label("Please Input Name of Runway:");
        HBox nameBox = new HBox();
        TextField nameText = new TextField();
        nameBox.getChildren().add(nameText);
        this.getChildren().add(nameLabel);
        this.getChildren().add(nameBox);

        Label toraLabel = new Label("Please Input Original TORA:");
        HBox toraBox = new HBox();
        TextField toraText = new TextField();
        Label toraMeasure = new Label("Meters");
        toraBox.getChildren().add(toraText);
        toraBox.getChildren().add(toraMeasure);
        this.getChildren().add(toraLabel);
        this.getChildren().add(toraBox);

        Label ldaLabel = new Label("Please Input Original LDA:");
        HBox ldaBox = new HBox();
        TextField ldaText = new TextField();
        Label ldaMeasure = new Label("Meters");
        ldaBox.getChildren().add(ldaText);
        ldaBox.getChildren().add(ldaMeasure);
        this.getChildren().add(ldaLabel);
        this.getChildren().add(ldaBox);

        Label stopwayLabel = new Label("Please Input Stopway length:");
        HBox stopwayBox = new HBox();
        TextField stopwayText = new TextField();
        Label stopwayMeasure = new Label("Meters");
        stopwayBox.getChildren().add(stopwayText);
        stopwayBox.getChildren().add(stopwayMeasure);
        this.getChildren().add(stopwayLabel);
        this.getChildren().add(stopwayBox);

        Label clearwayLabel = new Label("Please Input Clearway length:");
        HBox clearwayBox = new HBox();
        TextField clearwayText = new TextField();
        Label clearwayMeasure = new Label("Meters");
        clearwayBox.getChildren().add(clearwayText);
        clearwayBox.getChildren().add(clearwayMeasure);
        this.getChildren().add(clearwayLabel);
        this.getChildren().add(clearwayBox);

        Label obstacleHeightLabel = new Label("Please Input Obstacle Height:");
        HBox obstacleHeightBox = new HBox();
        TextField obstacleHeightText = new TextField();
        Label obstacleHeightMeasure = new Label("Meters");
        obstacleHeightBox.getChildren().add(obstacleHeightText);
        obstacleHeightBox.getChildren().add(obstacleHeightMeasure);
        this.getChildren().add(obstacleHeightLabel);
        this.getChildren().add(obstacleHeightBox);

        Label obstacleDistanceLabel = new Label("Please Input Obstacle Distance From Centerline:");
        HBox obstacleDistanceBox = new HBox();
        TextField obstacleDistanceText = new TextField();
        Label obstacleDistanceMeasure = new Label("Meters");
        obstacleDistanceBox.getChildren().add(obstacleDistanceText);
        obstacleDistanceBox.getChildren().add(obstacleDistanceMeasure);
        this.getChildren().add(obstacleDistanceLabel);
        this.getChildren().add(obstacleDistanceBox);

        Label directionLabel = new Label("Please Input Direction of Obstacle from centerline:");
        HBox directionBox = new HBox();
        TextField directionText = new TextField();
        directionBox.getChildren().add(directionText);
        this.getChildren().add(directionLabel);
        this.getChildren().add(directionBox);

        Label distanceLabel = new Label("Input Obstacle Distance from Runway Threshold:");
        HBox distanceBox = new HBox();
        TextField distanceText = new TextField();
        Label distanceMeasure = new Label("Meters");
        distanceBox.getChildren().add(distanceText);
        distanceBox.getChildren().add(distanceMeasure);
        this.getChildren().add(distanceLabel);
        this.getChildren().add(distanceBox);

        //Region blank = new Region();
        //this.setVgrow(blank, Priority.ALWAYS);
        //this.getChildren().add(blank);

        Button calculate = new Button("Calculate");
        this.getChildren().add(calculate);

        calculate.setOnAction(e -> {
            String a = nameText.getText();
            String b = toraText.getText();
            String c = ldaText.getText();
            String d = stopwayText.getText();
            String f = clearwayText.getText();
            String g = obstacleHeightText.getText();
            String h = obstacleDistanceText.getText();
            String i = directionText.getText();
            String j = distanceText.getText();
            cal = new Calculations(a,b,c,d,f,g,h,i,j);
            //System.out.println(a);
        });
    }
}
