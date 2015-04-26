package de.dataport.datastructures;

public class Gameobject implements  Comparable<Gameobject> {

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
	public Gameobject(Integer x, Integer y) {
		super();
		this.x = x; 
		this.y = y;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((heigth == null) ? 0 : heigth.hashCode());
		result = prime * result + ((width == null) ? 0 : width.hashCode());
		result = prime * result + ((x == null) ? 0 : x.hashCode());
		result = prime * result + ((y == null) ? 0 : y.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gameobject other = (Gameobject) obj;
		if (heigth == null) {
			if (other.heigth != null)
				return false;
		} else if (!heigth.equals(other.heigth))
			return false;
		if (width == null) {
			if (other.width != null)
				return false;
		} else if (!width.equals(other.width))
			return false;
		if (x == null) {
			if (other.x != null)
				return false;
		} else if (!x.equals(other.x))
			return false;
		if (y == null) {
			if (other.y != null)
				return false;
		} else if (!y.equals(other.y))
			return false;
		return true;
	}
	
	/** Relative-Position-On-Screen-Sorting */
	@Override
	public int compareTo(Gameobject other) {
		return this.getX().compareTo(other.getX());
	}
	
	

}
