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
	private Boolean fillDownwards = false;

	public Boolean isFillDownwards() {
		return fillDownwards;
	}

	public void setFillDownwards(boolean fillDownwards) {
		this.fillDownwards = fillDownwards;
	}

	public Boolean isDeadly() {
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

	public Gameblock(Integer x, Integer y, Integer width, Integer height, Boolean isDeadly, String name, Color color,
			Boolean fuellen) {
		super(x, y, width, height);
		this.isDeadly = isDeadly;
		this.name = name;
		this.color = color;
		this.fillDownwards = fuellen;
	}

	public Gameblock(Integer x, Integer y, String imageSource, Boolean isDeadly, String name, Boolean fillDownwards) {
		super(x, y, imageSource);
		this.isDeadly = isDeadly;
		this.name = name;
		this.fillDownwards = fillDownwards;
	}

	public Gameblock() {
		super();
	}

	/** Testausgabe */
	@Override
	public String toString() {
		return "Gameblock [isDeadly=" + isDeadly + ", name=" + name + ", color=" + color + "]";
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

	/** Info for the Leveleditor-View */
	public String infoString() {
		if(isDeadly()==null)setIsDeadly(false);
		if(isFillDownwards()==null)setFillDownwards(false);
		return "<html><body><p style=\"font-size:18f\">" + getName() + "</p><p style=\"font-size:10f\">" + getWidth()
				+ "X" + getHeight() + (isDeadly() ? " - tötlich" : "") + (isFillDownwards() ? " - füllt auf" : "")
				+ "</p></body></html>";
	}

}