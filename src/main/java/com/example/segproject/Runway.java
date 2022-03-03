package com.example.segproject;

public class Runway {
	private Integer	length;
	private Integer	width;
	private Integer	heigth;

	public Runway(Integer length, Integer width, Integer heigth) {
		this.length = length;
		this.width = width;
		this.heigth = heigth;
	}

	public Integer getLength() {
		return (this.length);
	}

	public Integer getWidth() {
		return (this.width);
	}

	public Integer getHeigth() {
		return (this.heigth);
	}

	public void setHeigth(Integer height) {
		this.heigth = height;
	}

	public void setWidth(Integer width) {
		this.heigth = width;
	}

	public void setLength(Integer length) {
		this.heigth = length;
	}
}
