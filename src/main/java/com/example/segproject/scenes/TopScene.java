package com.example.segproject.scenes;

import com.example.segproject.SceneController;
import javafx.geometry.Pos;

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
