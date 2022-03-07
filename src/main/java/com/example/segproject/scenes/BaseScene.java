package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;
import com.example.segproject.components.Canvas;
import com.example.segproject.components.Obstacle;
import com.example.segproject.components.Runway;

import com.example.segproject.components.CalculationOutput;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import static com.example.segproject.App.shutdown;

/**
 * Base class from which all scene classes will inherit
 * Provides basic functionality required by the SceneController
 * Provides a setupDefaultScene method to be used by all non-menu scenes
 * Provides functionality for setting the style sheet of a scene
 */
public abstract class BaseScene {

    protected SceneController controller;
    protected Scene scene;

    protected BorderPane root;
    protected StackPane runwayPane;
    protected HBox io;
    protected CalculationInput inputs;
    protected CalculationOutput outputs;
	protected MenuBar toolbar;
	protected Runway run;
	protected Canvas can;
	protected Obstacle obs;

    protected Calculations cal;

    public BaseScene(SceneController controller) {
        this.controller = controller;
        run	= new Runway(20, 11, 20);
		can	= new Canvas(run, 32);
		obs	= new Obstacle(10,run.getHeigth() - 3,2);
    }

    /**
     * Instantiates the foundational ui elements needed for all non-menu scenes
     */
    protected void setupDefaultScene() {
        root = new BorderPane();
        runwayPane = new StackPane();
        io = new HBox();

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

        obs.setObstacle(2, 2, 2);
		can.addObstacleToTileMap3D(obs);

        runwayPane.setMinWidth(controller.getWidth() * 0.66);
        io.setMinWidth(controller.getWidth() * 0.33);

        runwayPane.getChildren().add(new Text("Runway"));

        inputs = new CalculationInput();
        outputs = new CalculationOutput();

        io.getChildren().add(inputs);
        io.getChildren().add(outputs);

        inputs.setOnButtonClicked(this::newValues);
    }

    /**
     * Will be used by children to declare all ui elements in the scene
     */
    public abstract void build();

    /**
     * Used by the SceneController to declare an instance of this class as
     * the current scene to be displayed
     * @return scene
     */
    public Scene setScene() {
        Scene currentScene = controller.getScene();
        Scene scene = new Scene(root, currentScene.getWidth(), currentScene.getHeight());
        this.scene = scene;
        return scene;
    }

    /**
     * Called when calculate button is clicked
     * @param cal new calculated values
     * @param event the button click event, shouldn't be needed for anything
     */
    private void newValues(Calculations cal, ActionEvent event) {
        this.cal = cal;
        outputs.updateValues(cal);
    }

    /**
     * Maybe won't be used at all, idk
     * @return scene
     */
    public Scene getScene() {return scene;}

    /**
     * Set the stylesheet of the scene.
     * <p>
     * File name should be the full name with file type e.g. "menu.css".
     * Will commonly be used in the build method.
     * @param fileName Name of the stylesheet file in the resources folder
     */
    protected void setStylesheet(String fileName) {
        root.getStylesheets().add(this.getClass().getResource("/".concat(fileName)).toExternalForm());
    }

}
