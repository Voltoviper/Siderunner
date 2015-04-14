package de.dataport.berechnungen;

import java.awt.Canvas;
import java.awt.Color;

import de.dataport.window.Main;

public class Boden {

	int x,y;
	int breite, hoehe;
	public Boden(int x, int y, int breite, int hoehe) {
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
	
	public void boden_einpflegen(){
		Canvas boden = new Canvas();
		boden.setBounds(this.x, this.y, this.breite, this.hoehe);
		boden.setBackground(Color.BLACK);
		Main.frmJackRunner.add(boden);
	}
	
	public double[] Koord_links_oben(Boden rect) {
		double[] Koordinaten = new double[2];
		Koordinaten[0] = rect.x;
		Koordinaten[1] = rect.y;
		return Koordinaten;
	}

	public double[] Koord_links_unten(Boden rect) {
		double[] Koordinaten = new double[2];
		Koordinaten[0] = rect.x;
		Koordinaten[1] = rect.y + rect.hoehe;
		return Koordinaten;
	}

	public double[] Koord_rechts_oben(Boden rect) {
		double[] Koordinaten = new double[2];
		Koordinaten[0] = rect.x + rect.breite;
		Koordinaten[1] = rect.y;
		return Koordinaten;
	}

	public double[] Koord_rechts_unten(Boden rect) {
		double[] Koordinaten = new double[2];
		Koordinaten[0] = rect.x + rect.breite;
		Koordinaten[1] = rect.y + rect.hoehe;
		return Koordinaten;
	}
	
}
