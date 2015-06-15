package de.dataport.system;

import javax.swing.ImageIcon;

import de.dataport.window.Singleplayer;
import de.dataport.window.Start;
/**
 * Klasse, um eine Bewegung zu animieren. Derzeit noch fehlerhaft!
 * @author Christoph Nebendahl
 *
 */
public class Bewegungsanimation extends Thread
{
	private ImageIcon[] player;
	int x=0;

	public void run()
	{
		player = new ImageIcon[12];
		player[0] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/1.png"));
		player[1] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/2.png"));
		player[2] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/3.png"));
		player[3] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/4.png"));
		player[4] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/5.png"));
		player[5] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/6.png"));
		player[6] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/7.png"));
		player[7] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/8.png"));
		player[8] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/9.png"));
		player[9] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/10.png"));
		player[10] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/11.png"));
		player[11] = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/player/12.png"));
		while (true)
		{
			try
			{
				wait();
			} catch (InterruptedException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Singleplayer.player.setImage(player[x]);
			x+=1;
		}
	}
}
