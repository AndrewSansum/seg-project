package com.example.segproject;

import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.geometry.Pos; 
import javafx.scene.layout.TilePane; 

public class App extends Application {
    @Override
    public void start(Stage stage) {
        Runway run		= new Runway(10, 5, 6);
        Canvas can		= new Canvas(run);
		Obstacle obs	= new Obstacle(7,3,4);

        obs.setObstacle(2, 1, 2);
		can.addObstacleToTileMap3D(obs);
		can.renderView(stage, "Side View");
    }

    public static void main(String[] args) {
        launch();
    }
}
