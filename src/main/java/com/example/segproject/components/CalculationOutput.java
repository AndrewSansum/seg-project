package com.example.segproject.components;

import com.example.segproject.Calculations;
import com.example.segproject.events.CalculateButtonListener;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * UI component for displaying the calculated values
 */
public class CalculationOutput extends VBox {
    Calculations cal;

    private Text runwayName;
    private Text towardsTORA;
    private Text awayTORA;
    private Text towardsASDA;
    private Text awayASDA;
    private Text towardsTODA;
    private Text awayTODA;
    private Text towardsLDA;
    private Text awayLDA;
    private Text obstacleDirection;
    private Text obstacleDistanceFromCenter;
    private Text displacementThreshold;

    /**
     * Instantiates the labels
     */
    public CalculationOutput(){
        this.runwayName = new Text("Runway Designator: ");
        this.towardsTORA = new Text("Towards TORA: ");
        this.towardsASDA = new Text("Towards ASDA: ");
        this.towardsTODA = new Text("Towards TODA: ");
        this.towardsLDA = new Text("Towards LDA: ");
        this.awayTORA = new Text("Away TORA: ");
        this.awayASDA = new Text("Away ASDA: ");
        this.awayTODA = new Text("Away TODA: ");
        this.awayLDA = new Text("Away LDA: ");
        this.obstacleDirection = new Text("Obstacle Direction: ");
        this.obstacleDistanceFromCenter = new Text("Obstacle Distance from Center: ");
        this.displacementThreshold = new Text("Displacement Threshold: ");

        this.getChildren().add(this.runwayName);
        this.getChildren().add(this.towardsTORA);
        this.getChildren().add(this.towardsASDA);
        this.getChildren().add(this.towardsTODA);
        this.getChildren().add(this.towardsLDA);
        this.getChildren().add(this.awayTORA);
        this.getChildren().add(this.awayASDA);
        this.getChildren().add(this.awayTODA);
        this.getChildren().add(this.awayLDA);
        this.getChildren().add(this.obstacleDirection);
        this.getChildren().add(this.obstacleDistanceFromCenter);
        this.getChildren().add(this.displacementThreshold);
    }

    /**
     * Updates the values of each label when values are available
     * @param cal the values to be updated
     */
    public void updateValues(Calculations cal) {
        this.cal = cal;
        this.runwayName.setText("Runway Designator: " + cal.getRunwayName());
        this.towardsTORA.setText("Taking Off Towards TORA: " + cal.getTowardsTORA() + " Meters");
        this.towardsASDA.setText("Taking Off Towards ASDA: " + cal.getTowardsASDA() + " Meters");
        this.towardsTODA.setText("Taking Off Towards TODA: " + cal.getTowardsTODA() + " Meters");
        this.towardsLDA.setText("Landing Towards LDA: " + cal.getTowardsLDA() + " Meters");
        this.awayTORA.setText("Taking Off Away TORA: " + cal.getAwayTORA() + " Meters");
        this.awayASDA.setText("Taking Off Away ASDA: " + cal.getAwayASDA() + " Meters");
        this.awayTODA.setText("Taking Off Away TODA: " + cal.getAwayTODA() + " Meters");
        this.awayLDA.setText("Landig Over LDA: " + cal.getAwayLDA() + " Meters");
        this.obstacleDirection.setText("Obstacle Direction: " + cal.getObstacleDirection());
        this.obstacleDistanceFromCenter.setText("Obstacle Distance from Center: " + cal.getObstacleDistanceFromCenter() + " Meters");
        this.displacementThreshold.setText("Displacement Threshold: " + cal.getDisplacementThreshold() + " Meters");
    }
}
