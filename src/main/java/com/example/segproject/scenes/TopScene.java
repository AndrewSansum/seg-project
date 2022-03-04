package com.example.segproject.scenes;

import com.example.segproject.SceneController;

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
        runwayPane.setStyle("-fx-background-color: blue");
    }
}
