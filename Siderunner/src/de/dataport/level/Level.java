package de.dataport.level;

import de.dataport.Objekte.Rechtecke;
import de.dataport.window.Main;

public class Level {
		Rechtecke[] liste;

		public Level() {
			super();
			liste = new Rechtecke [100];
		}
		
		
		public Rechtecke[] level1(){
			Rechtecke rect1 = new Rechtecke(200, Main.test.getY()-40, 60, 40);
			liste[0]=rect1;
			Rechtecke rect2 = new Rechtecke(400, Main.test.getY()-20, 100, 10);
			liste[1]=rect2;

			return liste;
		}


		public Rechtecke[] getListe() {
			return liste;
		}


		public void setListe(Rechtecke[] liste) {
			this.liste = liste;
		}
		
}
