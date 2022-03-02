package com.example.segproject;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    private final int resH = 600;
    private final int resV = 800;

    private Stage stage;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        var controller = new SceneController(stage, resH, resV, "test");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void shutdown() {
        System.exit(0);
    }
}