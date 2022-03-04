package com.example.segproject.scenes;

import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;
import javafx.geometry.Pos;

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
        runwayPane.setStyle("-fx-background-color: yellow");
    }
}
