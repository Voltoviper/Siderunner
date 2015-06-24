package de.dataport.system;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import de.dataport.window.Menu_Elemente;
/**
 * TastaturListener und MouseMotionAdapter für das Startmenu.
 * @author Christoph Nebendahl
 *
 */
public class Tastatur implements KeyListener {
	public static Point clickPoint;
	public JFrame frame;

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case 27:

			Menu_Elemente.beenden();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	public Tastatur(JFrame frame){
		this.frame=frame;
	}
}
