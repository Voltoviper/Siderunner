package de.dataport.standardcatalog;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import de.dataport.Objekte.Level;
import de.dataport.datastructures.Gameblock;

public abstract class StandardContent{

	public static List<Gameblock> getStandardBlocks(){
		List<Gameblock> lBlocks=new ArrayList<>();
		final Gameblock spawn = new Gameblock(null, null, 10, 10, false, EnumStandardGameblockNames.SPAWN.toString(), Color.MAGENTA);
		final Gameblock goal = new Gameblock(null, null, 10, 10, false, EnumStandardGameblockNames.GOAL.toString(), Color.CYAN);
		//final Gameblock vanilla = new Gameblock(null, null, 30, 30, false, "vanilla", Color.BLUE);
		//final Gameblock eraser = new Gameblock(null, null, 10, 10, false, "Eraser", Color.WHITE);
		final Gameblock vanilla = new Gameblock(null, null, "/de/dataport/window/graphics/vanilla.jpg", false, EnumStandardGameblockNames.VANILLA.toString());
		final Gameblock eraser = new Gameblock(null, null, "/de/dataport/window/graphics/eraser.jpg", false, EnumStandardGameblockNames.ERASER.toString());
		lBlocks.add(spawn);
		lBlocks.add(goal);
		lBlocks.add(vanilla);
		lBlocks.add(eraser);
		return lBlocks;
	}
	
	public static boolean isStandardBlock(Gameblock gb){
		return getStandardBlocks().contains(gb);
	}
	
	public static Level getStandardLevel(){
		return null;
	}
}
