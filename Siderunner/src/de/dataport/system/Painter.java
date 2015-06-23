package de.dataport.system;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import de.dataport.Objekte.Level;
import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.EnumStandardGameblockNames;
import de.dataport.window.Start;

/**
 * Malt auf den canvas die Levelelemente und den Spieler.
 * 
 * @author Christoph Nebendahl
 *
 */
public class Painter extends Thread {

	Image dbImage;
	Graphics dbGraphics;
	ImageIcon background;
	private Canvas canvas;
	private Level level;
	public static boolean run = true;

	/**
	 * Run Methode, die alle 30ms durchgeführt wird
	 */

	public void run() {
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			reset();
		}
		while (run) {
			update(canvas);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block

			}
		}
		reset();
	}

	public Painter(Canvas canvas, Level level) {
		reset();
		this.canvas = canvas;
		this.level = level;
		this.background = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/background.png"));
	}

	private void reset() {
		run = true;
		dbImage = null;
		dbGraphics = null;
		background = null;
		canvas = null;
		level = null;
	}

	/**
	 * Kopiert den Bildschirm und baut den neuen auf. Erst nachdem dieser
	 * vollständig aufgebaut ist wird diese angezeigt.
	 * 
	 * @param canvas
	 *            Angeben, auf welches Canvas gemalt werden soll.
	 */
	private void update(Canvas canvas) {
		try {
			Graphics g = canvas.getGraphics();
			// TODO Auto-generated method stub
			if (dbImage == null) {
				dbImage = canvas.createImage(725, 494);
				dbGraphics = dbImage.getGraphics();
			}
			dbGraphics.setColor(Color.white);
			dbGraphics.fillRect(0, 0, canvas.getSize().width, canvas.getSize().height);
			dbGraphics.setColor(canvas.getForeground());
			paintlevel(dbGraphics);
			if (level.getAllPlayer() != null)
				paint(dbGraphics);
			g.drawImage(dbImage, 0, 0, canvas);
		} catch (Exception e) {
			System.out.println("Painter: Fehler beim Update des Bildschirmes");
		}
	}

	/**
	 * Malt das Level auf das Buffered Graphics Element
	 * 
	 * @param g
	 *            Geben Sie das Graphics-Element an, mit dem der Painter
	 *            ausgeführt wird.
	 */
	public void paintlevel(Graphics g) {
		Gameblock pause = null;
		// g.setColor(Color.white);
		g.drawImage(background.getImage(), 0, 0, canvas);
		for (Gameblock gb : level.getListe()) {
			if (gb.getImage() == null) {
				g.setColor(gb.getColor());
				g.fillRect(gb.getX() - (gb.getWidth() / 2), gb.getY() - (gb.getHeight() / 2), gb.getWidth(),
						gb.getHeight());
				/* Pause-Block muss am Ende gemalt werden */
				if (gb.getName().equals(EnumStandardGameblockNames.PAUSE.toString()))
					pause = gb;
			} else
				g.drawImage(gb.getImage().getImage(), gb.getX(), gb.getY(), canvas);
		}
		/* Pause-Block übermalt alles! */
		if (pause != null) {
			g.setColor(pause.getColor());
			g.fillRect(pause.getX() - (pause.getWidth() / 2), pause.getY() - (pause.getHeight() / 2), pause.getWidth(),
					pause.getHeight());
		}

	}

	/**
	 * Malt den Spieler auf das Buffered Graphics Element
	 * 
	 * @param g
	 *            Geben Sie das Graphics-Element an, mit dem der Painter
	 *            ausgeführt wird.
	 */
	public void paint(Graphics g) {
		for (Spielfigur p : level.getAllPlayer()) {
			g.setColor(Color.white);
			g.drawImage(p.getImage().getImage(), p.getX(), p.getY(), canvas);
		}

	}
}
