package de.dataport.window;

import java.awt.Frame;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import de.dataport.network.Game_Link_Server;
import de.dataport.system.Painter;

public class Menu {

	public static void dispose(Frame frame){
		if (Game.timer != null) {
			if (Game.timer.isRunning()) {
				Game.timer.stop();
			}
		}
		if (Game.p!= null){
			if(Game.p.isAlive()){
				Painter.run = false;
			}
		}
		if(Leveleditor.backgroundPainter!=null){
			if(Leveleditor.backgroundPainter.isAlive()){
				Painter.run = false;
			}
		}
		if(Game_Link_Server.registry!=null){
			try {
				Game_Link_Server.registry.unbind("Game_Link");
			} catch (RemoteException | NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		frame.dispose();
	}
}
