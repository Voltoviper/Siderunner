package de.dataport.window;

import java.awt.Frame;

import de.dataport.system.Painter;

public class Menu {

	public static void dispose(Frame frame){
		if (Singleplayer.timer != null) {
			if (Singleplayer.timer.isRunning()) {
				Singleplayer.timer.stop();
			}
		}
		if (Singleplayer.p!= null){
			if(Singleplayer.p.isAlive()){
				Painter.run = false;
			}
		}
		if(Leveleditor.backgroundPainter!=null){
			if(Leveleditor.backgroundPainter.isAlive()){
				Painter.run = false;
			}
		}
		frame.dispose();
	}
}
