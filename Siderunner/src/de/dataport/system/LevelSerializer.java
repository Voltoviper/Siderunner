package de.dataport.system;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import de.dataport.level.Level;

public class LevelSerializer {

	public static void write(Level level, String filename) throws Exception {
		XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(filename)));
		encoder.writeObject(level);
		encoder.close();
	}

	public static Level read(String filename) throws Exception {
		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filename)));
		Level level = (Level) decoder.readObject();
		decoder.close();
		return level;
	}
}
