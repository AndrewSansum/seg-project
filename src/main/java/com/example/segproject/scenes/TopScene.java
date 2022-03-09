package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Class that builds and determines the behaviour of the top view
 */
public class TopScene extends BaseScene {
    public TopScene (SceneController controller) {
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
        runway.setHeight(200);
        runway.setX(runwayPaneCenterX - runway.getWidth() * 0.5);
        runway.setY(runwayPaneCenterY - runway.getHeight() * 0.5);


        obstacle = new Rectangle(400, 400, 50, 50);

        runway.setFill(Color.DARKGRAY);
        obstacle.setFill(Color.ORANGE);

        runwayPane.getChildren().addAll(runway, obstacle);

    }

    private void newValues(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);

        runwayLength = cal.tora;

        if (cal.obstacleDirection == "North") {
            obstacle.setY((runwayPaneCenterY - obstacle.getHeight()) - (double) cal.getObstacleDistanceFromCenter());
        } else if (cal.obstacleDirection == "South") {
            obstacle.setY((runwayPaneCenterY - (obstacle.getHeight() * 0.5)) + (double) cal.getObstacleDistanceFromCenter());
        }



        if (Double.valueOf(cal.runwayName.substring(0,2)) <= 18) { // calculating from 01 to 18
            obstacle.setX(((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth());

        } else { // calculating from 19 to 36
            obstacle.setX(((double) (runwayLength - cal.getObstacleDistanceFromThreshold()) / (double) runwayLength) * runway.getWidth());

        }
    }
}
