package de.dataport.datastructures;

import java.awt.Color;
import java.io.Serializable;

/**
 * Datenstruktur, die vom Gameobject erbt. Es werden nur einige weitere
 * Eigenschaften festgelegt.
 * 
 * @author Jan Koch
 *
 */
public class Gameblock extends Gameobject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Boolean isDeadly;
	private String name;
	private Color color;

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

	public Gameblock(Integer x, Integer y, Integer width, Integer height, Boolean isDeadly, String name,
			Color color) {
		super(x, y, width, height);
		this.isDeadly = isDeadly;
		this.name = name;
		this.color = color;
	}
	
	public Gameblock(Integer x, Integer y, String imageSource, Boolean isDeadly, String name) {
		super(x, y, imageSource);
		this.isDeadly = isDeadly;
		this.name = name;
	}

	public Gameblock() {
		super();
	}

	/** Testausgabe */
	@Override
	public String toString() {
		return "Gameblock [isDeadly=" + isDeadly + ", name=" + name + ", color=" + color + "]";
	}

	/** Info for the Leveleditor-View */
	public String infoIsDeadly() {
		return ((getIsDeadly() == true) ? "isDeadly" : "");
	}

	/** Info for the Leveleditor-View */
	public String infoSize() {
		return getWidth() + "x" + getHeight();
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((isDeadly == null) ? 0 : isDeadly.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
