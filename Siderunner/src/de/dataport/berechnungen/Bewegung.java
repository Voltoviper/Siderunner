package de.dataport.berechnungen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.dataport.Objekte.Kollision;
import de.dataport.window.Main;

public class Bewegung implements KeyListener {

	public static Thread huepf;
	public static int zwischenspeicher;
	public static boolean jump = false;

	public void Bewegung_erkennen() {
		Main.frmJackRunner.addKeyListener(this);
		Main.canvas.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	/**
	 * Prüft auf einen Tastaturanschlag
	 */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		bewegen((int) e.getKeyCode());
		// System.out.println(e.getKeyCode());
		// System.out.println(e.getKeyText(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Nimmt einen KeyCode entgegen, um zu entscheiden, ob nach rechts oder
	 * links zu laufen oder zu springen.
	 * 
	 * @param keycode
	 *            Integer, der den Tastencode enthalten muss.
	 */
	public static void bewegen(int keycode) {
		
		switch (keycode) {
		case 39:
			if (Kollision.collisionDetected() == false) {
				Main.spieler.setX(Main.spieler.getX() + 10);
				
				if (Kollision.collisionDetected() == true)
					Main.spieler.setX(Main.spieler.getX() - 10);
				while (Kollision.collisionDetected() == false)
					Main.spieler.setY(Main.spieler.getY() + 1);
				Main.spieler.setY(Main.spieler.getY() - 1);
				Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
				Kollision.zielprüfung(Main.spieler);
			}
			break;
		case 37:
			if (Kollision.collisionDetected() == false) {
				Main.spieler.setX(Main.spieler.getX() - 10);

				if (Kollision.collisionDetected() == true)
					Main.spieler.setX(Main.spieler.getX() + 10);
				while (Kollision.collisionDetected() == false)
					Main.spieler.setY(Main.spieler.getY() + 1);
				Main.spieler.setY(Main.spieler.getY() - 1);
				Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
			}
			break;
		case 32:
			// Das Hüpfen wird als Thread ausgeführt, um zwischen springen und
			// fallen weitere Tastaturanschläge zu erkennen.
			huepf = new Thread() {
				public void run() {
					if (!jump) {
						jump = true;
						Main.spieler.setY(Main.spieler.getY() - 50);
						Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (jump) {

							while (Kollision.collisionDetected() == false)
								Main.spieler.setY(Main.spieler.getY() + 1);
							Main.spieler.setY(Main.spieler.getY() - 1);
							Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
							jump = false;
						}
					}
				}
			};
			Main.lblNewLabel.setText(Main.spieler.getX() + "");
			Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
			huepf.start();
			break;
		}

	}

}
