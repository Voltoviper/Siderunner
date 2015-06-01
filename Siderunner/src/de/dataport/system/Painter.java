package de.dataport.system;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;
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
		Graphics g = canvas.getGraphics();
		// TODO Auto-generated method stub
		if (dbImage == null)
		{
			dbImage = Main.canvas.createImage(Main.canvas.getSize().width, Main.canvas.getSize().height);
			dbGraphics = dbImage.getGraphics();
		}
			dbGraphics.setColor(Color.white);
			dbGraphics.fillRect(0, 0, Main.canvas.getSize().width, Main.canvas.getSize().height);
			dbGraphics.setColor(Main.canvas.getForeground());
			paint(dbGraphics);
			//paintlevel(dbGraphics);
			g.drawImage(dbImage, p.getX(), p.getY(), canvas);
		
	}

	private void paintlevel(Graphics g)
	{
		// TODO Auto-generated method stub
		for (Gameblock gb : Main.level.getListe()) {
			g.setColor(gb.getColor());
			g.fillRect(gb.getX() - (gb.getWidth() / 2), gb.getY() - (gb.getHeight() / 2), gb.getWidth(), gb.getHeight());
		}
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.white);
		g.drawLine(50, 50, 100, 50);
		g.drawImage(p.getImage().getImage(), p.getX(), p.getY(), Main.canvas);
		
	}
}
