package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.SceneController;
import com.example.segproject.components.DistanceIndicator;
import javafx.event.ActionEvent;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.*;

/**
 * Class that builds and determines the behaviour of the top view
 */
public class TopScene extends BaseScene {
    private Double k;

    public TopScene(SceneController controller, Double k) {
        super(controller);
        this.k = k;
    }

    /**
     * Builds the ui elements of the scene
     */
    public void build() {
        setupDefaultScene();
        inputs.setOnButtonClicked(this::newValues);
        Pane topScene = getTopScenePane();
        runwayPane.getChildren().add(topScene);
    }

    public Pane getTopScenePane() {
        Pane topScene = new Pane();

        runway = new ImageView("runway.png");
        runway.setFitWidth((controller.getWidth() * 0.66 - 300) * this.k);
        runway.setFitHeight(100);
        runway.setLayoutX((runwayPaneCenterX - runway.getFitWidth() * 0.5) * (this.k - 0.2));
        runway.setLayoutY(runwayPaneCenterY - runway.getFitHeight() * 0.5);

        Polygon clearedAndGradedArea = new Polygon();
        clearedAndGradedArea.getPoints().addAll(new Double[]{
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

        obstacle = createObstable(runwayPaneCenterX - 25, runwayPaneCenterY - 25);
        Rectangle background = new Rectangle(0, 0, (controller.getWidth() * 0.66) * this.k, controller.getHeight());

        background.setFill(Color.GREEN);
        clearedAndGradedArea.setFill(Color.BLUE);
        topScene.getChildren().addAll(background, clearedAndGradedArea, runway);

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

        return topScene;
    }

    public Rectangle getObstacleTop() {
        return this.obstacle;
    }


    public ImageView getRunwayTop() {
        return this.runway;
    }

    private void newValues(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);

        disableIndicators(new DistanceIndicator[]{toraIndicator, asdaIndicator, todaIndicator, ldaIndicator,
                distanceFromThresholdIndicator, displacementThresholdIndicator, resaIndicator,
                stripEndIndicator, blastProtectionIndicator, slopeCalculationIndicator});

        // once implemented needs to add clearway and stopway
        runwayLength = cal.getTORA();

        // ----------------------------------- Indicator Visualisation below here -----------------------------------


        if (cal.getObstacleDirection() == "North") {
            obstacle.setY((runwayPaneCenterY - obstacle.getHeight()) - (double) cal.getObstacleDistanceFromCenter());
        } else if (cal.getObstacleDirection() == "South") {
            obstacle.setY((runwayPaneCenterY - (obstacle.getHeight() * 0.5)) + (double) cal.getObstacleDistanceFromCenter());
        }

        if (Double.valueOf(cal.getRunwayName().substring(0, 2)) <= 18) { // calculating from 01 to 18
            obstacle.setX(((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getFitWidth());
        }
        else { // calculating from 19 to 36
            obstacle.setX(((double) (runwayLength - cal.getObstacleDistanceFromThreshold()) / (double) runwayLength) * runway.getFitWidth());
            runwayPane.getChildren().add(obstacle);
        }
    }

    public void rotate(int bearing) {

    }
}
