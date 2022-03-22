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
                stripEndIndicator, blastProtectionIndicator, slopeCalculationIndicator });

        // once implemented needs to add clearway and stopway
        runwayLength = cal.getTORA();

        // ----------------------------------- Indicator Visualisation below here////
        // -----------------------------------
        // Cases from 1-18
        if (Double.valueOf(cal.getRunwayName().substring(0, 2)) <= 18) {
            if (cal.getObstacleDistanceFromThreshold() <= (runwayLength * 0.5)) { // obstacle is on the near-side
                // Landing over case
                if (cal.getStatus() == "Landing") {
                    slopeCalculationIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                            "Slope: " + cal.getSlopeValue(), 0);
                    stripEndIndicator.update((double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Stripend: " + cal.getStripEnd(), 0);
                    if (cal.getSlopeValue() < cal.getResa()) {
                        slopeCalculationIndicator.disable();
                        stripEndIndicator.disable();
                        resaIndicator.update(
                                (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                        * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Resa: " + cal.getResa(), 0);
                        stripEndIndicator.update(
                                (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Stripend: " + cal.getStripEnd(), 0);
                    }
                    if (cal.getBlastProtection() > (cal.getStripEnd() + cal.getSlopeValue())) {
                        if (cal.getBlastProtection() > (cal.getResa() + cal.getStripEnd())) {
                            resaIndicator.disable();
                            slopeCalculationIndicator.disable();
                            stripEndIndicator.disable();
                            blastProtectionIndicator.update(
                                    (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                            * runway.getWidth(),
                                    (double) cal.getBlastProtection() / (double) runwayLength * runway.getWidth(),
                                    "Blast Protection: " + cal.getBlastProtection(), 0);
                        }
                    }
                    ldaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewLDA() / (double) runwayLength * runway.getWidth(),
                            "LDA: " + cal.getNewLDA(), 1);
                }
                // Taking off away case
                else {
                    resaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Resa: " + cal.getResa(), 1);
                    stripEndIndicator.update((double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Stripend: " + cal.getStripEnd(), 1);

                    if (cal.getBlastProtection() > (cal.getResa() + cal.getStripEnd())) {
                        resaIndicator.disable();
                        stripEndIndicator.disable();
                        blastProtectionIndicator.update(
                                (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                        * runway.getWidth(),
                                (double) cal.getBlastProtection() / (double) runwayLength * runway.getWidth(),
                                "Blast Protection: " + cal.getBlastProtection(), 0);
                    }
                    toraIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewTORA() / (double) runwayLength * runway.getWidth(),
                            "TORA: " + cal.getNewTORA(), 1);
                    todaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewTODA() / (double) runwayLength * runway.getWidth(),
                            "TODA: " + cal.getNewTODA(), 2);
                    asdaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewASDA() / (double) runwayLength * runway.getWidth(),
                            "ASDA: " + cal.getNewASDA(), 3);
                }
            }
            // obstacle is on the far-side
            else {
                // Landing towards case
                if (cal.getStatus() == "Landing") {
                    resaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Resa: " + cal.getResa(), 0);
                    stripEndIndicator.update((double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Stripend: " + cal.getStripEnd(), 0);
                    ldaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewLDA() / (double) runwayLength * runway.getWidth(),
                            "LDA: " + cal.getNewLDA(), 1);

                    // Taking off towards case
                } else {
                    if (cal.getSlopeValue() > cal.getResa()) {
                        slopeCalculationIndicator.update(
                                (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                        * runway.getWidth(),
                                (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                                "Slope: " + cal.getSlopeValue(), 0);
                        stripEndIndicator.update(
                                (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Stripend: " + cal.getStripEnd(), 0);
                    } else {
                        resaIndicator.update(
                                (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                        * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Resa: " + cal.getResa(), 0);
                        stripEndIndicator.update(
                                (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Stripend: " + cal.getStripEnd(), 0);
                    }
                    toraIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewTORA() / (double) runwayLength * runway.getWidth(),
                            "TORA: " + cal.getNewTORA(), 1);
                    todaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewTODA() / (double) runwayLength * runway.getWidth(),
                            "TODA: " + cal.getNewTODA(), 2);
                    asdaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewASDA() / (double) runwayLength * runway.getWidth(),
                            "ASDA: " + cal.getNewASDA(), 3);
                }
            }
            // Cases from
            // 19-36--------------------------------------------------------------------------------------------
        } else {
            if (cal.getObstacleDistanceFromThreshold() <= (runwayLength * 0.5)) { // obstacle is on the far side
                // Landing towards case
                if (cal.getStatus() == "Landing") {
                    resaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Resa: " + cal.getResa(), 0);
                    stripEndIndicator.update((double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Stripend: " + cal.getStripEnd(), 0);
                    ldaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewLDA() / (double) runwayLength * runway.getWidth(),
                            "LDA: " + cal.getNewLDA(), 1);
                }
                // Taking off towards case
                else {
                    if (cal.getSlopeValue() > cal.getResa()) {
                        slopeCalculationIndicator.update(
                                (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                        * runway.getWidth(),
                                (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                                "Slope: " + cal.getSlopeValue(), 0);
                        stripEndIndicator.update(
                                (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Stripend: " + cal.getStripEnd(), 0);
                    } else {
                        resaIndicator.update(
                                (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                        * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Resa: " + cal.getResa(), 0);
                        stripEndIndicator.update(
                                (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Stripend: " + cal.getStripEnd(), 0);
                    }
                    toraIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewTORA() / (double) runwayLength * runway.getWidth(),
                            "TORA: " + cal.getNewTORA(), 1);
                    todaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewTODA() / (double) runwayLength * runway.getWidth(),
                            "TODA: " + cal.getNewTODA(), 2);
                    asdaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewASDA() / (double) runwayLength * runway.getWidth(),
                            "ASDA: " + cal.getNewASDA(), 3);
                }
            }
            // obstacle is on the near-side
            else {
                // Landing over case
                if (cal.getStatus() == "Landing") {
                    slopeCalculationIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                            "Slope: " + cal.getSlopeValue(), 0);
                    stripEndIndicator.update((double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Stripend: " + cal.getStripEnd(), 0);
                    if (cal.getSlopeValue() < cal.getResa()) {
                        slopeCalculationIndicator.disable();
                        stripEndIndicator.disable();
                        resaIndicator.update(
                                (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                        * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Resa: " + cal.getResa(), 0);
                        stripEndIndicator.update(
                                (double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                                (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                                "Stripend: " + cal.getStripEnd(), 0);
                    }
                    if (cal.getBlastProtection() > (cal.getStripEnd() + cal.getSlopeValue())) {
                        if (cal.getBlastProtection() > (cal.getResa() + cal.getStripEnd())) {
                            resaIndicator.disable();
                            slopeCalculationIndicator.disable();
                            stripEndIndicator.disable();
                            blastProtectionIndicator.update(
                                    (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                            * runway.getWidth(),
                                    (double) cal.getBlastProtection() / (double) runwayLength * runway.getWidth(),
                                    "Blast Protection: " + cal.getBlastProtection(), 0);
                        }
                    }
                    ldaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewLDA() / (double) runwayLength * runway.getWidth(),
                            "LDA: " + cal.getNewLDA(), 1);

                    // Taking off away case
                } else {
                    resaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Resa: " + cal.getResa(), 1);
                    stripEndIndicator.update((double) cal.getSlopeValue() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getResa() / (double) runwayLength * runway.getWidth(),
                            "Stripend: " + cal.getStripEnd(), 1);

                    if (cal.getBlastProtection() > (cal.getResa() + cal.getStripEnd())) {
                        resaIndicator.disable();
                        stripEndIndicator.disable();
                        blastProtectionIndicator.update(
                                (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength
                                        * runway.getWidth(),
                                (double) cal.getBlastProtection() / (double) runwayLength * runway.getWidth(),
                                "Blast Protection: " + cal.getBlastProtection(), 0);
                    }
                    toraIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewTORA() / (double) runwayLength * runway.getWidth(),
                            "TORA: " + cal.getNewTORA(), 1);
                    todaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewTODA() / (double) runwayLength * runway.getWidth(),
                            "TODA: " + cal.getNewTODA(), 2);
                    asdaIndicator.update(
                            (double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength * runway.getWidth(),
                            (double) cal.getNewASDA() / (double) runwayLength * runway.getWidth(),
                            "ASDA: " + cal.getNewASDA(), 3);
                }
            }
        }

        if (Double.valueOf(cal.getRunwayName().substring(0, 2)) <= 18) { // calculating from 01 to 18
            obstacle.setX(
                    ((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth());

        } else { // calculating from 19 to 36
            obstacle.setX(((double) (runwayLength - cal.getObstacleDistanceFromThreshold()) / (double) runwayLength)
                    * runway.getWidth());

        }

    }
}
