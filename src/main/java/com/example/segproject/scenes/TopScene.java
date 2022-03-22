package com.example.segproject.scenes;

import java.util.ArrayList; // import the ArrayList class
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
        
        runwayPane.getChildren().remove(obstacle);

        // once implemented needs to add clearway and stopway
        runwayLength = cal.getTORA();

        // ----------------------------------- Indicator Visualisation below here -----------------------------------
        obstacle = getNewObstacle(cal, event);
        runwayPane.getChildren().add(obstacle);
		runwayPane.getChildren().addAll(displayLines(cal));
    }
    
    public Rectangle getNewObstacle(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);
        runwayLength = cal.getTORA();

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
        }
        return obstacle;
    }

	public ArrayList<DistanceIndicator> displayLines(Calculations cal) {
        ArrayList<DistanceIndicator> res = new ArrayList<DistanceIndicator>();
        runwayPane.getChildren().removeAll(toraIndicator, asdaIndicator, todaIndicator, ldaIndicator,
                resaIndicator, stripEndIndicator, displacementThresholdIndicator, distanceFromThresholdIndicator);
        runwayLength = cal.getTORA();
        double ratio = runwayLength / runway.getFitWidth();
		double startx = this.runway.getLayoutX();
		Integer lda = cal.getNewLDA();
		Integer stripEnd =  cal.getStripEnd();
		Integer resa = cal.getRESA();
		Integer displacement = cal.getDisplacementThreshold();
		Integer distThresholdObject = cal.getObstacleDistanceFromThreshold();

		if (cal.getStatus() != "Landing") {
			Integer toda = cal.getNewTODA();
			Integer asda = cal.getNewASDA();
			Integer tora = cal.getNewTORA(); //startx, (double) startx + displacement / ratio
			toraIndicator = new DistanceIndicator (runway, (double) startx , (double) ( startx + (tora / ratio)) , "TORA: " + tora.toString(), 0);
			asdaIndicator = new DistanceIndicator (runway, (double) startx , (double) ( startx + (asda / ratio)) , "ASDA: " + asda.toString(), 1);
			todaIndicator = new DistanceIndicator (runway, (double) startx , (double) ( startx + (toda / ratio)) , "TODA: " + tora.toString(), 2);
            res.add(toraIndicator);
            res.add(asdaIndicator);
            res.add(todaIndicator);
		} else {
			displacementThresholdIndicator = new DistanceIndicator (runway, startx, (double) startx + displacement / ratio , displacement.toString() + "m", 0);
			distanceFromThresholdIndicator = new DistanceIndicator (runway, (double) (startx + (displacement / ratio)), (double) (startx + ((displacement + distThresholdObject) / ratio)) , distThresholdObject.toString() + "m", 0);
			ldaIndicator = new DistanceIndicator (runway, startx + displacement / ratio, (double) startx + ((lda + displacement) / ratio) , lda.toString() + "m", 1);
			stripEndIndicator = new DistanceIndicator (runway, startx + ((lda + displacement) / ratio), (double) (startx + (lda + stripEnd + displacement) / ratio), Integer.valueOf(stripEnd).toString() + "m", 1);
			resaIndicator = new DistanceIndicator (runway,  (startx + ((lda + stripEnd + displacement) / ratio)), (double) (startx + (lda + resa + stripEnd + displacement) / ratio) , Integer.valueOf(resa).toString() + "m", 1);
			res.add(ldaIndicator);
			res.add(stripEndIndicator);
			res.add(resaIndicator);
			res.add(displacementThresholdIndicator);
			res.add(distanceFromThresholdIndicator);
        }
        return res;
	}


    public void rotate(int bearing) {

    }
}
