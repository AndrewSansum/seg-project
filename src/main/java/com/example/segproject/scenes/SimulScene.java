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

    public SimulScene (SceneController controller) {
        super(controller);
    }

    /**
     * Builds the ui elements of the scene
     */
    public void build() {
		setupDefaultScene();
		inputs.setOnButtonClicked(this::newValues);
		SideScene sideScene = new SideScene(controller,0.49);
		TopScene topScene = new TopScene(controller, 0.49);
		topScene.setupDefaultScene();
		sideScene.setupDefaultScene();
		SplitPane splitPane = new SplitPane();
		topPane = topScene.getTopScenePane();
		sidePane = sideScene.getSideScenePane();
		obstacle = sideScene.getObstacleSide();
		obstacle.setWidth(obstacle.getWidth() * 0.49);
		obstacle.setHeight(obstacle.getHeight() * 0.49);
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
		this.cal = cal;
		outputs.updateValues(cal);
        // once implemented needs to add clearway and stopway
		runwayLength = cal.getTORA();
        if (Double.valueOf(cal.getRunwayName().substring(0,2)) <= 18) // calculating from 01 to 18
            obstacle.setX(((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway.getWidth());
        else // calculating from 19 to 36
            obstacle.setX(((double) (runwayLength - cal.getObstacleDistanceFromThreshold()) / (double) runwayLength) * runway.getWidth());
		obstacle.setX(obstacle.getX() * 0.49);
		obstacle.setY(obstacle.getY() + obstacle.getHeight() * 1.05);
		this.sidePane.getChildren().add(obstacle);

        if (cal.getObstacleDirection() == "North")
            this.obstacle_2.setY((runwayPaneCenterY - this.obstacle_2.getHeight()) - (double) cal.getObstacleDistanceFromCenter());
        else if (cal.getObstacleDirection() == "South")
            this.obstacle_2.setY((runwayPaneCenterY - (this.obstacle_2.getHeight() * 0.5)) + (double) cal.getObstacleDistanceFromCenter());

        if (Double.valueOf(cal.getRunwayName().substring(0,2)) <= 18) // calculating from 01 to 18
            this.obstacle_2.setX(((double) cal.getObstacleDistanceFromThreshold() / (double) runwayLength) * runway_2.getFitWidth());
        else // calculating from 19 to 36
            this.obstacle_2.setX(((double) (runwayLength - cal.getObstacleDistanceFromThreshold()) / (double) runwayLength) * runway_2.getFitWidth());
		this.obstacle_2.setX(this.obstacle_2.getX() * 0.49 * 1.05);
		this.topPane.getChildren().add(this.obstacle_2);
	}
}
