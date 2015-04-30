package de.dataport.berechnungen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import de.dataport.Objekte.Kollision;
import de.dataport.Objekte.Spielfigur;
import de.dataport.system.Statisches;
import de.dataport.window.Main;

public class Bewegung implements KeyListener {

	public static Thread huepf2;
	public static int zwischenspeicher;
	
	public void Bewegung_erkennen() {
		Main.frmJackRunner.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		bewegen((int) e.getKeyCode());
//		System.out.println(e.getKeyCode());
//		System.out.println(e.getKeyText(e.getKeyCode()));
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	void bewegen(int keycode) {
		switch (keycode) {
		case 39:
			// Nach Rechts gehen
			Statisches.Bild_rechts();
			int[] koordinaten1 = new int[2];
			koordinaten1 = Kollision.kollision_rechts(Main.spieler,
					Main.level1);
			if (Main.spieler.getX() + Spielfigur.getBreite() + Spielfigur.getGeschwindigkeit() >= Main.frmJackRunner
					.getWidth()) {
				Main.spielfigur.fillRect(Main.frmJackRunner.getWidth()-Main.spieler.getWidth(),Main.spieler.getY() , Main.spieler.getWidth(), Main.spieler.getHeigth());
//				Main.spielfigur
//						.setBounds(
//								Main.frmJackRunner.getWidth()
//										- Spielfigur.getBreite(),
//								Main.spieler.getY(),
//								Main.spieler.getWidth(),
//								Main.spieler.getHeigth());
			} else {
				Main.spielfigur.fillRect(koordinaten1[0],koordinaten1[1] , Main.spieler.getWidth(), Main.spieler.getHeigth());

			}
			Main.lblNewLabel.setText(Main.spieler.getX() + "");
			Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
			break;
		case 37:
			// Nach Links gehen
			Statisches.Bild_links();
			int[] koordinaten = new int[2];
			if (Main.spieler.getX() - Spielfigur.getGeschwindigkeit() <= 0) {
				Main.spielfigur.fillRect(0,Main.spieler.getY() , Main.spieler.getWidth(), Main.spieler.getHeigth());
			} else {
				
				koordinaten = Kollision.kollision_links(Main.spieler,
						Main.level1);
				Main.spielfigur.fillRect(koordinaten[0],koordinaten[1] , Main.spieler.getWidth(), Main.spieler.getHeigth());
			}
			Main.lblNewLabel.setText(Main.spieler.getX() + "");
			Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
			break;
		case 32:
			
			Thread huepf = new Thread() {
				public void run() {
					Main.spielfigur.fillRect(Main.spieler.getX(),
							Main.spieler.getY() - 50,
							Main.spieler.getWidth(),
							Main.spieler.getHeigth());
					
					Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
				}
			};
			huepf2 = new Thread() {
				public void run() {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					int[] koordinaten2 = new int[2];
					koordinaten2 = Kollision.kollision_unten(Main.spieler,
							Main.level1);
					Main.spielfigur.fillRect(Main.spieler.getX(),
							koordinaten2[1],
							Main.spieler.getWidth(),
							Main.spieler.getHeigth());
					Main.lblNewLabel.setText(Main.spieler.getX() + "");
					Main.lblNewLabel_1.setText(Main.spieler.getY() + "");

				}
			};

			huepf.start();
			Main.lblNewLabel_1.setText(Main.spieler.getY() + "");
			huepf2.start();
			break;
		}
	}
}
