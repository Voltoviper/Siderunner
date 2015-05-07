package de.dataport.level;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;

import de.dataport.datastructures.Gameblock;

public class Level {
	private List<Gameblock> content = new ArrayList<Gameblock>();
	private Gameblock spawn;
	private Gameblock goal;

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

//	public List<Gameblock> level1() {
//
//		Gameblock rect1 = new Gameblock(200, Main.test.getY() - 40, 60, 40, false, "Peter", Color.BLUE);
//		content.add(rect1);
//		Gameblock rect2 = new Gameblock(400, Main.test.getY() - 20, 100, 10, false, "Peter", Color.BLUE);
//		content.add(rect2);
//
//		return content;
//	}

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
		int t = 5;
		return new Rectangle(gameblock.getX() + t, gameblock.getY() + t, gameblock.getWidth() - t,
				gameblock.getHeigth() - t);
	}

	/** repaints the whole level on the specific canvas */
	public void repaintAll(Canvas canvas) {
		Graphics g = canvas.getGraphics();
		g.setColor(canvas.getBackground());
		g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (Gameblock gb : getListe()) {
			g.setColor(gb.getColor());
			g.fillRect(gb.getX() - (gb.getWidth() / 2), gb.getY() - (gb.getHeigth() / 2), gb.getWidth(), gb.getHeigth());
		}
	}

}
