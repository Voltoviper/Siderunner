package de.dataport.level;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;

import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;
import de.dataport.window.Main;

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

	public void addBlock(Gameblock gameblock) {
		content.add(gameblock);
		Collections.sort(content);
	}

	public void removeBlock(Gameblock gameblock) {
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

	/** repaints the whole level on the specific canvas */
	public void repaintLevel(Canvas canvas) {

		Graphics g = canvas.getGraphics();
		g.setColor(canvas.getBackground());
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Gameblock gb : getListe()) {
			g.setColor(gb.getColor());
			g.fillRect(gb.getX() - (gb.getWidth() / 2), gb.getY() - (gb.getHeight() / 2), gb.getWidth(),
					gb.getHeight());
		}
	}

	public void deleteLevel(Canvas canvas) {
		this.content.clear();
		this.setGoal(null);
		this.setSpawn(null);
		repaintLevel(canvas);
	}

	int xPositionAdditionInsideLevel = 0;
// Der der am linksesten ist, ist die blockade
// x+ speicher für alle 
	public void move(boolean direction, Canvas canvas) {
		for (Gameblock gb : getListe())
			if (direction) {
				xPositionAdditionInsideLevel -= Spielfigur.getGeschwindigkeit();
				gb.setX(gb.getX() - Spielfigur.getGeschwindigkeit());
			} else {
				gb.setX(gb.getX() + Spielfigur.getGeschwindigkeit());
			}
		repaintLevel(canvas);
	}

}
