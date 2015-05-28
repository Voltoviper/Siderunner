package de.dataport.berechnungen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.dataport.Objekte.Kollision;
import de.dataport.Objekte.Spielfigur;
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
 * Nimmt einen KeyCode entgegen, um zu entscheiden, ob nach rechts oder links zu laufen oder zu springen.
 * @param keycode Integer, der den Tastencode enthalten muss.
 */
	public static void bewegen(int keycode) {
		switch (keycode) {
		case 39:
			// Nach Rechts gehen
//			int[] koordinaten1 = new int[2];
//			int[] koordinaten11 = new int[2];
//			koordinaten1 = Kollision.kollision_rechts(Main.spieler, Main.level);
//			if (Main.spieler.getX() + Main.spieler.getWidth()
//					+ Spielfigur.getGeschwindigkeit() >= Main.frmJackRunner
//						.getWidth()) {
//				Main.spieler.setX(Main.frmJackRunner.getWidth()
//						- Main.spieler.getWidth());
//				Main.spieler.setY(Main.spieler.getY());
//			} else {
//				Main.spieler.setX(koordinaten1[0]);
//				Main.spieler.setY(koordinaten1[1]);
//			}
			if(Kollision.collisionDetected() == false)
				Main.spieler.setX(Main.spieler.getX()+20);
			break;
		case 37:
			// Nach Links gehen
//			int[] koordinaten = new int[2];
//			int[] koordinaten01 = new int[2];
//			koordinaten01 = Kollision.kollision_unten(Main.spieler, Main.level);
//			if (Main.spieler.getX() - Spielfigur.getGeschwindigkeit() <= 0) {
//				Main.spieler.setX(0);
//			} else {
//				Main.spieler.setX(Main.spieler.getX()
//						- Spielfigur.getGeschwindigkeit());
//				Main.spieler.setY(koordinaten01[1]);
//				// koordinaten = Kollision.kollision_links(Main.spieler,
//				// Main.level1);
//				// Main.spielfigur.fillRect(koordinaten[0],koordinaten[1] ,
//				// Main.spieler.getWidth(), Main.spieler.getHeigth());
//			}
//			Main.lblNewLabel.setText(Main.spieler.getX() + "");
//			Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
			break;
		case 32:
			 //Das Hüpfen wird als Thread ausgeführt, um zwischen springen und fallen weitere Tastaturanschläge zu erkennen.
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
						if(jump){
							int[] koordinaten2 = new int[2];
							 koordinaten2 = Kollision.kollision_unten(Main.spieler,Main.level);
							 while(Kollision.collisionDetected()==false)
								 Main.spieler.setY(Main.spieler.getY() + 1);
						//Main.spieler.setY(koordinaten2[1]);

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
