package de.dataport.system;

import java.awt.Component;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.dataport.level.Level;

/**
 * Import und Export der Level in eine XML Datei. 
 * @author Jan Koch/Christoph Nebendahl
 *
 */
public abstract class Serializer {
	/**
	 * Speichern des Levels mit Hilfe eines Dialoges.
	 * @param level Ein Level, dessen Gamebloecke gespeichert werden sollen
	 * @param thisInstance
	 * @throws Exception Wirft eine Exception, sobald ein Problem beim Speichern auftaucht.
	 */
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

	/**
	 * Laden eines Levels mithilfe eines Dialoges.
	 * @param thisInstance
	 * @return Gibt keinen Wert zurück!!!
	 * @throws Exception Wirft eine Exception, wenn beim Zugriff auf die Datei, oder beim Einlesen der Datei ein Fehler entsteht.
	 */
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
	/**
	 * Dialog zum Speichern und Laden einer XML Datei. 
	 * @param openOnInstance Auf welcher Instanz dieser Dialog angezeigt werden soll
	 * @param jFileChooserDialog Legt fest, ob ein Laden oder Speichern gewünscht ist, um die Button dementsprechend azupassen
	 * @param text Legt den Titel des Dialoges fest.
	 * @return Wenn eine Datei geladen werden soll, Wird ein String zurück gegeben, der den Pfad enthält
	 */
	public static File getFileToChoose(Component openOnInstance,
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
				//Falls der Benutzer vergisst die Dateiendung ".xml" anzuhängen wird dieses hier abgefangen.
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
