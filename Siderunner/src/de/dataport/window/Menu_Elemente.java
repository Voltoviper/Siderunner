package de.dataport.window;

import java.awt.Frame;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.dataport.network.Game_Link_Server;
import de.dataport.system.Painter;

public class Menu_Elemente {
	/**
	 * Beenden aller Hintergrund Aktivitäten, um den Frame zu schließen.
	 * 
	 * @param frame
	 *            der beendet werden soll
	 */
	public static void dispose(Frame frame) {
		if (Fullscreen.getGame() != null)
			if (Fullscreen.getGame().getLevel() != null) {
				Fullscreen.getGame().setLevel(null);
			}
		if (Game.painter != null) {
			if (Game.painter.isAlive()) {
				Painter.run = false;
			}
		}
		if (Leveleditor.backgroundPainter != null) {
			if (Leveleditor.backgroundPainter.isAlive()) {
				Painter.run = false;
			}

			Leveleditor.backgroundPainter.interrupt();

		}
		if (Game_Link_Server.registry != null) {
			try {
				Game_Link_Server.registry.unbind("Game_Link");
			} catch (RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (frame != null) {
			frame.dispose();
		}

	}

	/**
	 * Koordiniertes beenden des Programmes.
	 */
	public static void beenden() {
		dispose(null);
		System.exit(0);
	}
}