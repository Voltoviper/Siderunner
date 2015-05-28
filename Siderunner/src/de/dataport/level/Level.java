package de.dataport.level;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.*;

import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;
import de.dataport.window.Main;

/**
 * Die Klasse, die das Level festlegt. Letzlich kommen hier alle Gameobjecte und die SPielfigur zusammen. 
 * @author chris_000
 *
 */
public class Level {
	private List<Gameblock> content = new ArrayList<Gameblock>();
	private Gameblock spawn;
	private Gameblock goal;
	private List<Spielfigur> player = new ArrayList<Spielfigur>();

	
	
	public void addPlayer(Spielfigur spielfigur){
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
		for(Spielfigur p:player){
			// ???
			if((Main.spieler.getVorherige_koord()[0]==0)&&(Main.spieler.getVorherige_koord()[1]==0)){
				p.repaintPlayer(canvas);
			}else if (!((Main.spieler.getX().equals(Main.spieler.getVorherige_koord()[0]))||Main.spieler.getY().equals(Main.spieler.getVorherige_koord()[1]))){
				p.repaintPlayer(canvas);
				Main.spieler.setVorherige_koord(Main.spieler.getX(), Main.spieler.getY()); 
			}
			
		}
	}

}
