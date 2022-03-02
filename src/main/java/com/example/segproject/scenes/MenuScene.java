package com.example.segproject.scenes;

import com.example.segproject.SceneController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

import static com.example.segproject.App.shutdown;

public class MenuScene extends BaseScene {
    public MenuScene (SceneController controller) {
        super(controller);
        root = new BorderPane();
    }

    public void build() {
        var vBox = new VBox();
        root.getChildren().add(vBox);

        var buttonSideScene = new Button("SideScene");
        var buttonTopScene = new Button("TopScene");
        var buttonDoubleScene = new Button("DoubleScene");
        var buttonExit = new Button("Exit");

        vBox.getChildren().addAll(buttonSideScene, buttonTopScene, buttonDoubleScene, buttonExit);

        buttonSideScene.setOnAction(this::openSideScene);
        buttonTopScene.setOnAction(this::openTopScene);
        buttonDoubleScene.setOnAction(this::openDoubleScene);
        buttonExit.setOnAction(this::exit);
    }

    private void openSideScene(ActionEvent actionEvent) {controller.openSideScene();}
    private void openTopScene(ActionEvent actionEvent) {controller.openTopScene();}
    private void openDoubleScene(ActionEvent actionEvent) {controller.openDoubleScene();}
    private void exit(ActionEvent actionEvent) {shutdown();}
}
