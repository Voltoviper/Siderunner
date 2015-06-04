package de.dataport.Objekte;

import java.awt.Point;

import de.dataport.datastructures.Gameobject;

/**
 * Klasse der Spielfigur.
 * 
 * @author chris_000
 *
 */
public class Spielfigur extends Gameobject {

	private static int geschwindigkeit = 10;
	private static int breite = 50, hoehe = 85;
	private Point vorherige_koord = new Point(0,0);

	public Point getVorherige_koord() {
		return vorherige_koord;
	}

	public void setVorherige_koord(Point vorherige_koord) {
		this.vorherige_koord = vorherige_koord;
	}

	public static int getBreite() {
		return breite;
	}

	public static void setBreite(int breite) {
		Spielfigur.breite = breite;
	}

	public Spielfigur(int x, int y, String imageSource) {
		super(x, y, imageSource);
	}

	public static int getGeschwindigkeit() {
		return geschwindigkeit;
	}

	public static void setGeschwindigkeit(int geschwindigkeit) {
		Spielfigur.geschwindigkeit = geschwindigkeit;
	}

	public static int getHoehe() {
		return hoehe;
	}

	public static void setHoehe(int hoehe) {
		Spielfigur.hoehe = hoehe;
	}


}
