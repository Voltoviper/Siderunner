package de.dataport.standardcatalog;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;

public abstract class Standard {

	public static List<Gameblock> getStandardBlocks(){
		List<Gameblock> lBlocks=new ArrayList<>();
		final Gameblock spawn = new Gameblock(null, null, 10, 10, false, "Spawn", Color.MAGENTA);
		final Gameblock goal = new Gameblock(null, null, 10, 10, false, "Goal", Color.CYAN);
		final Gameblock vanilla = new Gameblock(null, null, 30, 30, false, "vanilla", Color.BLUE);
		final Gameblock eraser = new Gameblock(null, null, 10, 10, false, "Eraser", Color.WHITE);
		lBlocks.add(spawn);
		lBlocks.add(goal);
		lBlocks.add(vanilla);
		lBlocks.add(eraser);
		return lBlocks;
	}
	
	public static Level getStandardLevel(){
		return null;
	}
}
