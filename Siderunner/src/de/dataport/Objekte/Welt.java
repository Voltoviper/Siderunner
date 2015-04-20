package de.dataport.Objekte;

import de.dataport.level.Level;

public class Welt {

	String name;
	Rechtecke[] Spielwelt = new Rechtecke[100000];
	Level level;
	public Welt(String name, Level level) {
		super();
		this.name = name;
		this.level = level;
		welt_befuellen(level);
	}
	private void welt_befuellen(Level level2) {
		// TODO Auto-generated method stub
		int i=0;
		for(Rechtecke rect: level.getListe()){
			for(i=0;i<=100000;i++){
				if(Spielwelt[i]==null){
					Spielwelt[i]=rect;
				}
			}
		}
	
	}
	public void welt_bewegen(){
		for(Rechtecke rect: this.Spielwelt){
			rect.setX(rect.getX()-Spielfigur.getGeschwindigkeit());
		}
	}
	
	
}
