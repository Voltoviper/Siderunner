package de.dataport.datastructures;

import javax.swing.ImageIcon;

import de.dataport.window.Start;

/**
 * Gameobject Datenstruktur. Alle Spielelemente (Ausnahme Spielfigur) Sind aus
 * dieses Klasse geerbt.
 * 
 * @author Jan Koch
 *
 */
public class Gameobject implements Comparable<Gameobject> {

	private Integer x;
	private Integer y;
	private Integer width;
	private Integer height;
	private ImageIcon image;
	private String imageSource;

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

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon image) {
		this.image = image;
	}

	public void setImageSource(String imageSource) {
		this.imageSource = imageSource;
		if (Start.class.getResource(imageSource) != null)
			this.image = new ImageIcon(Start.class.getResource(imageSource));
		else
			this.image = new ImageIcon(imageSource);
	}

	public String getImageSource() {
		return imageSource;
	}

	public Gameobject(Integer x, Integer y, Integer width, Integer heigth) {
		super();
		initialize(x, y, width, heigth);
	}

	public Gameobject(Integer x, Integer y, String imageSource) {
		super();
		setImageSource(imageSource);
		initialize(x, y, image.getIconWidth(), image.getIconHeight());
	}

	private void initialize(Integer x, Integer y, Integer width, Integer heigth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = heigth;
	}

	public Gameobject() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((height == null) ? 0 : height.hashCode());
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
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
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
