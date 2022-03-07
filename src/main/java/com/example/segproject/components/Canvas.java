package com.example.segproject.components;

import com.example.segproject.utils.Point3D;
import com.example.segproject.utils.Padding;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane; 
import java.util.ArrayList;

/**
 * Class where you'll find methods to
	 * getters/setters a 3D tile map from runway and obstacles infos (input format to be defined)
	 * getters/setters top-down view from the 3D tile map
	 * getters/setters side view from the 3D tile map
 */
public class Canvas {
	private Integer 			imgSz;
	private Integer[][][]		tileMap3D;
	private Integer[][]			tileMapSideView;
	private Integer[][]			tileMapTopDownView;
	private ArrayList<Obstacle>	obstacleList;
	private Runway				runway;

	public Canvas(Runway runway, Integer imgSz) {
		Integer heigth		= runway.getHeigth();
		Integer length		= runway.getLength();
		Integer width		= runway.getWidth();
		this.runway			= runway;
		this.tileMap3D		= new Integer[heigth][length][width];
		this.obstacleList	= new ArrayList<Obstacle>();
		this.imgSz			= imgSz;

		for(Integer y = 0; y < heigth; y++)
			for(Integer x = 0; x < length; x++)
				for(Integer z = 0; z < width; z++)
					this.tileMap3D[y][x][z] = 0;
		for(Integer x = 0; x < length; x++)
			for(Integer z = 0; z < width; z++) {
				if (z == width / 2 && x % 3 != 0)
					this.tileMap3D[heigth - 1][x][z] = 2;
				else
					this.tileMap3D[heigth - 1][x][z] = 1;
				}
		this.tileMap3D = Padding.addPadding(this.tileMap3D);
		generateTileMapTopDownView();
		generateTileMapSideView();
	}

	public TilePane renderView(String title) {
		Integer[][] grid	= new Integer[0][0];
		TilePane tilePane	= new TilePane();

		if (title.equals("Side View")) {
			grid = this.tileMapSideView;
			this.imgSz = 39;
		} else 
			grid = this.tileMapTopDownView;
		tilePane.setMaxSize(grid[0].length * this.imgSz, grid.length * this.imgSz);
		tilePane.setMinSize(grid[0].length * this.imgSz, grid.length * this.imgSz);
		// tilePane.setAlignment(Pos.TOP_CENTER);
		for (int y = 0; y <  grid.length; y++) {
			for (int x = 0; x <  grid[0].length; x++) {
				ImageView imageView = new ImageView();
				if ( grid[y][x] == 0)
					imageView	= new ImageView("file:src/main/resources/void.png");
				if ( grid[y][x] == 1)
					imageView	= new ImageView("file:src/main/resources/ground.png");
				if ( grid[y][x] == 2)
					imageView	= new ImageView("file:src/main/resources/runway_line.png");
				if ( grid[y][x] == 5)
					imageView	= new ImageView("file:src/main/resources/obs.png");
				if ( grid[y][x] == 3)
					imageView	= new ImageView("file:src/main/resources/grass.png");
				imageView.setFitWidth(this.imgSz);
				// imageView.setPreserveRatio(true);
				tilePane.getChildren().add(imageView);
			}
		}
		return (tilePane);
		// Scene scene = new Scene(tilePane, this.imgSz * grid[0].length, this.imgSz * grid.length);
		// stage.setTitle(title);
		// stage.setScene(scene);
		// stage.show();
	}

