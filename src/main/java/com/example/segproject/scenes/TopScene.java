package com.example.segproject.scenes;

import com.example.segproject.components.Canvas;
import com.example.segproject.components.Obstacle;
import com.example.segproject.components.Runway;
import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;
import javafx.geometry.Pos;
import javafx.scene.Scene;

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
		runwayPane.getChildren().add(can.renderView("Top View"));
		runwayPane.setAlignment(Pos.TOP_CENTER);
		// root.setCenter();
        // runwayPane.setStyle("-fx-background-color: yellow");
    }
}
