package de.dataport.Objekte;

import java.awt.Canvas;
import java.awt.Rectangle;
import java.io.Serializable;
import java.util.*;

import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.EnumStandardGameblockNames;

/**
 * Die Klasse, die das Level festlegt. Letzlich kommen hier alle Gameobjecte und
 * die SPielfigur zusammen.
 * 
 * @author Christoph Nebendahl
 *
 */
public class Level implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Gameblock> content = new ArrayList<Gameblock>();
	private Gameblock spawn;
	private Gameblock goal;
	private List<Spielfigur> allPlayer = new ArrayList<Spielfigur>();

	public void addPlayer(Spielfigur spielfigur) {
		allPlayer.add(spielfigur);
	}

	public List<Spielfigur> getAllPlayer() {
		return allPlayer;
	}

	public Gameblock getSpawn() {
		return spawn;
	}

	public void setSpawn(Gameblock spawn) {
		this.spawn = spawn;
	}

	public Gameblock getGoal() {
		return goal;
	}

	public void setGoal(Gameblock goal) {
		this.goal = goal;
	}

	public Level() {
		super();
	}

	private void addBlock(Gameblock gameblock) {
		content.add(gameblock);
		Collections.sort(content);
	}

	private void removeBlock(Gameblock gameblock) {
		content.remove(gameblock);
	}

	public void removePauseBlock() {
		Gameblock pause = null;
		for (Gameblock gb : content)
			if (gb.getName().equals(EnumStandardGameblockNames.PAUSE.toString())) {
				pause = gb;
				break;
			}
		content.remove(pause);
	}

	public List<Gameblock> getListe() {
		return content;
	}

	public void setListe(List<Gameblock> liste) {
		this.content = liste;
	}

	/** Überprüft, ob die Gameblöcke sich überschneiden */
	public Gameblock getIntersectingGameblock(Gameblock gameblock) {
		Rectangle rec = getRectangleFromGameblock(gameblock);
		for (Gameblock gb : getListe()) {
			if (!(gameblock.hashCode() == gb.hashCode())) {
				Rectangle rgb = getRectangleFromGameblock(gb);
				if (rec.intersects(rgb))
					return gb;
			}
		}
		return null;
	}

	/** Creates a rectangle from the gameblock */
	private Rectangle getRectangleFromGameblock(Gameblock gameblock) {
		/* 5 Pixel tolerance intern */
		int t = 7;
		return new Rectangle(gameblock.getX() + t, gameblock.getY() + t, gameblock.getWidth() - t,
				gameblock.getHeight() - t);
	}

	/** Verification of the Gameblock-object */
	public void processNewBlock(Gameblock gb) {

		Gameblock intersection = null;

		if (!gb.getName().equals(EnumStandardGameblockNames.PAUSE.toString()))
			intersection = getIntersectingGameblock(gb);

		/* erasing */
		if (gb.getName().equals(EnumStandardGameblockNames.ERASER.toString()) && intersection != null) {
			removeBlock(intersection);

			/* Spawn or goal unlock */
			lockableGoalAndSpawn(intersection, null);
		}

		/* adding */
		if (intersection == null && !gb.getName().equals(EnumStandardGameblockNames.ERASER.toString())) {

			/* Spawn or goal lock */
			lockableGoalAndSpawn(gb, gb);

			if (!getListe().contains(gb))
				addBlock(gb);
		}
	}

	private void lockableGoalAndSpawn(Gameblock gbCheck, Gameblock lock) {
		if (gbCheck.getName().equals(EnumStandardGameblockNames.SPAWN.toString())
				|| gbCheck.getName().equals(EnumStandardGameblockNames.GOAL.toString())) {
			if (gbCheck.getName().equals(EnumStandardGameblockNames.SPAWN.toString()))
				setSpawn(lock);
			if (gbCheck.getName().equals(EnumStandardGameblockNames.GOAL.toString()))
				setGoal(lock);
		}
	}
/**
 * Setzt im Leveleditor alles zurück
 * @param canvas
 */
	public void deleteLevel(Canvas canvas) {
		this.content.clear();
		this.setGoal(null);
		this.setSpawn(null);
	}
	/**
	 * Verschieben des Levels
	 * @param direction Gibt die Richtung an, in die verschoben wird (true=links)
	 * @param canvas Auf dem der Block verschoben wird
	 */
	public void move(boolean direction, Canvas canvas) {
		for (Gameblock gb : getListe())
			if (direction) {
				gb.setX(gb.getX() - Spielfigur.getGeschwindigkeit());
			} else {
				gb.setX(gb.getX() + Spielfigur.getGeschwindigkeit());
			}
	}
}
