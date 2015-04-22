package de.dataport.datastructures;

public class Gameobject {

	private Integer x;
	private Integer y;
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
	public Gameobject(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}
}
