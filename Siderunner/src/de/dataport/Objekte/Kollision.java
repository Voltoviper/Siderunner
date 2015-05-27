package de.dataport.Objekte;

import java.util.ArrayList;

import javax.swing.JDialog;

import de.dataport.berechnungen.Bewegung;
import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;
import de.dataport.window.Gewonnen;
import de.dataport.window.Main;
/**
 * Kollisionsabfragen... Es werden die Listen aus der Klasse Level verwendet. Um einen Kontakt zu überprüfen. 
 * 
 * @author Christoph Nebendahl
 *
 */
public class Kollision {
	boolean kollision;
	public static int[] koordinaten = new int[2];
	static ArrayList<Gameblock> beruehrpunkte = new ArrayList<Gameblock>();;
	String orientierung;
	public static Gewonnen fenster;

	public static int[] kollision_rechts(Spielfigur spielfigur, Level level) {
		int i = 0;
		for (Gameblock rect : level.getListe()) {
			if (rect != null) {
				//Testbereich bitte stehen lassensSs:)
				
				
//			if((Main.spieler.getX()+ Spielfigur.getBreite()+ (2*Spielfigur.getGeschwindigkeit())>rect.getX())&&
//					(Main.spieler.getX()+Spielfigur.getBreite()<rect.getX())
//					){
//				if((Main.spieler.getY()- (2*50)<rect.getY()+rect.getHeigth()&&Main.spieler.getY()>rect.getY())||
//						Main.spieler.getY()+Spielfigur.getHoehe()+(2*50)>rect.getY()&&Main.spieler.getY()+Spielfigur.getHoehe()<rect.getY())
//				{
//					beruehrpunkte.add(rect);
//				}
//			}else if((Main.spieler.getX()-(2*Spielfigur.getGeschwindigkeit())<rect.getX())&&
//					(Main.spieler.getX()>rect.getX())
//					){
//				if((Main.spieler.getY()- (2*50)<rect.getY()+rect.getHeigth()&&Main.spieler.getY()>rect.getY())||
//						Main.spieler.getY()+Spielfigur.getHoehe()+(2*50)>rect.getY()&&Main.spieler.getY()+Spielfigur.getHoehe()<rect.getY())
//				{
//				beruehrpunkte.add(rect);
//				}
//			}
	
				
				if (Main.spieler.getX() + Main.spieler.getWidth()
						+ Spielfigur.getGeschwindigkeit() > rect.getX()
						&& Main.spieler.getX() < rect.getX() + rect.getWidth()) {
					if (Main.spieler.getY() <= rect.getY() - Main.spieler.getHeigth()) {
					//	beruehrpunkte[i] = rect;
						i++;
						koordinaten[0] = Main.spieler.getX()
								+ Spielfigur.getGeschwindigkeit();
						koordinaten[1] = rect.getY() - Main.spieler.getHeigth();
						
						break;
					} else if (Main.spieler.getX() + Main.spieler.getWidth() <= rect.getX()) {
						koordinaten[0] = rect.getX() - Main.spieler.getWidth();
						koordinaten[1] = Main.spieler.getY();
						break;
					}

				} else {
					koordinaten[0] = Main.spieler.getX()
							+ Spielfigur.getGeschwindigkeit();
					//koordinaten[1] = Main.test.getY() - Main.spieler.getHeigth();
				}
			}
		}
		//koordinaten = auswählen(beruehrpunkte, 0);
		zielprüfung(koordinaten);
		return koordinaten;

	}

	private static void zielprüfung(int[] koordinaten2) {
		// TODO Auto-generated method stub
		if(koordinaten2[0]+Spielfigur.getBreite()>Main.level.getGoal().getX()){
			fenster = new Gewonnen();
			fenster.setVisible(true);
			fenster.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		}
	}

	public static int[] kollision_links(Spielfigur figur, Level level) {

		for (Gameblock rect : level.getListe()) {
			if (rect != null) {
				if (figur.getX() - Spielfigur.getGeschwindigkeit() < rect
						.getX() + rect.getWidth()
						&& figur.getX() + Main.spieler.getWidth() > rect.getX()) {
					if (figur.getY() <= rect.getY() - Main.spieler.getHeigth()) {
						koordinaten[0] = Main.spieler.getX()
								- Spielfigur.getGeschwindigkeit();
						koordinaten[1] = rect.getY() - Main.spieler.getHeigth();
						Bewegung.huepf.stop();
						break;
					} else if (figur.getX() > rect.getX()) {
						koordinaten[0] = rect.getX() + rect.getWidth();
						koordinaten[1] = Main.spieler.getY();
						break;
					}
				} else {
					koordinaten[0] = Main.spieler.getX()
							- Spielfigur.getGeschwindigkeit();
					koordinaten[1] = Main.test.getY() - Main.spieler.getHeigth();
				}
			}
		}
		return koordinaten;

	}

	public static int[] kollision_unten(Spielfigur figur, Level level) {

		for (Gameblock rect : level.getListe()) {
			if (rect != null) {
				if (figur.getY() + Main.spieler.getHeigth() + 50 > rect.getY()
						&& (figur.getX() + Main.spieler.getWidth() > rect.getX() && figur
								.getX() < rect.getX() + rect.getWidth())) {
					koordinaten[1] = rect.getY() - Main.spieler.getHeigth()- rect.getHeigth()/2;

				}

			}

		}

		return koordinaten;

	}
	/**
	 * Methode, die den nächsten Block berechnet, der berechnet werden muss.
	 * @param liste	Ein gameblock Array, die geprüft werden müssen
	 * @param Orientierung Integer, der die Orientierung vorgibt.
	 * @return gibt ein Integer Array zurück, der an Position 0 den X-Wert und an Position 1 den Y-Wert für die SPielfigur enthält.
	 */
	private static int[] auswählen(Gameblock[] liste, int Orientierung){
		ArrayList<Gameblock> unten = new ArrayList<Gameblock>();;
		switch(Orientierung){
		case 0: 
			for(Gameblock g:liste){
				if(g.getY()>Main.spieler.getY()+Main.spieler.getHeigth()){
					unten.add(g);
				}
				
				if(g.getX()+g.getWidth()<Main.spieler.getX()){
					
				}
			}
			
			break;
		}
		int y=0;
		Gameblock speicher;
		for (Gameblock g: unten){
			if(g.getX()>y){
				speicher = g;
			}
		}
		
		return null;
	}
}
