package de.dataport.Objekte;

import java.util.ArrayList;

import javax.swing.JDialog;

import de.dataport.datastructures.Gameblock;
import de.dataport.window.Gewonnen;
import de.dataport.window.Main;

/**
 * Kollisionsabfragen... Es werden die Listen aus der Klasse Level verwendet. Um
 * einen Kontakt zu überprüfen.
 * 
 * @author Christoph Nebendahl
 *
 */
public abstract class Kollision {
	boolean kollision;
	public static int[] koordinaten = new int[2];
	static ArrayList<Gameblock> beruehrpunkte = new ArrayList<Gameblock>();;
	String orientierung;
	public static Gewonnen fenster;

	public static void zielprüfung(Spielfigur player) {
		// TODO Auto-generated method stub
		if (player.getX() + Spielfigur.getBreite() > Main.level.getGoal().getX()) {
			fenster = new Gewonnen();
			fenster.setVisible(true);
			fenster.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
	}


	public static boolean collisionDetected() {
		int t=10;
		return (Main.level.getIntersectingGameblock(new Gameblock(Main.spieler.getX()-t, Main.spieler.getY()+t,
				Main.spieler.getWidth()+t, Main.spieler.getHeight()+t, null, null, null)) != null);
	}
}
