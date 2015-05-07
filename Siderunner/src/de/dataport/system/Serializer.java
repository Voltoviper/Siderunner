package de.dataport.system;

import java.awt.Component;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.dataport.level.Level;

public abstract class Serializer {

	public static void write(Level level, Component thisInstance)
			throws Exception {
		File f = getFileToChoose(thisInstance, JFileChooser.SAVE_DIALOG,
				"Speichern unter...");
		if (f != null) {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(
					new FileOutputStream(f)));
			encoder.writeObject(level);
			encoder.close();
		}
	}

	public static Level read(Component thisInstance) throws Exception {
		File f = getFileToChoose(thisInstance, JFileChooser.OPEN_DIALOG,
				"Öffnen...");
		if (f != null) {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(
					new FileInputStream(f)));
			Level level = (Level) decoder.readObject();
			decoder.close();
			return level;
		}
		return null;
	}

	private static File getFileToChoose(Component openOnInstance,
			int jFileChooserDialog, String text) {
		JFileChooser chooser;
		chooser = new JFileChooser();

		chooser.setDialogType(jFileChooserDialog);
		FileNameExtensionFilter markUpFilter = new FileNameExtensionFilter(
				"Markup: xml", "xml");
		chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
		chooser.setFileFilter(markUpFilter);
		chooser.setDialogTitle(text);
		chooser.setVisible(true);
		switch (jFileChooserDialog) {
		case 0:
			if (chooser.showOpenDialog(openOnInstance) == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();
			};
			break;
		case 1:
			if (chooser.showSaveDialog(openOnInstance) == JFileChooser.APPROVE_OPTION) {
				String pfad = chooser.getSelectedFile().toString();
				File file = new File(pfad);
				if (!markUpFilter.accept(file)) {
					pfad = pfad+".xml";
					file = new File(pfad);
					chooser.setSelectedFile(file);
				}
				return chooser.getSelectedFile();
			};
			break;
		}

		return null;
	}
}
