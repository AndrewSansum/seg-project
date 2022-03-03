package com.example.segproject;
import java.util.ArrayList;

/**
 * Class where you'll find methods to
	 * getters/setters a 3D tile map from runway and obstacles infos (input format to be defined)
	 * getters/setters top-down view from the 3D tile map
	 * getters/setters side view from the 3D tile map
 */
public class Canvas 
{
	private Integer[][][]	tileMap3D;
	private Integer[][]		tileMapSideView;
	private Integer[][]		tileMapTopDownView;

	public Canvas(Runway runway) {
		Integer heigth = runway.getHeigth();
		Integer length = runway.getLength();
		Integer width = runway.getWidth();
		this.tileMap3D = new Integer[heigth][length][width];

		for(Integer y = 0; y < heigth; y++) {
			for(Integer x = 0; x < length; x++) {
				for(Integer z = 0; z < width; z++) {
					this.tileMap3D[y][x][z] = 0;
				}
			}
		}
		for(Integer x = 0; x < length; x++) {
			for(Integer z = 0; z < width; z++) {
				this.tileMap3D[heigth - 1][x][z] = 1;
			}
		}
	}

	public void addObstacleToTileMap3D(Obstacle obs) {
		Point3D coord = obs.getPoint3d();
		Integer[][][] obstacleMatrix = obs.getObstacleMatrix();
		Integer x = coord.getX();
		Integer y = coord.getY();
		Integer z = coord.getZ();
        for(int y_s = 0; y_s < obstacleMatrix.length && y_s + y < this.tileMap3D.length; y_s++){
            for (int x_s = 0; x_s < obstacleMatrix[0].length && x_s + x < this.tileMap3D[0].length; x_s++)
                for (int z_s = 0; z_s < obstacleMatrix[0][0].length && z_s + z < this.tileMap3D[0][0].length; z_s++)
                    this.tileMap3D[y_s + y][x_s + x][z_s + z] = obstacleMatrix[y_s][x_s][z_s];
        }
	}

	public void addListOfObstaclesToTileMap3D(ArrayList<Obstacle> obsLst) {
		// To be done
	}

	public void removeObstacle(Obstacle obs) {
		// To be done
	}

	public void removeListOfObstacle(ArrayList<Obstacle> obsLst) {
		// To be done
	}

	public void moveObstacle(Obstacle obs, Point3D coord) {
		// To be done
	}

	public void printTopDownView() {
		// To be done
	}

	public void printSideView() {
		for(Integer y = 0; y < this.tileMap3D.length; y++) {
			for(Integer x = 0; x < this.tileMap3D[0].length; x++)
				System.out.print(this.tileMap3D[y][x][0].toString() + " ");
			System.out.print("\n");
		}
	}
	
	public Integer[][][] getTileMap3D() {
		return (this.tileMap3D);
	}

	public Integer[][] getTileMapSideView() {
		return (this.tileMapSideView);
	}

	public Integer[][] getTileMapTopDownView() {
		return (this.tileMapTopDownView);
	}
}