	public void generateTileMapSideView() {
		Integer heigth			= this.tileMap3D.length;
		Integer length			= this.tileMap3D[0].length;
		Integer width			= this.tileMap3D[0][0].length;
		this.tileMapSideView	= new Integer[heigth][length];

		for (Integer y = 0; y < heigth; y++)
			for (Integer x = 0; x < length; x++)
				this.tileMapSideView[y][x] = 0;
		for(Integer y = 0; y < heigth; y++)
			for(Integer x = 0; x < length; x++) {
				for(Integer z = 0; z < width; z++) {
					if (this.tileMap3D[y][x][z] != 0) {
						this.tileMapSideView[y][x] = this.tileMap3D[y][x][z];
						z = width;
					}
				}
			}
		for (Integer x = 0; x < length - Padding.paddingLend; x++) {
			if (x + 1 > Padding.paddingLstart)
				this.tileMapSideView[heigth - 1][x] = 1;
		}
	}

	public void generateTileMapTopDownView() {
		Integer heigth			= this.tileMap3D.length;
		Integer length			= this.tileMap3D[0].length;
		Integer width			= this.tileMap3D[0][0].length;
		this.tileMapTopDownView	= new Integer[length][width];

		for (Integer y = 0; y < length; y++)
			for (Integer x = 0; x < width; x++)
				this.tileMapTopDownView[y][x] = 0;
		for(Integer x = 0; x < length; x++)
			for(Integer z = 0; z < width; z++) {
				for(Integer y = 0; y < heigth; y++) {
					if (this.tileMap3D[y][x][z] != 0) {
						this.tileMapTopDownView[x][z] = this.tileMap3D[y][x][z];
						y = heigth;
					}
				}
			}
	}

	public void addObstacleToTileMap3D(Obstacle obs) {
		Point3D coord			= obs.getPoint3d();
		Integer[][][] obsMat	= obs.getObstacleMatrix();
		Integer x				= coord.getX();
		Integer y				= coord.getY();
		Integer z				= coord.getZ();

        for(Integer y_s = 0; y_s < obsMat.length && y_s + y < this.tileMap3D.length; y_s++){
            for (Integer x_s = 0; x_s < obsMat[0].length && x_s + x < this.tileMap3D[0].length; x_s++)
                for (Integer z_s = 0; z_s < obsMat[0][0].length && z_s + z < this.tileMap3D[0][0].length; z_s++)
					this.tileMap3D[y_s + y][x_s + x + Padding.paddingLstart][z_s + z + Padding.paddingWleft] = obsMat[y_s][x_s][z_s];
			}
		this.obstacleList.add(obs);
		generateTileMapTopDownView();
		generateTileMapSideView();
	}

	public void removeObstacle(Obstacle obs) {
		Integer heigth	= runway.getHeigth();
		Integer length	= runway.getLength();
		Integer width	= runway.getWidth();		
		
		for (Obstacle o : this.obstacleList) {
			if (o.equals(obs)) {
				this.obstacleList.remove(o);
				this.tileMap3D	= new Integer[heigth][length][width];
				addListOfObstaclesToTileMap3D(this.obstacleList);
				return ;
			}
		}
		generateTileMapTopDownView();
		generateTileMapSideView();
	}

	public void addListOfObstaclesToTileMap3D(ArrayList<Obstacle> obsLst) {
		for (Integer i = 0; i < obsLst.size(); i++)
			addObstacleToTileMap3D(obsLst.get(i));
	}

	public void removeListOfObstacle(ArrayList<Obstacle> obsLst) {
		for (Integer i = 0; i < obsLst.size(); i++)
			removeObstacle(obsLst.get(i));
	}

	public void moveObstacle(Obstacle obs, Point3D newCoord) {
		// To be done
	}

	public void printTopDownView() {
		for (Integer i = 0; i < this.tileMapTopDownView.length; i++){
			for (Integer j = 0; j < this.tileMapTopDownView[0].length; j++)
				System.out.print(this.tileMapTopDownView[i][j].toString() + " ");
			System.out.println();
		}
	}

	public void printSideView() {
		for (Integer i = 0; i < this.tileMapSideView.length; i++){
			for (Integer j = 0; j < this.tileMapSideView[0].length; j++)
				System.out.print(this.tileMapSideView[i][j].toString() + " ");
			System.out.println();
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
