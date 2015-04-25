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
			koordinaten1 = Kollision.kollision_rechts(Main.spielfigur,
					Main.level1);
			if (Main.spielfigur.getX() + Spielfigur.getBreite() + Spielfigur.getGeschwindigkeit() >= Main.frmJackRunner
					.getWidth()) {
				Main.spielfigur
						.setBounds(
								Main.frmJackRunner.getWidth()
										- Spielfigur.getBreite(),
								Main.spielfigur.getY(),
								Main.spielfigur.getWidth(),
								Main.spielfigur.getHeight());
			} else {
				Main.spielfigur
						.setBounds(koordinaten1[0], koordinaten1[1],
								Main.spielfigur.getWidth(), Main.spielfigur
										.getHeight());
			}
			Main.lblNewLabel.setText(Main.spielfigur.getX() + "");
			Main.lblNewLabel_1.setText(Main.spielfigur.getY() + "");
			break;
		case 37:
			// Nach Links gehen
			Statisches.Bild_links();
			int[] koordinaten = new int[2];
			if (Main.spielfigur.getX() - Spielfigur.getGeschwindigkeit() <= 0) {
				Main.spielfigur
						.setBounds(0, Main.spielfigur.getY(),
								Main.spielfigur.getWidth(),
								Main.spielfigur.getHeight());
			} else {
				
				koordinaten = Kollision.kollision_links(Main.spielfigur,
						Main.level1);
				Main.spielfigur.setBounds(koordinaten[0],
						koordinaten[1], Main.spielfigur.getWidth(),
						Main.spielfigur.getHeight());
			}
			Main.lblNewLabel.setText(Main.spielfigur.getX() + "");
			Main.lblNewLabel_1.setText(Main.spielfigur.getY() + "");
			break;
		case 32:
			
			Thread huepf = new Thread() {
				public void run() {
					Main.spielfigur.setBounds(Main.spielfigur.getX(),
							Main.spielfigur.getY() - 50,
							Main.spielfigur.getWidth(),
							Main.spielfigur.getHeight());
					
					Main.lblNewLabel_1.setText(Main.spielfigur.getY() + "");
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
					koordinaten2 = Kollision.kollision_unten(Main.spielfigur,
							Main.level1);
					
					Main.spielfigur.setBounds(Main.spielfigur.getX(),
							koordinaten2[1],
							Main.spielfigur.getWidth(),
							Main.spielfigur.getHeight());
					Main.lblNewLabel.setText(Main.spielfigur.getX() + "");
					Main.lblNewLabel_1.setText(Main.spielfigur.getY() + "");

				}
			};

			huepf.start();
			Main.lblNewLabel_1.setText(Main.spielfigur.getY() + "");
			huepf2.start();
			break;
		}
	}
}
