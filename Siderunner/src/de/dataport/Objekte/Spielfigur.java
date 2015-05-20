package de.dataport.Objekte;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

import de.dataport.datastructures.Gameobject;
import de.dataport.window.Start;

/**
 * Klasse der Spielfigur. 
 * @author chris_000
 *
 */
public class Spielfigur extends Gameobject {
	
	private static int geschwindigkeit=10;
	private static int breite=50, hoehe=85;
	private ImageIcon image;
	
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

	public Spielfigur(int x, int y) {
		super(x, y);
		this.setHeigth(getHoehe());
		this.setWidth(breite);
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
	
	public void repaintPlayer(Canvas canvas){
		Graphics g = canvas.getGraphics();
		g.setColor(Color.BLUE);
		g.drawImage(getImage().getImage(), getX(), getY(), canvas);
	}
	
}
