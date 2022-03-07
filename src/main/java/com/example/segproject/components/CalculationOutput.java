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
        this.awayTORA = new Text("Away TORA: ");
        this.towardsASDA = new Text("Towards ASDA: ");
        this.awayASDA = new Text("Away ASDA: ");
        this.towardsTODA = new Text("Towards TODA: ");
        this.awayTODA = new Text("Away TODA: ");
        this.towardsLDA = new Text("Towards LDA: ");
        this.awayLDA = new Text("Away LDA: ");
        this.obstacleDirection = new Text("Obstacle Direction: ");
        this.obstacleDistanceFromCenter = new Text("Obstacle Distance from Center: ");
        this.displacementThreshold = new Text("Displacement Threshold: ");

        this.getChildren().add(this.runwayName);
        this.getChildren().add(this.towardsTORA);
        this.getChildren().add(this.awayTORA);
        this.getChildren().add(this.towardsASDA);
        this.getChildren().add(this.awayASDA);
        this.getChildren().add(this.towardsTODA);
        this.getChildren().add(this.awayTODA);
        this.getChildren().add(this.towardsLDA);
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
        this.towardsTORA.setText("Towards TORA: " + cal.getTowardsTORA() + " Meters");
        this.awayTORA.setText("Away TORA: " + cal.getAwayTORA() + " Meters");
        this.towardsASDA.setText("Towards ASDA: " + cal.getTowardsASDA() + " Meters");
        this.awayASDA.setText("Away ASDA: " + cal.getAwayASDA() + " Meters");
        this.towardsTODA.setText("Towards TODA: " + cal.getTowardsTODA() + " Meters");
        this.awayTODA.setText("Away TODA: " + cal.getAwayTODA() + " Meters");
        this.towardsLDA.setText("Towards LDA: " + cal.getTowardsLDA() + " Meters");
        this.awayLDA.setText("Away LDA: " + cal.getAwayLDA() + " Meters");
        this.obstacleDirection.setText("Obstacle Direction: " + cal.getObstacleDirection());
        this.obstacleDistanceFromCenter.setText("Obstacle Distance from Center: " + cal.getObstacleDistanceFromCenter() + " Meters");
        this.displacementThreshold.setText("Displacement Threshold: " + cal.getDisplacementThreshold() + " Meters");
    }
}
