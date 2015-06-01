package de.dataport.system;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import de.dataport.Objekte.Spielfigur;
import de.dataport.window.Main;

public class Painter extends Thread
{
	Spielfigur p;
	Image dbImage;
	Graphics dbGraphics;

	public void run()
	{
		while (true)
		{
			update(Main.canvas);
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

	public Painter (Spielfigur player){
		this.p =player;
	}
	
	private void update(Canvas canvas)
	{
		try{
		Graphics g = canvas.getGraphics();
		// TODO Auto-generated method stub
		if (dbImage == null)
		{
			dbImage = Main.canvas.createImage(725, 494);
			dbGraphics = dbImage.getGraphics();
		}
			dbGraphics.setColor(Color.white);
			dbGraphics.fillRect(0, 0, Main.canvas.getSize().width, Main.canvas.getSize().height);
			dbGraphics.setColor(Main.canvas.getForeground());
			paint(dbGraphics);
			g.drawImage(dbImage, 0, 0, canvas);
		}catch(Exception e){
			System.out.println("Fehler beim Update des Bildschirmes");
		}
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.drawImage(p.getImage().getImage(), p.getX(), p.getY(), Main.canvas);
		
	}
}
