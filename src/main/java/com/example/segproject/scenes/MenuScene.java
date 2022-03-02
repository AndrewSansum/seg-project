package com.example.segproject.scenes;

import com.example.segproject.SceneController;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import static com.example.segproject.App.shutdown;

public class MenuScene extends BaseScene {
    public MenuScene (SceneController controller) {
        super(controller);
        root = new BorderPane();
    }

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

    private void openSideScene(ActionEvent actionEvent) {controller.openSideScene();}
    private void openTopScene(ActionEvent actionEvent) {controller.openTopScene();}
    private void openDoubleScene(ActionEvent actionEvent) {controller.openDoubleScene();}
    private void exit(ActionEvent actionEvent) {shutdown();}
}
