package com.example.segproject.components;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

/**
 * UI Component for displaying the distances on the runway
 */
public class DistanceIndicator extends Pane {
    private double startX;
    private double endX;
    private double y;
    private String text;
    private int layer;

    private Line left;
    private Line center;
    private Line right;
    private Label label;

    /**
     * Constructor for when runway is an image
     * @param runway the runway
     * @param x1 the start x
     * @param x2 the end x
     * @param text the label text
     * @param layer the layer the indicator is on
     */
    public DistanceIndicator (ImageView runway, double x1, double x2, String text, int layer) {
        this.y = runway.getLayoutY() + runway.getFitHeight();
        commonConstruct(x1, x2, text, layer);
    }

    /**
     * Constructor for when runway is a rectangle
     * @param runway the runway
     * @param x1 the start x
     * @param x2 the end x
     * @param text the label text
     * @param layer the layer the indicator is on
     */
    public DistanceIndicator(Rectangle runway, double x1, double x2, String text, int layer) {
        this.y = runway.getY() + runway.getHeight();
        commonConstruct(x1, x2, text, layer);
    }

    /**
     * Called by both constructor, constructs all elements that are common
     * @param x1 the start x
     * @param x2 the end x
     * @param text the label text
     * @param layer the layer the indicator is on
     */
    private void commonConstruct (double x1, double x2, String text, int layer) {
        this.startX = x1;
        this.endX = x2;
        this.text = text;
        this.layer = layer;

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
        label.setLayoutX((x2 - x1) * 0.5 - (label.getWidth()));
        label.setLayoutY(6);

        this.getChildren().addAll(left, center, right, label);
        this.setLayoutY(this.y + (5 * (layer + 1)) + (10 * layer));
        this.setLayoutX(x1);
        disable();
    }

    /**
     * Disables all of the elements of the indicator
     */
    public void disable() {
        left.setDisable(true);
        right.setDisable(true);
        center.setDisable(true);
        label.setDisable(true);
    }

    /**
     * Enables all of the elements of the indicator
     */
    public void enable() {
        left.setDisable(false);
        right.setDisable(false);
        center.setDisable(false);
        label.setDisable(false);
    }

    /**
     * Updates the values of the indicator
     * @param x1 the start x
     * @param x2 the end x
     * @param text the label text
     * @param layer the layer the indicator is on
     */
    public void update (double x1, double x2, String text, int layer) {
        this.startX = x1;
        this.endX = x2;
        this.text = text;
        this.layer = layer;

        enable();

        this.setLayoutX(x1);
        this.setLayoutY(this.y  + (5 * (layer + 1)) + (10 * layer));
        right.setLayoutX(x2 - x1);
        center.setEndX(x2 - x1);
        label.setLayoutX((x2 - x1) * 0.5 - (label.getWidth()));
        label.setText(text);
    }

    /**
     * Rotates the indicator
     */
    public void rotate(int bearing) {

    }

    public void setStartX (double startX) {this.startX = startX;}
    public void setEndX (double endX) {this.endX = endX;}
    public void setY (double y) {this.y = y;}
    public void setText (String text) {this.text = text;}
    public void setLayer (int layer) {this.layer = layer;}

    public double getStartX() {return this.startX;}
    public double getEndX() {return this.endX;}
    public double getY() {return this.y;}
    public String getText() {return this.text;}
    public int getLayer() {return this.layer;}

}
