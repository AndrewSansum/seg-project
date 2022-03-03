package com.example.segproject;

public class App {
    public static void main( String[] args ) {
        Runway run =	new Runway(10, 5, 6);
        Canvas can =	new Canvas(run);
        Obstacle obs =	new Obstacle(1,1,3);
        obs.setObstacle(2, 2, 2);
        can.addObstacleToTileMap3D(obs);
        can.generateTileMapTopDownView();
        can.generateTileMapSideView();
		can.printTopDownView();
		System.out.println();
        can.printSideView();
    }
}
