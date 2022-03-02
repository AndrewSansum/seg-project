package com.example.segproject;

import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.example.segproject.scenes.*;

public class SceneController {

    private final int resH;
    private final int resV;

    private final Stage stage;
    private final String title;
    private BaseScene currentScene;
    private Scene scene;

    public SceneController(Stage stage, int resH, int resV, String title) {
        this.resH = resH;
        this.resV = resV;
        this.stage = stage;
        this.title = title;

        createStage();

        this.scene = new Scene(new Pane(), this.resH, this.resV, Color.BLACK);
        stage.setScene(this.scene);

        openMenuScene();
    }

    private void createStage() {
        stage.setTitle(this.title);
        stage.setMinWidth(this.resH);
        stage.setMinHeight(this.resV);
        stage.setOnCloseRequest(ev -> App.shutdown());
    }

    private void loadScene(BaseScene newScene) {
        newScene.build();
        currentScene = newScene;
        scene = newScene.setScene();
        stage.setScene(scene);
    }

    public void openMenuScene() {loadScene(new MenuScene(this));}
    public void openSideScene() {loadScene(new SideScene(this));}
    public void openTopScene() {loadScene(new TopScene(this));}
    public void openDoubleScene() {;}

    public Scene getScene() {return scene;}

    public int getWidth() {return resH;}
    public int getHeight() {return resV;}
}
