package de.dataport.system;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;
import de.dataport.window.Main;
/**
 * Malt auf den Main.canvas die Levelelemente und den Spieler.
 * @author Christoph Nebendahl
 *
 */
public class Painter extends Thread
{
	Spielfigur p;
	Image dbImage;
	Graphics dbGraphics;
	private Canvas canvas;
	private Level level;
	public static boolean run=true;
	/**
	 * Run Methode,  die alle 30ms durchgeführt wird
	 */
	public void run()
	{
		while (run)
		{
			update(canvas);
			try
			{
				Thread.sleep(30);
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public Painter (Spielfigur player, Canvas canvas, Level level){
		this.p =player;
		this.canvas = canvas;
		this.level = level;
	}
	/**
	 * Kopiert den Bildschirm und baut den neuen auf. Erst nachdem dieser vollständig aufgebaut ist wird diese angezeigt.
	 * @param canvas Angeben, auf welches Canvas gemalt werden soll.
	 */
	private void update(Canvas canvas)
	{
		try{
		Graphics g = canvas.getGraphics();
		// TODO Auto-generated method stub
		if (dbImage == null)
		{
			dbImage = canvas.createImage(725, 494);
			dbGraphics = dbImage.getGraphics();
		}
			dbGraphics.setColor(Color.white);
			dbGraphics.fillRect(0, 0, canvas.getSize().width, canvas.getSize().height);
			dbGraphics.setColor(canvas.getForeground());
			if(p!=null)paint(dbGraphics);
			paintlevel(dbGraphics);
			g.drawImage(dbImage, 0, 0, canvas);
		}catch(Exception e){
			System.out.println("Fehler beim Update des Bildschirmes");
		}
	}
/**
 * Malt das Level auf das Buffered Graphics Element
 * @param g Bitte gebe die Grafik an, auf die gemalt werden soll
 */
	public void paintlevel(Graphics g){
		for (Gameblock gb : level.getListe()) {
			g.setColor(gb.getColor());
			g.fillRect(gb.getX() - (gb.getWidth() / 2), gb.getY() - (gb.getHeight() / 2), gb.getWidth(), gb.getHeight());
		}
	}
	/**
	 * Malt den Spieler auf das Buffered Graphics Element
	 * @param g Bitte gebe die Grafik an, auf die gemalt werden soll
	 */
	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.drawImage(p.getImage().getImage(), p.getX(), p.getY(), canvas);
		
	}
}
