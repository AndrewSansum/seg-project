package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
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

        runway = new ImageView("runway.png");
        runway.setFitWidth(controller.getWidth() * 0.66 - 300);
        runway.setFitHeight(100);
        runway.setLayoutX(runwayPaneCenterX - runway.getFitWidth() * 0.5);
        runway.setLayoutY(runwayPaneCenterY - runway.getFitHeight() * 0.5);

        Polygon clearedAndGradedArea = new Polygon();
        clearedAndGradedArea.getPoints().addAll(new Double[] {
                runway.getLayoutX() - 60, runway.getLayoutY() - 75,
                runway.getLayoutX() - 60, runway.getLayoutY() + 75 + runway.getFitHeight(),
                runway.getLayoutX() + 60, runway.getLayoutY() + 75 + runway.getFitHeight(),
                runway.getLayoutX() + 120, runway.getLayoutY() + 105 + runway.getFitHeight(),
                runway.getLayoutX() - 120 + runway.getFitWidth(), runway.getLayoutY() + 105 + runway.getFitHeight(),
                runway.getLayoutX() - 60 + runway.getFitWidth(), runway.getLayoutY() + 75 + runway.getFitHeight(),
                runway.getLayoutX() + 60 + runway.getFitWidth(), runway.getLayoutY() + 75 + runway.getFitHeight(),
                runway.getLayoutX() + 60 + runway.getFitWidth(), runway.getLayoutY() - 75,
                runway.getLayoutX() - 60 + runway.getFitWidth(), runway.getLayoutY() - 75,
                runway.getLayoutX() - 120 + runway.getFitWidth(), runway.getLayoutY() - 105,
                runway.getLayoutX() + 120, runway.getLayoutY() - 105,
                runway.getLayoutX() + 60, runway.getLayoutY() - 75
        });

        obstacle = new Rectangle(runwayPaneCenterX - 25, runwayPaneCenterY - 25, 50, 50);
        Rectangle background = new Rectangle(0,0, controller.getWidth() * 0.66, controller.getHeight());

        background.setFill(Color.GREEN);
        clearedAndGradedArea.setFill(Color.BLUE);
        obstacle.setFill(Color.ORANGE);

        runwayPane.getChildren().addAll(background, clearedAndGradedArea, runway, obstacle);

    }

    private void newValues(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);

        // once implemented needs to add clearway and stopway
        runwayLength = cal.getTORA();


        if (cal.getObstacleDirection() == "North") {
            obstacle.setY((runwayPaneCenterY - obstacle.getHeight()) - (double) cal.getObstacleDistanceFromCenter());
        } else if (cal.getObstacleDirection() == "South") {
            obstacle.setY((runwayPaneCenterY - (obstacle.getHeight() * 0.5)) + (double) cal.getObstacleDistanceFromCenter());
        }


        if (Double.valueOf(cal.getRunwayName().substring(0,2)) <= 18) { // calculating from 01 to 18
            obstacle.setX(((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getFitWidth());

        } else { // calculating from 19 to 36
            obstacle.setX(((double) (runwayLength - cal.getObstacleDistanceFromThreshold()) / (double) runwayLength) * runway.getFitWidth());

        }
    }
}
