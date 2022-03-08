package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.components.Canvas;
import com.example.segproject.components.Obstacle;
import com.example.segproject.components.Runway;
import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

/**
 * Class that builds and determines the behaviour of the side view
 */
public class SideScene extends BaseScene {
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

    }

    private void newValues(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);

        runwayLength = cal.tora;

        obstacle.setX(((double) cal.obstacleDistanceFromThreshold / (double) runwayLength) * runway.getWidth());
        obstacle.setWidth(((double) cal.displacementThreshold / (double) runwayLength) * runway.getWidth());
    }
}
