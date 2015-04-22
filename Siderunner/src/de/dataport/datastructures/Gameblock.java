package de.dataport.datastructures;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public class Gameblock extends Gameobject {

	private Boolean isDeadly;
	private String name;
	private Color color;
	private final Integer blockSize = 20;

	public Boolean getIsDeadly() {
		return isDeadly;
	}

	public void setIsDeadly(Boolean isDeadly) {
		this.isDeadly = isDeadly;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Integer getBlockSize() {
		return blockSize;
	}
	
	public Gameblock(Integer x, Integer y, Integer width, Integer heigth,
			Boolean isDeadly, String name, Color color) {
		super(x, y, width, heigth);
		this.isDeadly = isDeadly;
		this.name = name;
		this.color = color;
	}

	public String infoIsDeadly(){
		return ((getIsDeadly() == true) ? "isDeadly" : "");
	}
	
	public String infoSize(){
		return  getWidth()+"x"+getHeigth();
	}
	
}
