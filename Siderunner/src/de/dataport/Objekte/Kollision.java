package de.dataport.Objekte;

import javax.swing.JDialog;

import de.dataport.datastructures.Gameblock;
import de.dataport.window.Gewonnen;
import de.dataport.window.Game;

/**
 * Kollisionsabfragen... Es werden die Listen aus der Klasse Level verwendet. Um
 * einen Kontakt zu überprüfen.
 * 
 * @author Christoph Nebendahl
 *
 */
public abstract class Kollision
{
	public static Gewonnen fenster;

	public static boolean zielprüfung(Level level)
	{
		// TODO Auto-generated method stub
		for (Spielfigur player : level.getAllPlayer())
		{
			if (player.getX() + Spielfigur.getBreite() > Game.level.getGoal().getX())
			{
				fenster = new Gewonnen();
				fenster.setVisible(true);
				fenster.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				 return true;
			}
		}
	return false;
	}

	public static boolean collisionDetected()
	{
		int t = 5;
		return (Game.level.getIntersectingGameblock(new Gameblock(Game.player.getX() - t, Game.player.getY() + t,
				Game.player.getWidth() + t, Game.player.getHeight() + t, null, null, null)) != null);
	}
}
