package de.dataport.system;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.dataport.Objekte.Kollision;
import de.dataport.Objekte.Spielfigur;
import de.dataport.window.Fullscreen;
import de.dataport.window.Game;
import de.dataport.window.Menu;
import de.dataport.window.Start;
import de.dataport.window.tone.Ton;

/**
 * Hier finden die KeyEvents für das Game.
 * 
 * @author Christoph Nebendahl
 *
 */
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
		bewegen((int) e.getKeyCode());
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

			if (!Fullscreen.getGame().isPause()) {

				// if (Game.player.getX() + Spielfigur.getGeschwindigkeit() <=
				// Game.canvas.getWidth() / 2)
				// {
				if (Kollision.collisionDetected() == false) {

					if (Game.player.getX() < Fullscreen.getGame().getCanvas().getWidth() / 2 - Game.player.getWidth()
							/ 2
							|| Fullscreen.getGame().getLevel().getListe()
									.get(Fullscreen.getGame().getLevel().getListe().size() - 1).getX() < Fullscreen
									.getGame().getCanvas().getWidth() * 0.75) {
						Game.player.setX(Game.player.getX() + Spielfigur.getGeschwindigkeit());

						if (Kollision.collisionDetected() == true)
							Game.player.setX(Game.player.getX() - Spielfigur.getGeschwindigkeit());
					} else {
						Fullscreen.getGame().getLevel().move(true, Fullscreen.getGame().getCanvas());
						if (Kollision.collisionDetected() == true)
							Fullscreen.getGame().getLevel().move(false, Fullscreen.getGame().getCanvas());
					}

					while (Kollision.collisionDetected() == false)
						Game.player.setY(Game.player.getY() + 1);
					Game.player.setY(Game.player.getY() - 1);
				}
			}
			break;
		case 37: // Links
			if (!Fullscreen.getGame().isPause())
				if (Kollision.collisionDetected() == false) {
					if (Game.player.getX() > Fullscreen.getGame().getCanvas().getWidth() / 2 - Game.player.getWidth()
							/ 2
							|| Fullscreen.getGame().getLevel().getListe().get(0).getX() > Fullscreen.getGame()
									.getCanvas().getWidth() * 0.25) {
						Game.player.setX(Game.player.getX() - Spielfigur.getGeschwindigkeit());

						if (Kollision.collisionDetected() == true)
							Game.player.setX(Game.player.getX() + Spielfigur.getGeschwindigkeit());
					} else {
						Fullscreen.getGame().getLevel().move(false, Fullscreen.getGame().getCanvas());
						if (Kollision.collisionDetected() == true)
							Fullscreen.getGame().getLevel().move(true, Fullscreen.getGame().getCanvas());
					}
					while (Kollision.collisionDetected() == false)
						Game.player.setY(Game.player.getY() + 1);
					Game.player.setY(Game.player.getY() - 1);
				}
			break;
		case 32: // Hüpfen
			// Das Hüpfen wird als Thread ausgeführt, um zwischen springen und
			// fallen weitere Tastaturanschläge zu erkennen.
			if (Fullscreen.getGame() != null)
				if (!Fullscreen.getGame().isPause()) {
					new Thread(new Runnable() {
						public void run() {
							if (!jump) {
								jump = true;

								int y = 0;
								int speicher = 0;
								int time = 10;
								if (Menu.getLevel_ton().isSelected()) {
									String mp3Source = Start.class.getResource("/de/dataport/window/tone/jump.mp3")
											.getPath();
									Ton mp3 = new Ton(mp3Source);
									mp3.play();
								}
								while (time >= 0) {

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
			if (Fullscreen.getGame().isPause())
				Fullscreen.getGame().continueGame();
			else
				Fullscreen.getGame().pause();
			break;
		}

	}
}
