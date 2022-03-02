package com.example.segproject.scenes;

import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;
import javafx.geometry.Pos;

public class SideScene extends BaseScene {
    public SideScene (SceneController controller) {
        super(controller);
    }

    public void build() {
        super.setupDefaultScene();
        runwayPane.setStyle("-fx-background-color: yellow");
    }
}
