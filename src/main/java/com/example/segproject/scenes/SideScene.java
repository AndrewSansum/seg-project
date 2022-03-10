package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.components.*;
import com.example.segproject.SceneController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;

/**
 * Class that builds and determines the behaviour of the side view
 */
public class SideScene extends BaseScene {
    private Line displacedThresholdLine = new Line();
    private Line displacedThresholdLineLeft = new Line();
    private Line displacedThresholdLineRight = new Line();
    private Label displacedThresholdLabel = new Label();

    private Line distanceLine = new Line();
    private Line distanceLineLeft = new Line();
    private Line distanceLineRight = new Line();
    private Label distanceLabel = new Label();

    private Line ldaLine = new Line();
    private Line ldaLineLeft = new Line();
    private Line ldaLineRight = new Line();
    private Label ldaLabel = new Label();

    private Line stripEndLine = new Line();
    private Line stripEndLineLeft = new Line();
    private Line stripEndLineRight = new Line();
    private Label stripEndLabel = new Label();

    private Line resaLine = new Line();
    private Line resaLineLeft = new Line();
    private Line resaLineRight = new Line();
    private Label resaLabel = new Label();


    public SideScene (SceneController controller) {
        super(controller);
    }

    /**
     * Builds the ui elements of the scene
     */
    public void build() {
		setupDefaultScene();
        inputs.setOnButtonClicked(this::newValues);

        runway = new Rectangle();
        runway.setWidth(controller.getWidth() * 0.66 - 100);
        runway.setHeight(50);
        runway.setX(runwayPaneCenterX - runway.getWidth() * 0.5);
        runway.setY(runwayPaneCenterY - runway.getHeight() * 0.5);


        obstacle = new Rectangle();
        obstacle.setWidth(100);
        obstacle.setHeight(100);
        obstacle.setX(runway.getX());
        obstacle.setY(runway.getY() - obstacle.getHeight());

        runway.setFill(Color.DARKGRAY);
        obstacle.setFill(Color.ORANGE);

        runwayPane.getChildren().addAll(runway, obstacle);
        runwayPane.getChildren().addAll(displacedThresholdLine, displacedThresholdLineLeft, displacedThresholdLineRight, displacedThresholdLabel);
        runwayPane.getChildren().addAll(distanceLine, distanceLineLeft, distanceLineRight, distanceLabel);
        runwayPane.getChildren().addAll(ldaLine, ldaLineLeft, ldaLineRight, ldaLabel);
        runwayPane.getChildren().addAll(stripEndLine, stripEndLineLeft, stripEndLineRight, stripEndLabel);
        runwayPane.getChildren().addAll(resaLine, resaLineLeft, resaLineRight, resaLabel);

    }

    private void newValues(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);

        runwayLength = cal.getTORA();

        disableDistanceIdicators();

