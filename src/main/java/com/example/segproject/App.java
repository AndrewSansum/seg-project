package com.example.segproject;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Class that instantiates the application
 */
public class App extends Application {

    private final int resH = 800;
    private final int resV = 600;

    private Stage stage;

    /**
     * Generates the scene controller which then runs the rest of the application
     * @param stage the application window
     */
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