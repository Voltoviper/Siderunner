package de.dataport.berechnungen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

import javax.print.attribute.standard.Media;

import de.dataport.Objekte.Kollision;
import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;
import de.dataport.window.Main;
import de.dataport.window.Start;
import de.dataport.window.tone.Ton;

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
	 * Pr�ft auf einen Tastaturanschlag
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
		case 39: // Rechts
			if (Main.spieler.getX() + Spielfigur.getGeschwindigkeit() <= Main.canvas.getWidth()/2) {
				if (Kollision.collisionDetected() == false) {
					Main.spieler.setX(Main.spieler.getX() + 10);

					if (Kollision.collisionDetected() == true)
						Main.spieler.setX(Main.spieler.getX() - 10);
					while (Kollision.collisionDetected() == false)
						Main.spieler.setY(Main.spieler.getY() + 1);
					Main.spieler.setY(Main.spieler.getY() - 1);
					Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
					Kollision.zielpr�fung(Main.spieler);
				} else {
					Main.level.move(true, Main.canvas);
				}

			}
			break;
		case 37: // Links
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
		case 32: // H�pfen
			// Das H�pfen wird als Thread ausgef�hrt, um zwischen springen und
			// fallen weitere Tastaturanschl�ge zu erkennen.
			huepf = new Thread() {
				public void run() {
					if(!jump){
					jump = true;
					
					int y = 0;
					int speicher = 0;
					int time = 10;
					String mp3Source = Start.class.getResource("/de/dataport/window/tone/jump.mp3").getPath();
					Ton mp3 = new Ton(mp3Source);
			        mp3.play();
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
					jump=false;
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
