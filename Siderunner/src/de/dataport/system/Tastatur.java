package de.dataport.system;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

import de.dataport.window.Menu;

public class Tastatur extends MouseMotionAdapter implements KeyListener {
	public static Point clickPoint;
	public JFrame frame;

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case 27:

			Menu.beenden();
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

	@Override
	public void mouseDragged(MouseEvent e) {
		Point location = frame.getLocation();
		frame.setLocation(location.x + e.getX() - clickPoint.x,
				location.y + e.getY() - clickPoint.y);
	}
	public Tastatur(JFrame frame){
		this.frame=frame;
	}
}
