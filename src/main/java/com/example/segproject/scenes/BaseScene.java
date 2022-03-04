package com.example.segproject.scenes;

import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static com.example.segproject.App.shutdown;

public abstract class BaseScene {

    protected SceneController controller;
    protected Scene scene;

    protected BorderPane root;
    protected StackPane runwayPane;
    protected VBox io;
    protected CalculationInput inputs;
    protected MenuBar toolbar;

    public BaseScene(SceneController controller) {
        this.controller = controller;
    }

    protected void setupDefaultScene() {
        root = new BorderPane();
        runwayPane = new StackPane();
        io = new VBox();

        toolbar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem mainMenuItem = new MenuItem("Main Menu");
        MenuItem exitMenuItem = new MenuItem("Exit");
        mainMenuItem.setOnAction(h -> controller.openMenuScene());
        exitMenuItem.setOnAction(h -> shutdown());
        fileMenu.getItems().addAll(mainMenuItem, exitMenuItem);

        Menu viewMenu = new Menu("View");
        MenuItem topMenuItem = new MenuItem("Top View");
        MenuItem sideMenuItem = new MenuItem("Side View");
        MenuItem bothMenuItem = new MenuItem("Top & Side View");
        topMenuItem.setOnAction(h -> controller.openTopScene());
        sideMenuItem.setOnAction(h -> controller.openSideScene());
        bothMenuItem.setOnAction(h -> controller.openDoubleScene());
        viewMenu.getItems().addAll(topMenuItem, sideMenuItem, bothMenuItem);

        toolbar.getMenus().addAll(fileMenu, viewMenu);
        root.setTop(toolbar);

        root.setCenter(runwayPane);
        root.setRight(io);

        root.setMaxWidth(controller.getWidth());
        root.setMaxHeight(controller.getHeight());

        runwayPane.setMinWidth(controller.getWidth() * 0.66);
        io.setMinWidth(controller.getWidth() * 0.33);

        runwayPane.getChildren().add(new Text("Runway"));

        inputs = new CalculationInput();
        io.getChildren().add(inputs);
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
