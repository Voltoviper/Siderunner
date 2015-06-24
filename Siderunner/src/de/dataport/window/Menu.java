package de.dataport.window;

import java.awt.Frame;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.dataport.network.Game_Link_Server;
import de.dataport.system.Painter;

public class Menu
{
	/**
	 * Beenden aller Hintergrund Aktivit‰ten, um den Frame zu schlieﬂen.
	 * 
	 * @param frame
	 *            der beendet werden soll
	 */
	public static void dispose(Frame frame)
	{

		if (Game.level != null)
		{
			Game.level = null;
		}
		if (Game.painter != null)
		{
			if (Game.painter.isAlive())
			{
				Painter.run = false;
			}
		}
		if (Leveleditor.backgroundPainter != null)
		{
			if (Leveleditor.backgroundPainter.isAlive())
			{
				Painter.run = false;
			}

			Leveleditor.backgroundPainter.interrupt();

		}
		if (Game_Link_Server.registry != null)
		{
			try
			{
				Game_Link_Server.registry.unbind("Game_Link");
			} catch (RemoteException | NotBoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (frame != null)
		{
			Start.panel.setVisible(true);
			if (Game.mainPane != null)
			{
				if (Game.mainPane.isVisible())
				{
					Game.mainPane.setVisible(false);
				}
			}
			if (Multiplayer.panel != null)
			{
				if (Multiplayer.panel.isVisible())
				{
					Multiplayer.panel.setVisible(false);
				}
			}
			if (Leveleditor.panel != null)
			{
				if (Leveleditor.panel.isVisible())
				{
					Leveleditor.panel.setVisible(false);
				}
			}
		}

	}

	/**
	 * Koordiniertes beenden des Programmes.
	 */
	public static void beenden()
	{
		dispose(null);
		System.exit(0);
	}
}