package de.dataport.Objekte;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import de.dataport.datastructures.Gameobject;
import de.dataport.window.Main;
import de.dataport.window.Start;

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
	private ImageIcon image;

	public Point getVorherige_koord() {
		return vorherige_koord;
	}

	public void setVorherige_koord(Point vorherige_koord) {
		this.vorherige_koord = vorherige_koord;
	}

	public ImageIcon getImage() {
		return image;
	}

	public void setImage(String source) {
		this.image = new ImageIcon(Start.class.getResource(source));
	}

	public static int getBreite() {
		return breite;
	}

	public static void setBreite(int breite) {
		Spielfigur.breite = breite;
	}

	public Spielfigur(int x, int y, String imageSource) {
		super(x, y);
		this.setImage(imageSource);
		this.setHeight(getImage().getIconHeight());
		this.setWidth(getImage().getIconWidth());
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
