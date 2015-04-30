package de.dataport.Objekte;

import de.dataport.datastructures.Gameobject;

public class Spielfigur extends Gameobject {
	
	private static int geschwindigkeit=10;
	private static int breite=50, hoehe=85;
	public Spielfigur(int x, int y) {
		super(x, y);

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
	public static int getGeschwindigkeit() {
		return geschwindigkeit;
	}
	public static void setGeschwindigkeit(int geschwindigkeit) {
		Spielfigur.geschwindigkeit = geschwindigkeit;
	}
	
	
}
