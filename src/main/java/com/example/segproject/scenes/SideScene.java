package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.components.*;
import com.example.segproject.SceneController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * Class that builds and determines the behaviour of the side view
 */
public class SideScene extends BaseScene {

    private Rectangle runway;

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
        runway.setWidth(controller.getWidth() * 0.66 - 300);
        runway.setHeight(50);
        runway.setX(runwayPaneCenterX - runway.getWidth() * 0.5);
        runway.setY(runwayPaneCenterY - runway.getHeight() * 0.5);

        Rectangle clearedAndGradedArea = new Rectangle(runway.getX() - 60, runway.getY(), runway.getWidth() + 120, runway.getHeight());
        Rectangle lowerBackground = new Rectangle(0, runway.getY(), controller.getWidth() * 0.66, controller.getHeight());
        Rectangle upperBackground = new Rectangle(0,0,controller.getWidth() * 0.66, controller.getHeight() * 0.5 - runway.getHeight() * 0.5);

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

        DistanceIndicator test = new DistanceIndicator(runway, 100, 1100, "test label", 0);

        runwayPane.getChildren().addAll(lowerBackground, upperBackground, clearedAndGradedArea, runway, obstacle, test);

    }

    private void newValues(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);

        // once implemented needs to add clearway and stopway
        runwayLength = cal.getTORA();


        if (Double.valueOf(cal.getRunwayName().substring(0,2)) <= 18) { // calculating from 01 to 18
            obstacle.setX(((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth());

        } else { // calculating from 19 to 36
            obstacle.setX(((double) (runwayLength - cal.getObstacleDistanceFromThreshold()) / (double) runwayLength) * runway.getWidth());

        }



    }
}
