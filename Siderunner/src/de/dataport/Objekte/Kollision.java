package de.dataport.Objekte;

import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.EnumStandardGameblockNames;

import de.dataport.window.Game;

/**
 * Kollisionsabfragen... Es werden die Listen aus der Klasse Level verwendet. Um
 * einen Kontakt zu überprüfen.
 * 
 * @author Christoph Nebendahl
 *
 */
public abstract class Kollision {
	public static void zielprüfung(Level level) {
		for (Spielfigur player : level.getAllPlayer()) {
			if (player.getX() + Spielfigur.getBreite() > Game.level.getGoal().getX()) {
				Game.pause("You reached the goal");
			}
		}
	}

	public static boolean collisionDetected() {
		int t = 5;
		Gameblock collision = Game.level.getIntersectingGameblock(new Gameblock(Game.player.getX() - t, Game.player
				.getY() + t, Game.player.getWidth() + t, Game.player.getHeight() + t, null, null, null, false));
		if (collision != null) {
			if (collision.getName().equals(EnumStandardGameblockNames.SPAWN.toString()))
				return false;
			if (collision.getName().equals(EnumStandardGameblockNames.GOAL.toString())) {
				zielprüfung(Game.level);   
				return false;
			}
		}
		return (collision != null);
	}
}
