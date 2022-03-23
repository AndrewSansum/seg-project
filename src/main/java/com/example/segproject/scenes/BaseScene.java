package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;

import com.example.segproject.components.CalculationOutput;
import com.example.segproject.components.DistanceIndicator;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

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
    protected Pane runwayPane;
    protected HBox io;
    protected CalculationInput inputs;
    protected CalculationOutput outputs;
	protected MenuBar toolbar;

    protected Calculations cal;
    protected Double runwayPaneCenterX;
    protected Double runwayPaneCenterY;
    protected Integer runwayLength;

    protected ImageView runway;
    protected Rectangle obstacle;

    protected DistanceIndicator toraIndicator;
    protected DistanceIndicator asdaIndicator;
    protected DistanceIndicator todaIndicator;
    protected DistanceIndicator ldaIndicator;
    protected DistanceIndicator distanceFromThresholdIndicator;
    protected DistanceIndicator displacementThresholdIndicator;
    protected DistanceIndicator resaIndicator;
    protected DistanceIndicator stripEndIndicator;
    protected DistanceIndicator blastProtectionIndicator;
    protected DistanceIndicator slopeCalculationIndicator;

    public BaseScene(SceneController controller) {
        this.controller = controller;
    }

    /**
     * Instantiates the foundational ui elements needed for all non-menu scenes
     */
    protected void setupDefaultScene() {
        root = new BorderPane();
        runwayPane = new Pane();
        io = new HBox();

        toolbar = new MenuBar();

        Menu fileMenu = new Menu("File");
        MenuItem exitMenuItem = new MenuItem("Exit");
        exitMenuItem.setOnAction(h -> shutdown());
        fileMenu.getItems().addAll(exitMenuItem);

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

        inputs = new CalculationInput();
        outputs = new CalculationOutput();

        io.getChildren().add(inputs);
        io.getChildren().add(outputs);

        inputs.setOnButtonClicked(this::newValues);

        runwayPaneCenterX = controller.getWidth() * 0.66 * 0.5;
        runwayPaneCenterY = controller.getHeight() * 0.5;

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

    protected void setIndicatorsLabel(DistanceIndicator[] indicators) {
        for (DistanceIndicator indicator:indicators) {
            indicator.setLabelX();
        }
    }

    protected void disableIndicators(DistanceIndicator[] indicators) {
        for (DistanceIndicator indicator:indicators) {
            indicator.disable();
        }
    }

}
