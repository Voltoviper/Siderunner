package de.dataport.level;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;

import de.dataport.datastructures.Gameblock;
import de.dataport.window.Main;

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
	}
	
	public void removeBlock(Gameblock gameblock){
		content.remove(gameblock);
	}

	public List<Gameblock> level1() {

		Gameblock rect1 = new Gameblock(200, Main.test.getY() - 40, 60, 40, false, "Peter", Color.BLUE);
		content.add(rect1);
		Gameblock rect2 = new Gameblock(400, Main.test.getY() - 20, 100, 10, false, "Peter", Color.BLUE);
		content.add(rect2);

		return content;
	}

	public List<Gameblock> getListe() {
		return content;
	}

	public void setListe(List<Gameblock> liste) {
		this.content = liste;
	}

	/** Checks if the block is too close to an existing block */  
	public boolean contentTooCloseTo(Gameblock gameblock) {
		Rectangle rec = new Rectangle(gameblock.getX(), gameblock.getY(), gameblock.getWidth(), gameblock.getHeigth());
		for (Gameblock gb : content) {
			if (!(gameblock.hashCode() == gb.hashCode())) {
				Rectangle rgb = new Rectangle(gb.getX(), gb.getY(), gb.getWidth(), gb.getHeigth());
				if (rec.intersects(rgb))
					return true;
			}
		}
		return false;
	}
}
