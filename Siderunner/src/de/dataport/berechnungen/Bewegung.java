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
		case 39:	// Rechts
			if (Kollision.collisionDetected() == false) {
				Main.spieler.setX(Main.spieler.getX() + 10);

				if (Kollision.collisionDetected() == true)
					Main.spieler.setX(Main.spieler.getX() - 10);
				while (Kollision.collisionDetected() == false)
					Main.spieler.setY(Main.spieler.getY() + 1);
				Main.spieler.setY(Main.spieler.getY() - 1);
				Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
				Kollision.zielprüfung(Main.spieler);
				
				/* Levelbewegung */
				//...
			}
			break;
		case 37:	// Links
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
		case 32:	// Hüpfen
			// Das Hüpfen wird als Thread ausgeführt, um zwischen springen und
			// fallen weitere Tastaturanschläge zu erkennen.
			huepf = new Thread() {
				public void run() {

					jump = true;
					int y = 0;
					int speicher = 0;
					int time = 10;
					while (time >= 0) {
						if (!Kollision.collisionDetected()) {
							y = 2 * (-1 * (time * time) + 10 * time);
							time -= 1;
							try {
								Thread.sleep(18);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							System.out.println(y - speicher);

							Main.spieler.setY(Main.spieler.getY()
									- (y - speicher));
							speicher = y;
						} else {
							while (Kollision.collisionDetected()) {
								Main.spieler.setY(Main.spieler.getY() - 1);
							}
						}
					}
					y = 0;
					speicher = 0;
					time = 10;
				}
			};
			Main.lblNewLabel.setText(Main.spieler.getX() + "");
			Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
			huepf.start();
			break;
		}

	}

}
