package com.example.segproject.scenes;

import com.example.segproject.Calculations;
import com.example.segproject.SceneController;
import com.example.segproject.components.CalculationInput;
import com.example.segproject.components.CalculationOutput;
import com.example.segproject.components.DistanceIndicator;

import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
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

    protected Boolean rotationEnabled = false;

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
        viewMenu.getItems().addAll(topMenuItem, sideMenuItem);
        viewMenu.getItems().add(bothMenuItem);

        Menu settingsMenu = new Menu("Settings");
        MenuItem rotationMenuItem = new MenuItem("Enable View Rotation");
        StringProperty rotationMenuItemText = rotationMenuItem.textProperty();
        rotationMenuItem.setOnAction(h -> {
            if (rotationEnabled) {
                rotationEnabled = false;
                rotationMenuItemText.set("Enable View Rotation");
            } else {
                rotationEnabled = true;
                rotationMenuItemText.set("Disable View Rotation");
            }
        });
        settingsMenu.getItems().addAll(rotationMenuItem);

        Menu importMenu = new Menu("Import");
        MenuItem importXML = new MenuItem("Import XML");
        importMenu.getItems().addAll(importXML);

        Menu exportMenu = new Menu("Export");
        MenuItem exportAsXML = new MenuItem("Export as XML");
        MenuItem exportAsJPEG = new MenuItem("Export as JPEG");
        exportAsJPEG.setOnAction(h -> takeScreenshot(root, "jpg"));
        MenuItem exportAsPNG = new MenuItem("Export as PNG");
        exportAsPNG.setOnAction(h -> takeScreenshot(root, "png"));
        MenuItem exportAsGIF = new MenuItem("Export as GIF");
        exportAsGIF.setOnAction(h -> takeScreenshot(root, "gif"));
        exportMenu.getItems().addAll(exportAsXML, exportAsJPEG, exportAsPNG, exportAsGIF);

        toolbar.getMenus().addAll(fileMenu, viewMenu, settingsMenu, importMenu, exportMenu);
        root.setTop(toolbar);

        root.setCenter(runwayPane);
        root.setRight(io);

        root.setMaxWidth(controller.getWidth());
		root.setMaxHeight(controller.getHeight());

        runwayPane.setMinWidth(controller.getWidth() * 0.66);
        io.setMinWidth(controller.getWidth() * 0.33);

        inputs = new CalculationInput(controller);
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
	
	public Rectangle createObstable(Double x, Double y) {
		Rectangle obstacle = new Rectangle(x, y, 50, 50);
        obstacle.setFill(Color.ORANGE);
		return obstacle;
	}

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

    protected void rotate(int bearing) {
        int rotateVal = 0;

        if (bearing == 36 || bearing == 18) {
            rotateVal = 90;
        } else if (bearing == 27 || bearing == 9) {
            rotateVal = 0;
        } else if (bearing == 1 || bearing == 17 || bearing == 35 || bearing == 19){
            rotateVal = 80;
        } else if (bearing == 2 || bearing == 16 || bearing == 34 || bearing == 20){
            rotateVal = 70;
        } else if (bearing == 3 || bearing == 15 || bearing == 33 || bearing == 21){
            rotateVal = 60;
        } else if (bearing == 4 || bearing == 14 || bearing == 32 || bearing == 22){
            rotateVal = 50;
        } else if (bearing == 5 || bearing == 13 || bearing == 31 || bearing == 23){
            rotateVal = 40;
        } else if (bearing == 6 || bearing == 12 || bearing == 30 || bearing == 24){
            rotateVal = 30;
        } else if (bearing == 7 || bearing == 11 || bearing == 29 || bearing == 25){
            rotateVal = 20;
        } else if (bearing == 8 || bearing == 10 || bearing == 28 || bearing == 26){
            rotateVal = 10;
        }

        if ((19 <= bearing && bearing <= 27) || (1 <= bearing && bearing <= 8)) {
            rotateVal = rotateVal * (-1);
        }

        runwayPane.setRotate(rotateVal);
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

    protected void setIndicatorsToDarkMode(DistanceIndicator[] indicators) {
        for (DistanceIndicator indicator:indicators) {
            indicator.setColor(Color.WHITE);
        }
    }

    protected void setIndicatorsToLightMode(DistanceIndicator[] indicators) {
        for (DistanceIndicator indicator:indicators) {
            indicator.setColor(Color.BLACK);
        }
    }

    protected void takeScreenshot(Node node, String format) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        Bounds bounds = node.getLayoutBounds();
        int imageWidth = (int) Math.round(bounds.getWidth());
        int imageHeight = (int) Math.round(bounds.getHeight());

        WritableImage screenshot = new WritableImage(imageWidth, imageHeight);
        screenshot = node.snapshot(params, screenshot);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Screenshot");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(format.toUpperCase(), "*." + format),
                new FileChooser.ExtensionFilter("All Images", "*.*")
        );


        File file = fileChooser.showSaveDialog(controller.getStage());

        try {
            if (file != null) {
                if (format.equals("jpg")) {
                    BufferedImage bImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
                    ImageIO.write(SwingFXUtils.fromFXImage(screenshot, bImage), format, file);
                } else {
                    ImageIO.write(SwingFXUtils.fromFXImage(screenshot, null), format, file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
