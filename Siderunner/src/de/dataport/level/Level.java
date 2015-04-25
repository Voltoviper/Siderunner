package de.dataport.level;

import java.awt.Color;
import java.util.*;

import de.dataport.Objekte.Rechtecke;
import de.dataport.datastructures.Gameblock;
import de.dataport.window.Main;

public class Level {
		private List<Gameblock> content = new ArrayList<Gameblock>();

		public Level() {
			super();
		}
		
		public void addBlock(Gameblock block){
			content.add(block);
		}
		
		public List<Gameblock> level1(){
			
			Gameblock rect1 = new Gameblock(200,Main.test.getY()-40,60, 40, false, "Peter", Color.BLUE);
			content.add(rect1);
			Gameblock rect2 = new Gameblock(400,Main.test.getY()-20,100, 10, false, "Peter", Color.BLUE);
			content.add(rect2);

			return content;
		}


		public List<Gameblock> getListe() {
			return content;
		}


		public void setListe(List<Gameblock> liste) {
			this.content = liste;
		}
		
}
