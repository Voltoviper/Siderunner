package de.dataport.level;

import java.awt.Canvas;
import java.awt.Rectangle;
import java.util.*;

import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;

/**
 * Die Klasse, die das Level festlegt. Letzlich kommen hier alle Gameobjecte und
 * die SPielfigur zusammen.
 * 
 * @author chris_000
 *
 */
public class Level {
	private List<Gameblock> content = new ArrayList<Gameblock>();
	private Gameblock spawn;
	private Gameblock goal;
	private List<Spielfigur> player = new ArrayList<Spielfigur>();

	public void addPlayer(Spielfigur spielfigur) {
		player.add(spielfigur);
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

	public List<Gameblock> getListe() {
		return content;
	}

	public void setListe(List<Gameblock> liste) {
		this.content = liste;
	}

	/** Checks for intersection of one gameblock to all level content */
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
	public void verification(Gameblock gb) {

		Gameblock intersection = getIntersectingGameblock(gb);

		/* erasing */
		if (gb.getName().equals("Eraser") && intersection != null) {
			removeBlock(intersection);

			/* Spawn or goal unlock */
			lockableGoalAndSpawn(intersection, null);
		}

		/* adding */
		if (intersection == null && !gb.getName().equals("Eraser")) {

			/* Spawn or goal lock */
			lockableGoalAndSpawn(gb, gb);
			
			if (!getListe().contains(gb))
				addBlock(gb);
		}
	}

	private void lockableGoalAndSpawn(Gameblock gbCheck, Gameblock lock) {
		if (gbCheck.getName().equals("Spawn") || gbCheck.getName().equals("Goal")) {
			if (gbCheck.getName().equals("Spawn"))
				setSpawn(lock);
			if (gbCheck.getName().equals("Goal"))
				setGoal(lock);
		}
	}

	public void deleteLevel(Canvas canvas) {
		this.content.clear();
		this.setGoal(null);
		this.setSpawn(null);
	}

	public void move(boolean direction, Canvas canvas) {
		for (Gameblock gb : getListe())
			if (direction) {
				gb.setX(gb.getX() - Spielfigur.getGeschwindigkeit());
			} else {
				gb.setX(gb.getX() + Spielfigur.getGeschwindigkeit());
			}
	}
}
