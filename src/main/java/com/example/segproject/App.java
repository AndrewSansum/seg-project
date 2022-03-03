package com.example.segproject;

public class App {
    public static void main( String[] args ) {
        Runway run = new Runway(10, 5, 6);
        Canvas can = new Canvas(run);
        Obstacle obs = new Obstacle(1,1,0);
        obs.setObstacle(1, 2, 1);
        can.addObstacleToTileMap3D(obs);
        can.printSideView();
    }
}
