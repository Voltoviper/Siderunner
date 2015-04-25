package de.dataport.datastructures;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

import de.dataport.level.Level;

public class Gameblock extends Gameobject {

	private Boolean isDeadly;
	private String name;
	private Color color;
	private Boolean multiUsage = true;

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

	public Gameblock(Integer x, Integer y, Integer width, Integer heigth, Boolean isDeadly, String name, Color color) {
		super(x, y, width, heigth);
		this.isDeadly = isDeadly;
		this.name = name;
		this.color = color;
	}

	/** Info for the Leveleditor-View*/
	public String infoIsDeadly() {
		return ((getIsDeadly() == true) ? "isDeadly" : "");
	}
	
	/** Info for the Leveleditor-View*/
	public String infoSize() {
		return getWidth() + "x" + getHeigth();
	}

	/** Painting and verification of the Gameblock-object */ 
	public void paint(Canvas canvas, Level level) {
		if (multiUsage) {

			if (!level.contentTooCloseTo(this)) {
				Graphics g = canvas.getGraphics();
				g.setColor(getColor());
				g.fillRect(getX() - getWidth() / 2, getY() - getHeigth() / 2, getWidth(), getHeigth());

				if (getName().equals("Spawn") || getName().equals("Goal")) {
					if (getName().equals("Spawn"))
						level.setSpawn(this);
					if (getName().equals("Goal"))
						level.setGoal(this);
					multiUsage = !multiUsage;
				} else
					level.addBlock(this);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((isDeadly == null) ? 0 : isDeadly.hashCode());
		result = prime * result + ((multiUsage == null) ? 0 : multiUsage.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gameblock other = (Gameblock) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (isDeadly == null) {
			if (other.isDeadly != null)
				return false;
		} else if (!isDeadly.equals(other.isDeadly))
			return false;
		if (multiUsage == null) {
			if (other.multiUsage != null)
				return false;
		} else if (!multiUsage.equals(other.multiUsage))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
