package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.components.*;
import com.example.segproject.SceneController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Class that builds and determines the behaviour of the side view
 */
public class SideScene extends BaseScene {

    private Rectangle runway;

    public SideScene(SceneController controller) {
        super(controller);
    }

    /**
     * Builds the ui elements of the scene
     */
    public void build() {
        setupDefaultScene();
        inputs.setOnButtonClicked(this::newValues);

        runway = new Rectangle();
        runway.setWidth(controller.getWidth() * 0.66 - 300);
        runway.setHeight(50);
        runway.setX(runwayPaneCenterX - runway.getWidth() * 0.5);
        runway.setY(runwayPaneCenterY - runway.getHeight() * 0.5);

        Rectangle clearedAndGradedArea = new Rectangle(runway.getX() - 60, runway.getY(), runway.getWidth() + 120,
                runway.getHeight());
        Rectangle lowerBackground = new Rectangle(0, runway.getY(), controller.getWidth() * 0.66,
                controller.getHeight());
        Rectangle upperBackground = new Rectangle(0, 0, controller.getWidth() * 0.66,
                controller.getHeight() * 0.5 - runway.getHeight() * 0.5);

        obstacle = new Rectangle();
        obstacle.setWidth(50);
        obstacle.setHeight(50);
        obstacle.setX(runway.getX());
        obstacle.setY(runway.getY() - obstacle.getHeight());

        lowerBackground.setFill(Color.GREEN);
        upperBackground.setFill(Color.LIGHTCYAN);
        clearedAndGradedArea.setFill(Color.BLUE);
        runway.setFill(Color.DARKGRAY);
        obstacle.setFill(Color.ORANGE);

        runwayPane.getChildren().addAll(lowerBackground, upperBackground, clearedAndGradedArea, runway, obstacle);

        toraIndicator = new DistanceIndicator(runway, 0, 0, "", 0);
        asdaIndicator = new DistanceIndicator(runway, 0, 0, "", 0);
        todaIndicator = new DistanceIndicator(runway, 0, 0, "", 0);
        ldaIndicator = new DistanceIndicator(runway, 0, 0, "", 0);
        distanceFromThresholdIndicator = new DistanceIndicator(runway, 0, 0, "", 0);
        displacementThresholdIndicator = new DistanceIndicator(runway, 0, 0, "", 0);
        resaIndicator = new DistanceIndicator(runway, 0, 0, "", 0);
        stripEndIndicator = new DistanceIndicator(runway, 0, 0, "", 0);
        blastProtectionIndicator = new DistanceIndicator(runway, 0, 0, "", 0);
        slopeCalculationIndicator = new DistanceIndicator(runway, 0, 0, "", 0);

        runwayPane.getChildren().addAll(toraIndicator, asdaIndicator, todaIndicator, ldaIndicator,
                distanceFromThresholdIndicator, displacementThresholdIndicator, resaIndicator,
                stripEndIndicator, blastProtectionIndicator, slopeCalculationIndicator);

        // Image vector = new Image("vectorArrow.png");
        // ImageView iv = new ImageView();
        // iv.setImage(vector);
        // iv.setPreserveRatio(true);
        // iv.setFitHeight(100);
        // iv.setRotate(45);
        // BorderPane borderPane = new BorderPane();
        // borderPane.setBottom(iv);
    }

    private void newValues(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);

        disableIndicators(new DistanceIndicator[] { toraIndicator, asdaIndicator, todaIndicator, ldaIndicator,
                distanceFromThresholdIndicator, displacementThresholdIndicator, resaIndicator,
                stripEndIndicator, blastProtectionIndicator, slopeCalculationIndicator});

        // once implemented needs to add clearway and stopway
        runwayLength = cal.getTORA();

        // ----------------------------------- Indicator Visualisation below here////
        // -----------------------------------


        if (Double.valueOf(cal.getRunwayName().substring(0, 2)) <= 18) { // calculating from 01 to 18
            obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth() + runway.getX());

