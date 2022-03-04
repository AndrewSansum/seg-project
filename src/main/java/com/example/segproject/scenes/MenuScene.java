package com.example.segproject.scenes;

import com.example.segproject.SceneController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import static com.example.segproject.App.shutdown;

/**
 * Class that builds and determines the behaviour of the menu
 */
public class MenuScene extends BaseScene {
    public MenuScene (SceneController controller) {
        super(controller);
        root = new BorderPane();
    }

    /**
     * Builds the ui elements of the scene
     */
    public void build() {
        var buttonBox = new VBox();
        buttonBox.getStyleClass().add("v-box");
        root.setCenter(buttonBox);

        var titleLabel = new Label("Runway Re-declaration Tool");
        titleLabel.getStyleClass().add("titleLabel");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        root.setTop(titleLabel);

        var buttonSideScene = new Button("SideScene");
        var buttonTopScene = new Button("TopScene");
        var buttonDoubleScene = new Button("DoubleScene");
        var buttonExit = new Button("Exit");

        buttonBox.getChildren().addAll(buttonSideScene, buttonTopScene, buttonDoubleScene, buttonExit);

        buttonSideScene.setOnAction(this::openSideScene);
        buttonTopScene.setOnAction(this::openTopScene);
        buttonDoubleScene.setOnAction(this::openDoubleScene);
        buttonExit.setOnAction(this::exit);

        //Fetch and apply the css file "menu.css" from the resources folder. Most styling is done here
        setStylesheet("menu.css");
    }

    /**
     * Calls the scene controller to open the side view
     * @param actionEvent When the corresponding button is clicked
     */
    private void openSideScene(ActionEvent actionEvent) {controller.openSideScene();}

    /**
     * Calls the scene controller to open the top view
     * @param actionEvent When the corresponding button is clicked
     */
    private void openTopScene(ActionEvent actionEvent) {controller.openTopScene();}

    /**
     * Calls the scene controller to open the dual view
     * @param actionEvent When the corresponding button is clicked
     */
    private void openDoubleScene(ActionEvent actionEvent) {controller.openDoubleScene();}

    /**
     * Calls for the application to be closed
     * @param actionEvent When the corresponding button is clicked
     */
    private void exit(ActionEvent actionEvent) {shutdown();}
}
