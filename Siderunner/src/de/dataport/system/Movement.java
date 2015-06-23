package de.dataport.system;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.dataport.Objekte.Kollision;
import de.dataport.Objekte.Spielfigur;
import de.dataport.window.Game;
import de.dataport.window.Start;
import de.dataport.window.tone.Ton;

public class Movement implements KeyListener {

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

			if (!Game.isPause()) {

				// if (Game.player.getX() + Spielfigur.getGeschwindigkeit() <=
				// Game.canvas.getWidth() / 2)
				// {
				if (Kollision.collisionDetected() == false) {

					if (Game.player.getX() < Game.canvas.getWidth() / 2 - Game.player.getWidth() / 2
							|| Game.level.getListe().get(Game.level.getListe().size() - 1).getX() < Game.canvas
									.getWidth() * 0.75) {
						Game.player.setX(Game.player.getX() + Spielfigur.getGeschwindigkeit());

						if (Kollision.collisionDetected() == true)
							Game.player.setX(Game.player.getX() - Spielfigur.getGeschwindigkeit());
					} else {
						Game.level.move(true, Game.canvas);
						if (Kollision.collisionDetected() == true)
							Game.level.move(false, Game.canvas);
					}
 
					while (Kollision.collisionDetected() == false)
						Game.player.setY(Game.player.getY() + 1);
					Game.player.setY(Game.player.getY() - 1);
					Game.lblNewLabel_1.setText(Game.player.getY() + "");

					// }
					// else
					// {

					// while (Kollision.collisionDetected())
					// Game.player.setY(Game.player.getY() - 1);
					// }

				}
			}
			break;
		case 37: // Links
			if (!Game.isPause())
				if (Kollision.collisionDetected() == false) {
					if (Game.player.getX() < Game.canvas.getWidth() / 2 - Game.player.getWidth() / 2) {
						Game.player.setX(Game.player.getX() - Spielfigur.getGeschwindigkeit());

						if (Kollision.collisionDetected() == true)
							Game.player.setX(Game.player.getX() + Spielfigur.getGeschwindigkeit());
					} else {
						Game.level.move(false, Game.canvas);
						if (Kollision.collisionDetected() == true)
							Game.level.move(true, Game.canvas);
					}
					while (Kollision.collisionDetected() == false)
						Game.player.setY(Game.player.getY() + 1);
					Game.player.setY(Game.player.getY() - 1);
					Game.lblNewLabel_1.setText(Game.player.getY() + "");
				}
			break;
		case 32: // Hüpfen
			// Das Hüpfen wird als Thread ausgeführt, um zwischen springen und
			// fallen weitere Tastaturanschläge zu erkennen.
			if (!Game.isPause()) {
				new Thread(new Runnable() {
					public void run() {
						if (!jump) {
							jump = true;

							int y = 0;
							int speicher = 0;
							int time = 10;
							if (Game.ton.isSelected()) {
								String mp3Source = Start.class.getResource("/de/dataport/window/tone/jump.mp3")
										.getPath();
								Ton mp3 = new Ton(mp3Source);
								mp3.play();
							}
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
									Game.player.setY(Game.player.getY() - (y - speicher));
									speicher = y;
									if (Kollision.collisionDetected()) {
										while (Kollision.collisionDetected()) {
											Game.player.setY(Game.player.getY() - 1);
										}
									}
								}
							}

							y = 0;
							speicher = 0;
							time = 10;
							jump = false;
						}
					}
				}).start();
			}
			break;
		case 27:
			/* Pause-Menu */
			if (Game.isPause())
				Game.continueGame();
			else
				Game.pause();
			break;
		}

	}
}
