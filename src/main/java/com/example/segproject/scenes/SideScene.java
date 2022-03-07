package com.example.segproject.scenes;

import com.example.segproject.components.Canvas;
import com.example.segproject.components.Obstacle;
import com.example.segproject.components.Runway;
import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;
import javafx.geometry.Pos;
import javafx.scene.Scene;

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
        // Runway run		= new Runway(15, 11, 16);
		// Canvas can		= new Canvas(run, 27);
		// Obstacle obs	= new Obstacle(10,13,2);

        // obs.setObstacle(2, 2, 2);
		// can.addObstacleToTileMap3D(obs);
		runwayPane.getChildren().add(can.renderView("Side View"));
		runwayPane.setAlignment(Pos.TOP_CENTER);
		// root.setCenter();
        // runwayPane.setStyle("-fx-background-color: yellow");
    }
}
