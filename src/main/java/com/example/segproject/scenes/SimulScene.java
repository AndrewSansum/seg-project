package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.components.*;
import com.example.segproject.SceneController;
import com.example.segproject.scenes.*;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;

/**
 * Class that builds and determines the behaviour of the side view
 */
public class SimulScene extends BaseScene {

	private Rectangle runway;
	private ImageView runway_2;
	private Rectangle obstacle_2;
	private Pane sidePane;
	private Pane topPane;
	private SideScene sideScene;
	private TopScene topScene;

    public SimulScene (SceneController controller) {
        super(controller);
    }

    /**
     * Builds the ui elements of the scene
     */
    public void build() {
		setupDefaultScene();
		inputs.setOnButtonClicked(this::newValues);
		sideScene = new SideScene(controller,0.49);
		topScene = new TopScene(controller, 0.49);
		topScene.setupDefaultScene();
		sideScene.setupDefaultScene();
		SplitPane splitPane = new SplitPane();
		topPane = topScene.getTopScenePane();
		sidePane = sideScene.getSideScenePane();
		obstacle = sideScene.getObstacleSide();
		obstacle.setWidth(obstacle.getWidth() * 0.49);
		obstacle.setHeight(obstacle.getHeight() * 0.49);
		obstacle.setY(obstacle.getY() + obstacle.getHeight() * 1.05);
		this.obstacle_2 = topScene.getObstacleTop();
		this.obstacle_2.setWidth(this.obstacle_2.getWidth() * 0.49);
		this.obstacle_2.setHeight(this.obstacle_2.getHeight() * 0.49);
		runway = sideScene.getRunwaySide();
		runway_2 = topScene.getRunwayTop();
		topPane.setMaxWidth((controller.getWidth()*0.66)/2);
		sidePane.setMaxWidth((controller.getWidth()*0.66)/2);
		splitPane.getItems().addAll(sidePane,topPane);
		runwayPane.getChildren().add(splitPane);
    }

    private void newValues(Calculations cal, ActionEvent event) {
        this.topPane.getChildren().removeAll(this.topScene.toraIndicator, this.topScene.asdaIndicator, this.topScene.todaIndicator, this.topScene.ldaIndicator,
				this.topScene.resaIndicator, this.topScene.stripEndIndicator, this.topScene.displacementThresholdIndicator, this.topScene.distanceFromThresholdIndicator);
		this.sidePane.getChildren().removeAll(this.sideScene.toraIndicator, this.sideScene.asdaIndicator, this.sideScene.todaIndicator, this.sideScene.ldaIndicator,
				this.sideScene.resaIndicator, this.sideScene.stripEndIndicator, this.sideScene.displacementThresholdIndicator, this.sideScene.distanceFromThresholdIndicator);
		this.sidePane.getChildren().remove(this.obstacle);
		this.topPane.getChildren().remove(this.obstacle_2);
		this.cal = cal;
		outputs.updateValues(cal);

        // once implemented needs to add clearway and stopway
		runwayLength = cal.getTORA();
		// this.sidePane = this.sideScene.displayLines(cal);
		// this.topPane = this.topScene.displayLines(cal);

		obstacle = this.sideScene.getNewObstacle(cal, event);
		obstacle.setX(obstacle.getX());
		this.sidePane.getChildren().addAll(this.sideScene.displayLines(cal));
		this.sidePane.getChildren().add(this.obstacle);

		obstacle_2 = this.topScene.getNewObstacle(cal, event);
		this.obstacle_2.setX(this.obstacle_2.getX() * 1.04);
		this.topPane.getChildren().addAll(this.topScene.displayLines(cal));
		this.topPane.getChildren().add(this.obstacle_2);
	}

	public void rotate(int bearing) {

	}
}
