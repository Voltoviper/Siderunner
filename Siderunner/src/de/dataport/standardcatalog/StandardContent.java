package de.dataport.standardcatalog;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.FontPosture;
import de.dataport.Objekte.Level;
import de.dataport.datastructures.Gameblock;
import de.dataport.window.Game;

public abstract class StandardContent{

	public static List<Gameblock> getStandardBlocks(){
		List<Gameblock> lBlocks=new ArrayList<>();
		final Gameblock spawn = new Gameblock(null, null, 10, 10, false, EnumStandardGameblockNames.SPAWN.toString(), Color.MAGENTA, false);
		final Gameblock goal = new Gameblock(null, null, 10, 10, false, EnumStandardGameblockNames.GOAL.toString(), Color.CYAN, false);
		//final Gameblock vanilla = new Gameblock(null, null, 30, 30, false, "vanilla", Color.BLUE);
		//final Gameblock eraser = new Gameblock(null, null, 10, 10, false, "Eraser", Color.WHITE);
		final Gameblock vanilla = new Gameblock(null, null, "/de/dataport/window/graphics/vanilla.jpg", false, EnumStandardGameblockNames.VANILLA.toString(), false);
		final Gameblock eraser = new Gameblock(null, null, "/de/dataport/window/graphics/eraser.jpg", false, EnumStandardGameblockNames.ERASER.toString(), false);
		lBlocks.add(spawn);
		lBlocks.add(goal);
		lBlocks.add(vanilla);
		lBlocks.add(eraser);
		return lBlocks;
	}
	
	public static boolean isStandardBlock(Gameblock gb){
		return getStandardBlocks().contains(gb);
	}
	
	public static Font neuropolFont(int fontStyle, float fontSize){
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, StandardContent.class.getResourceAsStream("/de/dataport/window/font/NEUROPOL.TTF"));
		} catch (FontFormatException | IOException ex) {
			ex.printStackTrace();
		}
		font = font.deriveFont(fontStyle, fontSize);
		return font;
	}
	
	public static Level getStandardLevel(){
		return null;
	}
}