            if (cal.getObstacleDistanceFromThreshold() <= (runwayLength * 0.5)) { // obstacle on near-side
                if (cal.getStatus() == "Landing") { // Plane is landing
                    obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth() + runway.getX());

                    displacementThresholdIndicator.update(runway.getX(),
                            ((double) cal.getDisplacementThreshold() / (double) runwayLength) * runway.getWidth() + runway.getX(),
                            "" + cal.getDisplacementThreshold(),
                            0);

                    distanceFromThresholdIndicator.update(displacementThresholdIndicator.getEndX(),
                            ((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth() + displacementThresholdIndicator.getEndX(),
                            "Distance from Threshold: " + cal.getObstacleDistanceFromThreshold(),
                            0);

                    if (cal.getResa() > cal.getSlopeValue()) {
                        if (cal.getResa() + cal.getStripEnd() < cal.getBlastProtection()) {
                            blastProtectionIndicator.update(distanceFromThresholdIndicator.getEndX(),
                                    ((double) cal.getBlastProtection() / (double) runwayLength) * runway.getWidth() + distanceFromThresholdIndicator.getEndX(),
                                    "Blast Protection: " + cal.getBlastProtection(),
                                    0);
                            ldaIndicator.update(blastProtectionIndicator.getEndX(),
                                    ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth() + blastProtectionIndicator.getEndX(),
                                    "LDA: " + cal.getNewLDA(),
                                    0);
                        } else {
                            resaIndicator.update(distanceFromThresholdIndicator.getEndX(),
                                    ((double) cal.getResa() / (double) runwayLength) * runway.getWidth() + distanceFromThresholdIndicator.getEndX(),
                                    "RESA: " + cal.getResa(),
                                    0);
                            stripEndIndicator.update(resaIndicator.getEndX(),
                                    ((double) cal.getStripEnd() / (double) runwayLength) * runway.getWidth() + resaIndicator.getEndX(),
                                    "" + cal.getStripEnd(),
                                    0);
                            ldaIndicator.update(stripEndIndicator.getEndX(),
                                    ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth() + stripEndIndicator.getEndX(),
                                    "LDA: " + cal.getNewLDA(),
                                    0);
                        }

                    } else {
                        if (cal.getSlopeValue() + cal.getStripEnd() < cal.getBlastProtection()) {
                            blastProtectionIndicator.update(distanceFromThresholdIndicator.getEndX(),
                                    ((double) cal.getBlastProtection() / (double) runwayLength) * runway.getWidth() + distanceFromThresholdIndicator.getEndX(),
                                    "Blast Protection: " + cal.getBlastProtection(),
                                    0);
                            ldaIndicator.update(blastProtectionIndicator.getEndX(),
                                    ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth() + blastProtectionIndicator.getEndX(),
                                    "LDA: " + cal.getNewLDA(),
                                    0);
                        } else {
                            slopeCalculationIndicator.update(distanceFromThresholdIndicator.getEndX(),
                                    ((double) cal.getSlopeValue() / (double) runwayLength) * runway.getWidth() + distanceFromThresholdIndicator.getEndX(),
                                    "Slope Calculation: " + cal.getSlopeValue(),
                                    0);
                            stripEndIndicator.update(slopeCalculationIndicator.getEndX(),
                                    ((double) cal.getStripEnd() / (double) runwayLength) * runway.getWidth() + slopeCalculationIndicator.getEndX(),
                                    "" + cal.getStripEnd(),
                                    0);
                            ldaIndicator.update(stripEndIndicator.getEndX(),
                                    ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth() + stripEndIndicator.getEndX(),
                                    "LDA: " + cal.getNewLDA(),
                                    0);
                        }
                    }

                } else { // Plane is taking off
                    obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth() + runway.getX() - obstacle.getWidth());

                    displacementThresholdIndicator.update(runway.getX(),
                            ((double) cal.getDisplacementThreshold() / (double) runwayLength) * runway.getWidth() + runway.getX(),
                            "" + cal.getDisplacementThreshold(),
                            0);
                    distanceFromThresholdIndicator.update(displacementThresholdIndicator.getEndX(),
                            ((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth() + displacementThresholdIndicator.getEndX(),
                            "Distance from Threshold: " + cal.getObstacleDistanceFromThreshold(),
                            0);
                    blastProtectionIndicator.update(distanceFromThresholdIndicator.getEndX(),
                            ((double) cal.getBlastProtection() / (double) runwayLength) * runway.getWidth() + distanceFromThresholdIndicator.getEndX(),
                            "" + cal.getBlastProtection(),
                            0);

                    toraIndicator.update(blastProtectionIndicator.getEndX(),
                            ((double) cal.getNewTORA() / (double) runwayLength) * runway.getWidth() + blastProtectionIndicator.getEndX(),
                            "TORA: " + cal.getNewTORA(),
                            0);
                    todaIndicator.update(blastProtectionIndicator.getEndX(),
                            ((double) cal.getNewTODA() / (double) runwayLength) * runway.getWidth() + blastProtectionIndicator.getEndX(),
                            "TODA: " + cal.getNewTODA(),
                            1);
                    asdaIndicator.update(blastProtectionIndicator.getEndX(),
                            ((double) cal.getNewASDA() / (double) runwayLength) * runway.getWidth() + blastProtectionIndicator.getEndX(),
                            "ASDA: " + cal.getNewASDA(),
                            2);
                }
            } else { // obstacle on far-side
                if (cal.getStatus() == "Landing") { // Plane is landing
                    obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth() + runway.getX());

                    displacementThresholdIndicator.update(runway.getX(),
                            ((double) cal.getDisplacementThreshold() / (double) runwayLength) * runway.getWidth() + runway.getX(),
                            "" + cal.getDisplacementThreshold(),
                            0);
                    distanceFromThresholdIndicator.update(displacementThresholdIndicator.getEndX(),
                            ((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth() + displacementThresholdIndicator.getEndX(),
                            "Distance from Threshold: " + cal.getObstacleDistanceFromThreshold(),
                            0);
                    ldaIndicator.update(displacementThresholdIndicator.getEndX(),
                            ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth() + displacementThresholdIndicator.getEndX(),
                            "LDA: " + cal.getNewLDA(),
                            1);
                    stripEndIndicator.update(ldaIndicator.getEndX(),
                            ((double) cal.getStripEnd() / (double) runwayLength) * runway.getWidth() + ldaIndicator.getEndX(),
                            "" + cal.getStripEnd(),
                            1);
                    resaIndicator.update(stripEndIndicator.getEndX(),
                            ((double) cal.getResa() / (double) runwayLength) * runway.getWidth() + stripEndIndicator.getEndX(),
                            "RESA: " + cal.getResa(),
                            1);


                } else { // Plane is taking off
                    obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth() + runway.getX() - obstacle.getWidth());

                    displacementThresholdIndicator.update(runway.getX(),
                            ((double) cal.getDisplacementThreshold() / (double) runwayLength) * runway.getWidth() + runway.getX(),
                            "" + cal.getDisplacementThreshold(),
                            0);
                    distanceFromThresholdIndicator.update(displacementThresholdIndicator.getEndX(),
                            ((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth() + displacementThresholdIndicator.getEndX(),
                            "Distance from Threshold: " + cal.getObstacleDistanceFromThreshold(),
                            0);
                    toraIndicator.update(runway.getX(),
                            ((double) cal.getNewTORA() / (double) runwayLength) * runway.getWidth() + runway.getX(),
                            "TORA: " + cal.getNewTORA(),
                            1);
                    stripEndIndicator.update(toraIndicator.getEndX(),
                            ((double) cal.getStripEnd() / (double) runwayLength) * runway.getWidth() + toraIndicator.getEndX(),
                            "" + cal.getStripEnd(),
                            1);

                    if (cal.getResa() > cal.getSlopeValue()) {
                        resaIndicator.update(stripEndIndicator.getEndX(),
                                ((double) cal.getResa() / (double) runwayLength) * runway.getWidth() + stripEndIndicator.getEndX(),
                                "RESA: " + cal.getResa(),
                                1);
                    } else {
                        slopeCalculationIndicator.update(stripEndIndicator.getEndX(),
                                ((double) cal.getSlopeValue() / (double) runwayLength) * runway.getWidth() + stripEndIndicator.getEndX(),
                                "Slope Calculation: " + cal.getSlopeValue(),
                                1);
                    }
                }
            }

        } else { // calculating from 19 to 36
            obstacle.setX((runway.getX() + runway.getWidth()) - (((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth());

            if (cal.getObstacleDistanceFromThreshold() <= (runwayLength * 0.5)) { // obstacle on near-side
                obstacle.setX((runway.getX() + runway.getWidth()) - (((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth() - obstacle.getWidth());

                if (cal.getStatus() == "Landing") { // Plane is landing
                    displacementThresholdIndicator.update((runway.getX() + runway.getWidth()) - ((double) cal.getDisplacementThreshold() / (double) runwayLength) * runway.getWidth(),
                            runway.getX() + runway.getWidth(),
                            "" + cal.getDisplacementThreshold(),
                            0);

                    distanceFromThresholdIndicator.update(displacementThresholdIndicator.getStartX() - ((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth(),
                            displacementThresholdIndicator.getStartX(),
                            "Distance from Threshold: " + cal.getObstacleDistanceFromThreshold(),
                            0);

                    if (cal.getResa() > cal.getSlopeValue()) {
                        if (cal.getResa() + cal.getStripEnd() < cal.getBlastProtection()) {
                            blastProtectionIndicator.update(distanceFromThresholdIndicator.getStartX() - ((double) cal.getBlastProtection() / (double) runwayLength) * runway.getWidth(),
                                    distanceFromThresholdIndicator.getStartX(),
                                    "Blast Protection: " + cal.getBlastProtection(),
                                    0);
                            ldaIndicator.update(blastProtectionIndicator.getStartX() - ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth(),
                                    blastProtectionIndicator.getStartX(),
                                    "LDA: " + cal.getNewLDA(),
                                    0);
                        } else {
                            resaIndicator.update(distanceFromThresholdIndicator.getStartX() - ((double) cal.getResa() / (double) runwayLength) * runway.getWidth(),
                                    distanceFromThresholdIndicator.getStartX(),
                                    "RESA: " + cal.getResa(),
                                    0);
                            stripEndIndicator.update(resaIndicator.getStartX() - ((double) cal.getStripEnd() / (double) runwayLength) * runway.getWidth(),
                                    resaIndicator.getStartX(),
                                    "" + cal.getStripEnd(),
                                    0);
                            ldaIndicator.update(stripEndIndicator.getStartX() - ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth(),
                                    stripEndIndicator.getStartX(),
                                    "LDA: " + cal.getNewLDA(),
                                    0);
                        }

                    } else {
                        if (cal.getSlopeValue() + cal.getStripEnd() < cal.getBlastProtection()) {
                            blastProtectionIndicator.update(distanceFromThresholdIndicator.getStartX() - ((double) cal.getBlastProtection() / (double) runwayLength) * runway.getWidth(),
                                    distanceFromThresholdIndicator.getStartX(),
                                    "Blast Protection: " + cal.getBlastProtection(),
                                    0);
                            ldaIndicator.update(blastProtectionIndicator.getStartX() - ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth(),
                                    blastProtectionIndicator.getStartX(),
                                    "LDA: " + cal.getNewLDA(),
                                    0);
                        } else {
                            slopeCalculationIndicator.update(distanceFromThresholdIndicator.getStartX() - ((double) cal.getSlopeValue() / (double) runwayLength) * runway.getWidth(),
                                    distanceFromThresholdIndicator.getStartX(),
                                    "Slope Calculation: " + cal.getSlopeValue(),
                                    0);
                            stripEndIndicator.update(slopeCalculationIndicator.getStartX() - ((double) cal.getStripEnd() / (double) runwayLength) * runway.getWidth(),
                                    slopeCalculationIndicator.getStartX(),
                                    "" + cal.getStripEnd(),
                                    0);
                            ldaIndicator.update(stripEndIndicator.getStartX() - ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth(),
                                    stripEndIndicator.getStartX(),
                                    "LDA: " + cal.getNewLDA(),
                                    0);
                        }
                    }
                } else { // Plane is taking off
                    obstacle.setX((runway.getX() + runway.getWidth()) - (((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth());

                    displacementThresholdIndicator.update((runway.getX() + runway.getWidth()) - ((double) cal.getDisplacementThreshold() / (double) runwayLength) * runway.getWidth(),
                            runway.getX() + runway.getWidth(),
                            "" + cal.getDisplacementThreshold(),
                            0);
                    distanceFromThresholdIndicator.update(displacementThresholdIndicator.getStartX() - ((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth(),
                            displacementThresholdIndicator.getStartX(),
                            "Distance from Threshold: " + cal.getObstacleDistanceFromThreshold(),
                            0);
                    blastProtectionIndicator.update(distanceFromThresholdIndicator.getStartX() - ((double) cal.getBlastProtection() / (double) runwayLength) * runway.getWidth(),
                             distanceFromThresholdIndicator.getStartX(),
                            "" + cal.getBlastProtection(),
                            0);

                    toraIndicator.update(blastProtectionIndicator.getStartX() - ((double) cal.getNewTORA() / (double) runwayLength) * runway.getWidth(),
                            blastProtectionIndicator.getStartX(),
                            "TORA: " + cal.getNewTORA(),
                            0);
                    todaIndicator.update(blastProtectionIndicator.getStartX() - ((double) cal.getNewTODA() / (double) runwayLength) * runway.getWidth(),
                            blastProtectionIndicator.getStartX(),
                            "TODA: " + cal.getNewTODA(),
                            1);
                    asdaIndicator.update(blastProtectionIndicator.getStartX() - ((double) cal.getNewASDA() / (double) runwayLength) * runway.getWidth(),
                            blastProtectionIndicator.getStartX(),
                            "ASDA: " + cal.getNewASDA(),
                            2);
                }
            } else { // obstacle on far-side
                if (cal.getStatus() == "Landing") { // Plane is landing
                    obstacle.setX((runway.getX() + runway.getWidth()) - (((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth() - obstacle.getWidth());

                    displacementThresholdIndicator.update((runway.getX() + runway.getWidth()) - ((double) cal.getDisplacementThreshold() / (double) runwayLength) * runway.getWidth(),
                            runway.getX() + runway.getWidth(),
                            "" + cal.getDisplacementThreshold(),
                            0);
                    distanceFromThresholdIndicator.update(displacementThresholdIndicator.getStartX() - ((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth(),
                            displacementThresholdIndicator.getStartX(),
                            "Distance from Threshold: " + cal.getObstacleDistanceFromThreshold(),
                            0);
                    ldaIndicator.update( displacementThresholdIndicator.getStartX() - ((double) cal.getNewLDA() / (double) runwayLength) * runway.getWidth(),
                             displacementThresholdIndicator.getStartX(),
                            "LDA: " + cal.getNewLDA(),
                            1);
                    stripEndIndicator.update(ldaIndicator.getStartX() - ((double) cal.getStripEnd() / (double) runwayLength) * runway.getWidth(),
                            ldaIndicator.getStartX(),
                            "" + cal.getStripEnd(),
                            1);
                    resaIndicator.update(stripEndIndicator.getStartX() - ((double) cal.getResa() / (double) runwayLength) * runway.getWidth(),
                            stripEndIndicator.getStartX(),
                            "RESA: " + cal.getResa(),
                            1);


                } else { // Plane is taking off
                    obstacle.setX((runway.getX() + runway.getWidth()) - (((double) cal.getObstacleDistanceFromThreshold() + (double) cal.getDisplacementThreshold()) / (double) runwayLength) * runway.getWidth());

                    displacementThresholdIndicator.update((runway.getX() + runway.getWidth()) - ((double) cal.getDisplacementThreshold() / (double) runwayLength) * runway.getWidth(),
                            runway.getX() + runway.getWidth(),
                            "" + cal.getDisplacementThreshold(),
                            0);
                    distanceFromThresholdIndicator.update(displacementThresholdIndicator.getStartX() - ((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth(),
                            displacementThresholdIndicator.getStartX(),
                            "Distance from Threshold: " + cal.getObstacleDistanceFromThreshold(),
                            0);
                    toraIndicator.update((runway.getX() + runway.getWidth()) - ((double) cal.getNewTORA() / (double) runwayLength) * runway.getWidth(),
                            runway.getX() + runway.getWidth(),
                            "TORA: " + cal.getNewTORA(),
                            1);
                    stripEndIndicator.update(toraIndicator.getStartX() - ((double) cal.getStripEnd() / (double) runwayLength) * runway.getWidth(),
                            toraIndicator.getStartX(),
                            "" + cal.getStripEnd(),
                            1);

                    if (cal.getResa() > cal.getSlopeValue()) {
                        resaIndicator.update(stripEndIndicator.getStartX() - ((double) cal.getResa() / (double) runwayLength) * runway.getWidth(),
                                stripEndIndicator.getStartX(),
                                "RESA: " + cal.getResa(),
                                1);
                    } else {
                        slopeCalculationIndicator.update(stripEndIndicator.getStartX() - ((double) cal.getSlopeValue() / (double) runwayLength) * runway.getWidth(),
                                stripEndIndicator.getStartX(),
                                "Slope Calculation: " + cal.getSlopeValue(),
                                1);
                    }
                }
            }

        }

        setIndicatorsLabel(new DistanceIndicator[] { toraIndicator, asdaIndicator, todaIndicator, ldaIndicator,
                distanceFromThresholdIndicator, displacementThresholdIndicator, resaIndicator,
                stripEndIndicator, blastProtectionIndicator, slopeCalculationIndicator});
    }
}
