package de.dataport.standardcatalog;

public enum EnumStandardGameblockNames {
	SPAWN,
	GOAL,
	VANILLA,
	ERASER,
	PAUSE;
	
	@Override
	public String toString(){
		return name().substring(0,1).toUpperCase() + name().substring(1).toLowerCase();
	}
}
