package de.dataport.datastructures;

public class Gameobject {

	private Integer x;
	private Integer y;
	private Integer width;
	private Integer heigth;
	
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public Integer getHeigth() {
		return heigth;
	}
	public void setHeigth(Integer heigth) {
		this.heigth = heigth;
	}
	public Gameobject(Integer x, Integer y, Integer width, Integer heigth) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.heigth = heigth;
	}
	

}
