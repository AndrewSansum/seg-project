package com.example.segproject.scenes;

import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public abstract class BaseScene {

    protected SceneController controller;
    protected Scene scene;

    protected BorderPane root;
    protected StackPane runwayPane;
    protected VBox io;
    protected HBox toolbar;

    public BaseScene(SceneController controller) {
        this.controller = controller;
    }

    protected void setupDefaultScene() {
        root = new BorderPane();
        runwayPane = new StackPane();
        io = new VBox();

        // not in use
        toolbar = new HBox();

        root.setCenter(runwayPane);
        root.setRight(io);

        root.setMaxWidth(controller.getWidth());
        root.setMaxHeight(controller.getHeight());

        runwayPane.setMinWidth(controller.getWidth() * 0.66);
        io.setMinWidth(controller.getWidth() * 0.33);

        runwayPane.getChildren().add(new Text("Runway"));
        io.getChildren().add(new CalculationInput());
    }

    public abstract void build();

    public Scene setScene() {
        Scene currentScene = controller.getScene();
        Scene scene = new Scene(root, currentScene.getWidth(), currentScene.getHeight());
        this.scene = scene;
        return scene;
    }

    public Scene getScene() {return scene;}

    /**
     * Set the stylesheet of the scene.
     * <p>
     * File name should be the full name with file type e.g. "menu.css".
     * Will commonly be used in the build method.
     * @param fileName Name of the stylesheet file i the resources folder
     */
    protected void setStylesheet(String fileName) {
        root.getStylesheets().add(this.getClass().getResource("/".concat(fileName)).toExternalForm());
    }

}
