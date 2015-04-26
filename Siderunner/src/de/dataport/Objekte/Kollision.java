package de.dataport.Objekte;

import javax.swing.JLabel;

import de.dataport.berechnungen.Bewegung;
import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;
import de.dataport.window.Main;

public class Kollision {
	boolean kollision;
	public static int[] koordinaten = new int[2];

	public static int[] kollision_rechts(JLabel figur, Level level) {

		for (Gameblock rect : level.getListe()) {
			if (rect != null) {

				if (figur.getX() + figur.getWidth() + Spielfigur.getGeschwindigkeit() > rect.getX()
						&& figur.getX() < rect.getX() + rect.getWidth()) {
					if (figur.getY() <= rect.getY() - Spielfigur.getHoehe()) {
						koordinaten[0] = Main.spielfigur.getX() + Spielfigur.getGeschwindigkeit();
						koordinaten[1] = rect.getY() - Spielfigur.getHoehe();
						Bewegung.huepf2.stop();
						break;
					} else if (figur.getX() + figur.getWidth() <= rect.getX()) {
						koordinaten[0] = rect.getX() - Spielfigur.getBreite();
						koordinaten[1] = Main.spielfigur.getY();
						break;
					}

				} else {
					koordinaten[0] = Main.spielfigur.getX() + Spielfigur.getGeschwindigkeit();
					koordinaten[1] = Main.test.getY() - Spielfigur.getHoehe();
				}
			}
		}
		return koordinaten;

	}

	public static int[] kollision_links(JLabel figur, Level level) {

		for (Gameblock rect : level.getListe()) {
			if (rect != null) {
				if (figur.getX() - Spielfigur.getGeschwindigkeit() < rect.getX() + rect.getWidth()
						&& figur.getX() + Spielfigur.getBreite() > rect.getX()) {
					if (figur.getY() <= rect.getY() - Spielfigur.getHoehe()) {
						koordinaten[0] = Main.spielfigur.getX() - Spielfigur.getGeschwindigkeit();
						koordinaten[1] = rect.getY() - Spielfigur.getHoehe();
						Bewegung.huepf2.stop();
						break;
					} else if (figur.getX() > rect.getX()) {
						koordinaten[0] = rect.getX() + rect.getWidth();
						koordinaten[1] = Main.spielfigur.getY();
						break;
					}

					// koordinaten[0] = level.getListe()[0].getX() -
					// Spielfigur.getBreite();
				} else {
					koordinaten[0] = Main.spielfigur.getX() - Spielfigur.getGeschwindigkeit();
					koordinaten[1] = Main.test.getY() - Spielfigur.getHoehe();
				}
			}
		}
		return koordinaten;

	}

	public static int[] kollision_unten(JLabel figur, Level level) {

		for (Gameblock rect : level.getListe()) {
			if (rect != null) {
				if (figur.getY() + Spielfigur.getHoehe() + 50 > rect.getY()
						&& (figur.getX() + Spielfigur.getBreite() > rect
								.getX() && figur.getX() < rect.getX()
								+ rect.getWidth())) {
					koordinaten[1] = rect.getY() - Spielfigur.getHoehe();

				}

			}

		}

		return koordinaten;

	}
}
