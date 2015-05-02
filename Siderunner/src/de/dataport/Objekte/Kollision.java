package de.dataport.Objekte;

import de.dataport.berechnungen.Bewegung;
import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;
import de.dataport.window.Main;

public class Kollision {
	boolean kollision;
	public static int[] koordinaten = new int[2];
	static Gameblock[] beruehrpunkte = new Gameblock[1000];
	String orientierung;

	public static int[] kollision_rechts(Spielfigur spielfigur, Level level) {
		int i = 0;
		for (Gameblock rect : level.getListe()) {
			if (rect != null) {
			
				if (Main.spieler.getX() + Main.spieler.getWidth()
						+ Spielfigur.getGeschwindigkeit() > rect.getX()
						&& Main.spieler.getX() < rect.getX() + rect.getWidth()) {
					if (Main.spieler.getY() <= rect.getY() - Main.spieler.getHeigth()) {
					//	beruehrpunkte[i] = rect;
						i++;
						koordinaten[0] = Main.spieler.getX()
								+ Spielfigur.getGeschwindigkeit();
						koordinaten[1] = rect.getY() - Main.spieler.getHeigth();
						
						break;
					} else if (Main.spieler.getX() + Main.spieler.getWidth() <= rect.getX()) {
						koordinaten[0] = rect.getX() - Main.spieler.getWidth();
						koordinaten[1] = Main.spieler.getY();
						break;
					}

				} else {
					koordinaten[0] = Main.spieler.getX()
							+ Spielfigur.getGeschwindigkeit();
					//koordinaten[1] = Main.test.getY() - Main.spieler.getHeigth();
				}
			}
		}
		//koordinaten = auswählen(beruehrpunkte, 0);
		return koordinaten;

	}

	public static int[] kollision_links(Spielfigur figur, Level level) {

		for (Gameblock rect : level.getListe()) {
			if (rect != null) {
				if (figur.getX() - Spielfigur.getGeschwindigkeit() < rect
						.getX() + rect.getWidth()
						&& figur.getX() + Main.spieler.getWidth() > rect.getX()) {
					if (figur.getY() <= rect.getY() - Main.spieler.getHeigth()) {
						koordinaten[0] = Main.spieler.getX()
								- Spielfigur.getGeschwindigkeit();
						koordinaten[1] = rect.getY() - Main.spieler.getHeigth();
						Bewegung.huepf.stop();
						break;
					} else if (figur.getX() > rect.getX()) {
						koordinaten[0] = rect.getX() + rect.getWidth();
						koordinaten[1] = Main.spieler.getY();
						break;
					}

					// koordinaten[0] = level.getListe()[0].getX() -
					// Spielfigur.getBreite();
				} else {
					koordinaten[0] = Main.spieler.getX()
							- Spielfigur.getGeschwindigkeit();
					koordinaten[1] = Main.test.getY() - Main.spieler.getHeigth();
				}
			}
		}
		return koordinaten;

	}

	public static int[] kollision_unten(Spielfigur figur, Level level) {

		for (Gameblock rect : level.getListe()) {
			if (rect != null) {
				if (figur.getY() + Main.spieler.getHeigth() + 50 > rect.getY()
						&& (figur.getX() + Main.spieler.getWidth() > rect.getX() && figur
								.getX() < rect.getX() + rect.getWidth())) {
					koordinaten[1] = rect.getY() - Main.spieler.getHeigth()- rect.getHeigth()/2;

				}

			}

		}

		return koordinaten;

	}
	private static int[] auswählen(Gameblock[] liste, int Orientierung){
		switch(Orientierung){
		case 0: 
			
			break;
		}
		
		
		return null;
	}
}
