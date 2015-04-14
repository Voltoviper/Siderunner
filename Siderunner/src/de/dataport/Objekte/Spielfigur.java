package de.dataport.Objekte;

public class Spielfigur {
	
	private int x=5,y=325;
	private static int breite=50, hoehe=85;
	public Spielfigur(int x, int y) {
		super();
		this.x = x;
		this.y = y;
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
	public static int getBreite() {
		return breite;
	}
	public static void setBreite(int breite) {
		Spielfigur.breite = breite;
	}
	public static int getHoehe() {
		return hoehe;
	}
	public static void setHoehe(int hoehe) {
		Spielfigur.hoehe = hoehe;
	}
	
	
}
