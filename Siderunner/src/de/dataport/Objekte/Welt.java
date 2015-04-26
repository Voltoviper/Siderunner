package de.dataport.Objekte;

import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;

public class Welt {

	String name;
	Level level;
	public Welt(String name, Level level) {
		super();
		this.name = name;
		this.level = level;
	}

	public void welt_bewegen(){
		for(Gameblock rect: this.level.getListe()){ 
			rect.setX(rect.getX()-Spielfigur.getGeschwindigkeit());
		}
	}
	
	
}
