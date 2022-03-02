package com.example.segproject.scenes;

import com.example.segproject.SceneController;

public class TopScene extends BaseScene {
    public TopScene (SceneController controller) {
        super(controller);
    }

    public void build() {
        super.setupDefaultScene();
        runwayPane.setStyle("-fx-background-color: blue");
    }
}
