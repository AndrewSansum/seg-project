package com.example.segproject.utils;

public class Padding {
	public static Integer	paddingLend		= 0;
	public static Integer	paddingLstart	= 0;
	public static Integer	paddingWleft	= 0;
	public static Integer	paddingWright	= 0;

	public static Integer[][][] addLendPadding(Integer[][][] tileMap3D, Integer paddLength) {
		Integer[][][] tileMap3DCopy = new Integer[tileMap3D.length][tileMap3D[0].length + paddLength][tileMap3D[0][0].length];
		Integer x = 0;

		for (Integer y = 0; y < tileMap3D.length; y++)
            for (x = 0; x < tileMap3D[0].length; x++)
                for (Integer z = 0; z < tileMap3D[0][0].length; z++)
					tileMap3DCopy[y][x][z] = tileMap3D[y][x][z];
		for (Integer y_b = 0; y_b < tileMap3DCopy.length; y_b++)
			for (Integer x_b = x; x_b < tileMap3DCopy[0].length; x_b++)
				for (Integer z_b = 0; z_b < tileMap3DCopy[0][0].length; z_b++) {
					tileMap3DCopy[y_b][x_b][z_b] = 3;
					if (y_b == tileMap3DCopy.length - 1)
						tileMap3DCopy[y_b][x_b][z_b] = 3;
					else
						tileMap3DCopy[y_b][x_b][z_b] = 0;
				}
		
		Padding.paddingLend = paddLength;
		return (tileMap3DCopy);
	}

	public static Integer[][][] addLstartPadding(Integer[][][] tileMap3D, Integer paddLength) {
		Integer[][][] tileMap3DCopy = new Integer[tileMap3D.length][tileMap3D[0].length + paddLength][tileMap3D[0][0].length];

		for (Integer y = 0; y < tileMap3DCopy.length; y++)
			for (Integer x = 0; x < paddLength; x++)
				for (Integer z = 0; z < tileMap3DCopy[0][0].length; z++) {
					tileMap3DCopy[y][x][z] = 3;
					if (y == tileMap3DCopy.length - 1)
						tileMap3DCopy[y][x][z] = 3;
					else
						tileMap3DCopy[y][x][z] = 0;
				}
		for (Integer y_b = 0; y_b < tileMap3DCopy.length; y_b++)
			for (Integer x_b = paddLength; x_b < tileMap3DCopy[0].length; x_b++)
				for (Integer z_b = 0; z_b < tileMap3DCopy[0][0].length; z_b++)
					tileMap3DCopy[y_b][x_b][z_b] = tileMap3D[y_b][x_b - paddLength][z_b];
		Padding.paddingLstart = paddLength;
		return (tileMap3DCopy);
	}

	public static Integer[][][] addWleftPadding(Integer[][][] tileMap3D, Integer paddLength) {
		Integer[][][] tileMap3DCopy = new Integer[tileMap3D.length][tileMap3D[0].length][tileMap3D[0][0].length + paddLength];

		for (Integer y = 0; y < tileMap3DCopy.length; y++)
			for (Integer x = 0; x < tileMap3DCopy[0].length; x++)
				for (Integer z = 0; z < paddLength; z++) {
					tileMap3DCopy[y][x][z] = 3;
					if (y == tileMap3DCopy.length - 1)
						tileMap3DCopy[y][x][z] = 3;
					else
						tileMap3DCopy[y][x][z] = 0;
				}
		for (Integer y_b = 0; y_b < tileMap3DCopy.length; y_b++)
			for (Integer x_b = 0; x_b < tileMap3DCopy[0].length; x_b++)
				for (Integer z_b = paddLength; z_b < tileMap3DCopy[0][0].length; z_b++)
					tileMap3DCopy[y_b][x_b][z_b] = tileMap3D[y_b][x_b][z_b - paddLength];
		Padding.paddingWleft = paddLength;
		return (tileMap3DCopy);
	}

	public static Integer[][][] addWrightPadding(Integer[][][] tileMap3D, Integer paddLength) {
		Integer[][][] tileMap3DCopy = new Integer[tileMap3D.length][tileMap3D[0].length][tileMap3D[0][0].length + paddLength];
		Integer z = 0;

		for (Integer y = 0; y < tileMap3D.length; y++)
            for (Integer x = 0; x < tileMap3D[0].length; x++)
                for (z = 0; z < tileMap3D[0][0].length; z++)
					tileMap3DCopy[y][x][z] = tileMap3D[y][x][z];

		for (Integer y_b = 0; y_b < tileMap3DCopy.length; y_b++)
			for (Integer x_b = 0; x_b < tileMap3DCopy[0].length; x_b++)
				for (Integer z_b = z; z_b < tileMap3DCopy[0][0].length; z_b++) {
					tileMap3DCopy[y_b][x_b][z_b] = 3;
					if (y_b == tileMap3DCopy.length - 1)
						tileMap3DCopy[y_b][x_b][z_b] = 3;
					else
						tileMap3DCopy[y_b][x_b][z_b] = 0;
				}
		Padding.paddingWright = paddLength;
		return (tileMap3DCopy);
	}

	public static Integer[][][] addPadding(Integer[][][] tileMap3D) {
		tileMap3D = Padding.addLstartPadding(tileMap3D, 2);
		tileMap3D = Padding.addLendPadding(tileMap3D, 2);
		tileMap3D = Padding.addWleftPadding(tileMap3D, 2);
		tileMap3D = Padding.addWrightPadding(tileMap3D, 2);
		return (tileMap3D);
	}
}
