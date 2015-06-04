package de.dataport.window;

import java.awt.Frame;

import de.dataport.system.Painter;

public class Menu {

	public static void dispose(Frame frame){
		if (Main.timer != null) {
			if (Main.timer.isRunning()) {
				Main.timer.stop();
			}
		}
		if (Main.p!= null){
			if(Main.p.isAlive()){
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
