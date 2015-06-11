package de.dataport.berechnungen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import de.dataport.Objekte.Kollision;
import de.dataport.Objekte.Spielfigur;
import de.dataport.window.Singleplayer;
import de.dataport.window.Start;
import de.dataport.window.tone.Ton;

public class Bewegung implements KeyListener {

	public static Thread huepf;
	public static int zwischenspeicher;
	public static boolean jump = false;

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
		case 39: // Rechts
			if (!Singleplayer.isPaused())
				if (Singleplayer.player.getX() + Spielfigur.getGeschwindigkeit() <= Singleplayer.canvas.getWidth() / 2) {
					if (Kollision.collisionDetected() == false) {
						Singleplayer.player.setX(Singleplayer.player.getX() + 10);

						if (Kollision.collisionDetected() == true)
							Singleplayer.player.setX(Singleplayer.player.getX() - 10);
						while (Kollision.collisionDetected() == false)
							Singleplayer.player.setY(Singleplayer.player.getY() + 1);
						Singleplayer.player.setY(Singleplayer.player.getY() - 1);
						Singleplayer.lblNewLabel_1.setText(Singleplayer.player.getY() + "");
						Kollision.zielprüfung(Singleplayer.player);
					} else {

						Singleplayer.level.move(true, Singleplayer.canvas);
					}

				}
			break;
		case 37: // Links
			if (!Singleplayer.isPaused())
				if (Kollision.collisionDetected() == false) {
					Singleplayer.player.setX(Singleplayer.player.getX() - 10);

					if (Kollision.collisionDetected() == true)
						Singleplayer.player.setX(Singleplayer.player.getX() + 10);
					while (Kollision.collisionDetected() == false)
						Singleplayer.player.setY(Singleplayer.player.getY() + 1);
					Singleplayer.player.setY(Singleplayer.player.getY() - 1);
					Singleplayer.lblNewLabel_1.setText(Singleplayer.player.getY() + "");
				}
			break;
		case 32: // Hüpfen
			// Das Hüpfen wird als Thread ausgeführt, um zwischen springen und
			// fallen weitere Tastaturanschläge zu erkennen.
			if (!Singleplayer.isPaused()) {
				huepf = new Thread() {
					public void run() {
						if (!jump) {
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
									Singleplayer.player.setY(Singleplayer.player.getY() - (y - speicher));
									speicher = y;
								} else {
									while (Kollision.collisionDetected()) {
										Singleplayer.player.setY(Singleplayer.player.getY() - 1);
									}
								}
							}
							y = 0;
							speicher = 0;
							time = 10;
							jump = false;
						}
					}
				};
				Singleplayer.lblNewLabel.setText(Singleplayer.player.getX() + "");
				Singleplayer.lblNewLabel_1.setText(Singleplayer.player.getY() + "");
				huepf.start();
			}
			break;
		case 27:
			/* Pause-Menu */
			if (Singleplayer.isPaused())
				Singleplayer.continueGame();
			else
				Singleplayer.pause();
			break;
		}

	}
}
