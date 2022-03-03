package com.example.segproject;

public class Obstacle {
	private Integer[][][]	obsMatrix;
	private Point3D			coord;

	/**
	 * Creates an obstacle given a set of coordinates. The shape of obstacle
	 * is to be defined in a function
	 * Coordinate values start at the top left of the obstacle
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 */
	public Obstacle(Integer x, Integer y, Integer z) {
		this.coord = new Point3D(x, y, z);
	}

	public void setCubeObstacle(Integer size) {
		Integer[][][] newObsMatrix = new Integer[size][size][size];
		for (Integer y = 0; y < size; y++)
			for (Integer x = 0; x < size; x++)
				for (Integer z = 0; z < size; z++)
					newObsMatrix[y][x][z] = 5;
		obsMatrix = newObsMatrix;
	}

	public void setObstacle(Integer height, Integer length, Integer width) {
		Integer[][][] newObsMatrix = new Integer[height][length][width];
		for (Integer y = 0; y < height; y++)
			for (Integer x = 0; x < length; x++)
				for (Integer z = 0; z < width; z++)
					newObsMatrix[y][x][z] = 5;
		obsMatrix = newObsMatrix;
	}

	public Integer[][][] getObstacleMatrix() {
		return (this.obsMatrix);
	}

	public Point3D getPoint3d() {
		return (this.coord);
	}
}