        if (Double.valueOf(cal.getRunwayName().substring(0,2)) <= 18) { // calculating from 01 to 18
            if (cal.getStatus() == "Landing" && cal.getObstacleDistanceFromThreshold() <= runwayLength * 0.5) { // Landing over
                obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacedThreshold()) / (double) runwayLength) * runway.getWidth() + (obstacle.getWidth() * 0.5));

            } else if (cal.getStatus() == "Landing" && cal.getObstacleDistanceFromThreshold() > runwayLength * 0.5) { // Landing towards
                obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacedThreshold()) / (double) runwayLength) * runway.getWidth() + (obstacle.getWidth() * 0.5));
                int lda = cal.getNewLDA();
                int obstacleDistanceFromThreshold = cal.getObstacleDistanceFromThreshold();
                int resa = cal.getRESA();
                int stripEnd = cal.getStripEnd();
                int displacedThreshold = cal.getDisplacedThreshold();

                distanceLine.setDisable(false);
                distanceLineLeft.setDisable(false);
                distanceLineRight.setDisable(false);
                distanceLabel.setDisable(false);

                ldaLine.setDisable(false);
                ldaLineLeft.setDisable(false);
                ldaLineRight.setDisable(false);
                ldaLabel.setDisable(false);

                stripEndLine.setDisable(false);
                stripEndLineLeft.setDisable(false);
                stripEndLineRight.setDisable(false);
                stripEndLabel.setDisable(false);

                resaLine.setDisable(false);
                resaLineLeft.setDisable(false);
                resaLineRight.setDisable(false);
                resaLabel.setDisable(false);

                if (displacedThreshold > 0) {
                    // create line for displaced threshold
                    displacedThresholdLine.setDisable(false);
                    displacedThresholdLineLeft.setDisable(false);
                    displacedThresholdLineRight.setDisable(false);
                    displacedThresholdLabel.setDisable(false);

                    displacedThresholdLine.setLayoutX(runway.getX());
                    displacedThresholdLine.setLayoutY(runway.getY() + runway.getHeight() + 10);
                    displacedThresholdLine.setEndX(((double) displacedThreshold/ (double) runwayLength) * runway.getWidth());

                    displacedThresholdLineLeft.setLayoutX(displacedThresholdLine.getLayoutX());
                    displacedThresholdLineLeft.setLayoutY(displacedThresholdLine.getLayoutY() - 5);
                    displacedThresholdLineLeft.setEndY(10);

                    displacedThresholdLineRight.setLayoutX(displacedThresholdLine.getLayoutX() + displacedThresholdLine.getEndX());
                    displacedThresholdLineRight.setLayoutY(displacedThresholdLine.getLayoutY() - 5);
                    displacedThresholdLineRight.setEndY(10);

                    displacedThresholdLabel.setText("" + displacedThreshold + "m");
                    displacedThresholdLabel.setLayoutY(displacedThresholdLine.getLayoutY() + 1);
                    displacedThresholdLabel.setLayoutX(displacedThresholdLine.getLayoutX() + (displacedThresholdLine.getEndX() * 0.5));
                }

                // create line for distance
                distanceLine.setLayoutX(runway.getX() + ((double) displacedThreshold/ (double) runwayLength) * runway.getWidth());
                distanceLine.setLayoutY(runway.getY() + runway.getHeight() + 10);
                distanceLine.setEndX(((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth());

                distanceLineLeft.setLayoutX(distanceLine.getLayoutX());
                distanceLineLeft.setLayoutY(distanceLine.getLayoutY() - 5);
                distanceLineLeft.setEndY(10);

                distanceLineRight.setLayoutX(distanceLine.getLayoutX() + distanceLine.getEndX());
                distanceLineRight.setLayoutY(distanceLine.getLayoutY() - 5);
                distanceLineRight.setEndY(10);

                distanceLabel.setText("" + obstacleDistanceFromThreshold + "m");
                distanceLabel.setLayoutY(distanceLine.getLayoutY() + 1);
                distanceLabel.setLayoutX(distanceLine.getLayoutX() + (distanceLine.getEndX() * 0.5));

                // create line for lda
                ldaLine.setLayoutX(distanceLine.getLayoutX());
                ldaLine.setLayoutY(distanceLine.getLayoutY() + 20);
                ldaLine.setEndX(((double) lda / (double) runwayLength) * runway.getWidth());

                ldaLineLeft.setLayoutX(ldaLine.getLayoutX());
                ldaLineLeft.setLayoutY(ldaLine.getLayoutY() - 5);
                ldaLineLeft.setEndY(10);

                ldaLineRight.setLayoutX(ldaLine.getLayoutX() + ldaLine.getEndX());
                ldaLineRight.setLayoutY(ldaLine.getLayoutY() - 5);
                ldaLineRight.setEndY(10);

                ldaLabel.setText("" + lda + "m");
                ldaLabel.setLayoutY(ldaLine.getLayoutY() + 1);
                ldaLabel.setLayoutX(ldaLine.getLayoutX() + (ldaLine.getEndX() * 0.5));

                // create line for stripEnd
                stripEndLine.setLayoutX(ldaLine.getLayoutX() + ldaLine.getEndX());
                stripEndLine.setLayoutY(ldaLine.getLayoutY());
                stripEndLine.setEndX(((double) stripEnd / (double) runwayLength) * runway.getWidth());

                stripEndLineLeft.setLayoutX(stripEndLine.getLayoutX());
                stripEndLineLeft.setLayoutY(stripEndLine.getLayoutY() - 5);
                stripEndLineLeft.setEndY(10);

                stripEndLineRight.setLayoutX(stripEndLine.getLayoutX() + stripEndLine.getEndX());
                stripEndLineRight.setLayoutY(stripEndLine.getLayoutY() - 5);
                stripEndLineRight.setEndY(10);

                stripEndLabel.setText("" + stripEnd + "m");
                stripEndLabel.setLayoutY(stripEndLine.getLayoutY() + 1);
                stripEndLabel.setLayoutX(stripEndLine.getLayoutX() + (stripEndLine.getEndX() * 0.5));

                // create line for resa
                resaLine.setLayoutX(stripEndLine.getLayoutX() + stripEndLine.getEndX());
                resaLine.setLayoutY(stripEndLine.getLayoutY());
                resaLine.setEndX(((double) resa / (double) runwayLength) * runway.getWidth());

                resaLineLeft.setLayoutX(resaLine.getLayoutX());
                resaLineLeft.setLayoutY(resaLine.getLayoutY() - 5);
                resaLineLeft.setEndY(10);

                resaLineRight.setLayoutX(resaLine.getLayoutX() + resaLine.getEndX());
                resaLineRight.setLayoutY(resaLine.getLayoutY() - 5);
                resaLineRight.setEndY(10);

                resaLabel.setText("" + resa + "m");
                resaLabel.setLayoutY(resaLine.getLayoutY() + 1);
                resaLabel.setLayoutX(resaLine.getLayoutX() + (resaLine.getEndX() * 0.5));


            } else if (cal.getStatus() == "Takeoff" && cal.getObstacleDistanceFromThreshold() <= runwayLength * 0.5) { // Takeoff away
                obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacedThreshold()) / (double) runwayLength) * runway.getWidth() - (obstacle.getWidth() * 0.5));

            } else if (cal.getStatus() == "Takeoff" && cal.getObstacleDistanceFromThreshold() > runwayLength * 0.5) { // Takeoff towards
                obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacedThreshold()) / (double) runwayLength) * runway.getWidth() - (obstacle.getWidth() * 0.5));

            }

        } else { // calculating from 19 to 36
            if (cal.getStatus() == "Landing" && cal.getObstacleDistanceFromThreshold() <= runwayLength * 0.5) { // Landing over
                obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacedThreshold()) / (double) runwayLength) * runway.getWidth() - (obstacle.getWidth() * 0.5));

            } else if (cal.getStatus() == "Landing" && cal.getObstacleDistanceFromThreshold() > runwayLength * 0.5) { // Landing towards
                obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacedThreshold()) / (double) runwayLength) * runway.getWidth() - (obstacle.getWidth() * 0.5));
                int lda = cal.getNewLDA();
                int obstacleDistanceFromThreshold = cal.getObstacleDistanceFromThreshold();
                int resa = cal.getRESA();
                int stripEnd = cal.getStripEnd();
                int displacedThreshold = cal.getDisplacedThreshold();

                distanceLine.setDisable(false);
                distanceLineLeft.setDisable(false);
                distanceLineRight.setDisable(false);
                distanceLabel.setDisable(false);

                ldaLine.setDisable(false);
                ldaLineLeft.setDisable(false);
                ldaLineRight.setDisable(false);
                ldaLabel.setDisable(false);

                stripEndLine.setDisable(false);
                stripEndLineLeft.setDisable(false);
                stripEndLineRight.setDisable(false);
                stripEndLabel.setDisable(false);

                resaLine.setDisable(false);
                resaLineLeft.setDisable(false);
                resaLineRight.setDisable(false);
                resaLabel.setDisable(false);

               // if (displacedThreshold > 0) {
                    // create line for displaced threshold

              //  }


            } else if (cal.getStatus() == "Takeoff" && cal.getObstacleDistanceFromThreshold() <= runwayLength * 0.5) { // Takeoff away
                obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacedThreshold()) / (double) runwayLength) * runway.getWidth() + (obstacle.getWidth() * 0.5));

            } else if (cal.getStatus() == "Takeoff" && cal.getObstacleDistanceFromThreshold() > runwayLength * 0.5) { // Takeoff towards
                obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacedThreshold()) / (double) runwayLength) * runway.getWidth() + (obstacle.getWidth() * 0.5));

            }

        }

    }

    private void disableDistanceIdicators() {
        displacedThresholdLine.setDisable(true);
        displacedThresholdLineLeft.setDisable(true);
        displacedThresholdLineRight.setDisable(true);
        displacedThresholdLabel.setDisable(true);

        distanceLine.setDisable(true);
        distanceLineLeft.setDisable(true);
        distanceLineRight.setDisable(true);
        distanceLabel.setDisable(true);

        ldaLine.setDisable(true);
        ldaLineLeft.setDisable(true);
        ldaLineRight.setDisable(true);
        ldaLabel.setDisable(true);

        stripEndLine.setDisable(true);
        stripEndLineLeft.setDisable(true);
        stripEndLineRight.setDisable(true);
        stripEndLabel.setDisable(true);

        resaLine.setDisable(true);
        resaLineLeft.setDisable(true);
        resaLineRight.setDisable(true);
        resaLabel.setDisable(true);
    }
}
