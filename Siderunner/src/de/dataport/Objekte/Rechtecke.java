package de.dataport.Objekte;

public class Rechtecke{
	int x,y,breite,hoehe;

	public Rechtecke(int x, int y, int breite, int hoehe) {
		super();
		this.x = x;
		this.y = y;
		this.breite = breite;
		this.hoehe = hoehe;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getBreite() {
		return breite;
	}

	public void setBreite(int breite) {
		this.breite = breite;
	}

	public int getHoehe() {
		return hoehe;
	}

	public void setHoehe(int hoehe) {
		this.hoehe = hoehe;
	}
	
}