package com.example.segproject.components;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class DistanceIndicator extends Pane {
    private double startX;
    private double startY;
    private double y;
    private String text;
    private int layer;

    private Line left;
    private Line center;
    private Line right;
    private Label label;

    public DistanceIndicator (ImageView runway, double x1, double x2, String text, int layer) {
        this.y = runway.getLayoutY() + runway.getFitHeight() + (5 * (layer + 1)) + (10 * layer);
        commonConstruct(x1, x2, text);
    }

    public DistanceIndicator(Rectangle runway, double x1, double x2, String text, int layer) {
        this.y = runway.getY() + runway.getHeight() + (5 * (layer + 1)) + (10 * layer);
        commonConstruct(x1, x2, text);
    }

    private void commonConstruct (double x1, double x2, String text) {
        left = new Line ();
        left.setLayoutX(0);
        left.setLayoutY(0);
        left.setEndY(10);

        right = new Line ();
        right.setLayoutX(x2 - x1);
        right.setLayoutY(0);
        right.setEndY(10);

        center = new Line ();
        center.setLayoutX(0);
        center.setEndX(x2 - x1);
        center.setLayoutY(5);

        label = new Label(text);
        label.setLayoutX((x2 - x1) * 0.5 - (label.getWidth())); ///////////////////
        label.setLayoutY(6);

        this.getChildren().addAll(left, center, right, label);
        this.setLayoutY(this.y);
        this.setLayoutX(x1);
    }

    public void disable() {
        left.setDisable(true);
        right.setDisable(true);
        center.setDisable(true);
        label.setDisable(true);
    }

    public void enable() {
        left.setDisable(false);
        right.setDisable(false);
        center.setDisable(false);
        label.setDisable(false);
    }

    public void updateValues (double x1, double x2, String text) {
        this.setLayoutX(x1);
        right.setLayoutX(x2 - x1);
        center.setEndX(x2 - x1);
        label.setLayoutX((x2 - x1) * 0.5 - (label.getWidth()));
        label.setText(text);
    }

}
