package de.dataport.Objekte;

import de.dataport.datastructures.Gameobject;

public class Spielfigur extends Gameobject {
	
	private static int geschwindigkeit=10;
	private static int breite=50, hoehe=85;
	public Spielfigur(int x, int y) {
		super(x, y);
		this.setHeigth(hoehe);
		this.setWidth(breite);
	}

	public static int getGeschwindigkeit() {
		return geschwindigkeit;
	}
	public static void setGeschwindigkeit(int geschwindigkeit) {
		Spielfigur.geschwindigkeit = geschwindigkeit;
	}
	
	
}
