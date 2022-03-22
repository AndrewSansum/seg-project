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
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;

/**
 * Class that builds and determines the behaviour of the side view
 */
public class SideScene extends BaseScene {

	private Rectangle runway;
	private Double k;

    public SideScene (SceneController controller, Double k) {
		super(controller);
		this.k = k;
    }

    /**
     * Builds the ui elements of the scene
     */
    public void build() {
		setupDefaultScene();
        inputs.setOnButtonClicked(this::newValues);
		Pane sideScenePane = getSideScenePane();
        runwayPane.getChildren().add(sideScenePane);
    }

	public Pane getSideScenePane() {
		Pane sideScene = new Pane();
        runway = new Rectangle();
        runway.setWidth(controller.getWidth() * 0.66 - 300);
        runway.setHeight(50);
        runway.setX((runwayPaneCenterX - runway.getWidth() * 0.5));
        runway.setY(runwayPaneCenterY - runway.getHeight() * 0.5);
        Rectangle clearedAndGradedArea = new Rectangle(runway.getX() - 60, runway.getY(), runway.getWidth() + 120, runway.getHeight());
        Rectangle lowerBackground = new Rectangle(0, runway.getY(), controller.getWidth() * 0.66, controller.getHeight());
        Rectangle upperBackground = new Rectangle(0,0,controller.getWidth() * 0.66, controller.getHeight() * 0.5 - runway.getHeight() * 0.5);
		obstacle = createObstable(runway.getX(), runway.getY() - 50);
        lowerBackground.setFill(Color.GREEN);
        upperBackground.setFill(Color.LIGHTCYAN);
        clearedAndGradedArea.setFill(Color.BLUE);
		runway.setFill(Color.DARKGRAY);
	
		runway.setX(runway.getX() * this.k);
		runway.setWidth(runway.getWidth() * this.k);
		clearedAndGradedArea.setWidth(clearedAndGradedArea.getWidth() * this.k);
		clearedAndGradedArea.setX(clearedAndGradedArea.getX() * this.k);
		lowerBackground.setWidth(lowerBackground.getWidth() * this.k);
		upperBackground.setWidth(upperBackground.getWidth() * this.k);
	
		sideScene.getChildren().addAll(lowerBackground, upperBackground, clearedAndGradedArea, runway);

		return sideScene;
	}

	public Rectangle getObstacleSide() {
		return this.obstacle;
	}

	public Rectangle getRunwaySide() {
		return this.runway;
	}

    private void newValues(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);
		runwayPane.getChildren().remove(obstacle);
        runwayPane.getChildren().removeAll(toraIndicator, asdaIndicator, todaIndicator, ldaIndicator,
                resaIndicator, stripEndIndicator, displacementThresholdIndicator, distanceFromThresholdIndicator);

        // once implemented needs to add clearway and stopway
		runwayLength = cal.getTORA();
		double ratio = runwayLength / runway.getWidth();
		double startx = this.runway.getX();
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
			runwayPane.getChildren().addAll(toraIndicator, asdaIndicator, todaIndicator);
		} else {
			displacementThresholdIndicator = new DistanceIndicator (runway, startx, (double) startx + displacement / ratio , displacement.toString() + "m", 0);
			distanceFromThresholdIndicator = new DistanceIndicator (runway, (double) (startx + (displacement / ratio)), (double) (startx + ((displacement + distThresholdObject) / ratio)) , distThresholdObject.toString() + "m", 0);
			ldaIndicator = new DistanceIndicator (runway, startx + displacement / ratio, (double) startx + ((lda + displacement) / ratio) , lda.toString() + "m", 1);
			stripEndIndicator = new DistanceIndicator (runway, startx + ((lda + displacement) / ratio), (double) (startx + (lda + stripEnd + displacement) / ratio), Integer.valueOf(stripEnd).toString() + "m", 1);
			resaIndicator = new DistanceIndicator (runway,  (startx + ((lda + stripEnd + displacement) / ratio)), (double) (startx + (lda + resa + stripEnd + displacement) / ratio) , Integer.valueOf(resa).toString() + "m", 1);
			runwayPane.getChildren().addAll(ldaIndicator, stripEndIndicator, resaIndicator, displacementThresholdIndicator, distanceFromThresholdIndicator);
		}

        // ----------------------------------- Indicator Visualisation below here -----------------------------------

		obstacle = getNewObstacle(cal, event);
		runwayPane.getChildren().add(obstacle);

	}
	
	public Rectangle getNewObstacle(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);
        runwayLength = cal.getTORA();

        if (Double.valueOf(cal.getRunwayName().substring(0,2)) <= 18) { // calculating from 01 to 18
			obstacle.setX((((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth()));
        } else { // calculating from 19 to 36
            obstacle.setX((((double) (runwayLength - cal.getObstacleDistanceFromThreshold()) / (double) runwayLength) * runway.getWidth()));
		}
		obstacle.setX(obstacle.getX());
		return obstacle;
    }


    public void rotate(int bearing) {

    }
}
