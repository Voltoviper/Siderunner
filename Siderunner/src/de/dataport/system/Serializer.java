package de.dataport.system;

import java.awt.Component;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.dataport.Objekte.Level;
import de.dataport.datastructures.Gameblock;

/**
 * Import und Export der Level in eine XML Datei.
 * 
 * @author Jan Koch/Christoph Nebendahl
 *
 */
public abstract class Serializer {
	/**
	 * Speichern des Levels mit Hilfe eines Dialoges.
	 * 
	 * @param level
	 *            Ein Level, dessen Gamebloecke gespeichert werden sollen
	 * @param thisInstance
	 * @throws Exception
	 *             Wirft eine Exception, sobald ein Problem beim Speichern
	 *             auftaucht.
	 */
	public static void write(Level level, Component thisInstance) throws Exception {
		File f = getFileToChoose(thisInstance, JFileChooser.SAVE_DIALOG, "Speichern unter...", "Markup: xml",
				new String[] { "xml" });
		if (f != null) {
			optimizePosition(level);
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
			encoder.writeObject(level);
			encoder.close();
		}
	}

	/**
	 * Laden eines Levels mithilfe eines Dialoges.
	 * 
	 * @param thisInstance
	 * @return Gibt keinen Wert zur�ck!!!
	 * @throws Exception
	 *             Wirft eine Exception, wenn beim Zugriff auf die Datei, oder
	 *             beim Einlesen der Datei ein Fehler entsteht.
	 */
	public static Level read(Component thisInstance) throws Exception {
		File f = getFileToChoose(thisInstance, JFileChooser.OPEN_DIALOG, "�ffnen...", "Markup: xml",
				new String[] { "xml" });
		if (f != null) {
			XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
			Level level = (Level) decoder.readObject();
			decoder.close();
			return level;
		}
		return null;
	}

	/**
	 * Liest eine Datei aus einem String Pfad aus.
	 * 
	 * @param path
	 *            Pfad, von dem die Datei eingelesen werden muss.
	 * @return gibt ein Level zur�ck
	 * @throws Exception
	 *             Falls die Datei nicht vorhanden ist.
	 */
	public static Level readfromString(String path) throws Exception {
		File f = new File(path);

		XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
		Level level = (Level) decoder.readObject();
		decoder.close();
		return level;
	}

	/**
	 * Dialog, der einen Pfad f�r Bilder ausw�hlen l�sst.
	 * 
	 * @param openOnInstance
	 *            Frame, auf dem dieser Dialog angezeigt werden soll
	 * @param jFileChooserDialog
	 *            Integer Wert, welcher FileChooser angezeigt werden soll
	 * @param dialogTitle
	 *            Titel des Dialoges
	 * @return Gibt einen String Pfad zu�ck.
	 */
	public static String getImagePath(Component openOnInstance, int jFileChooserDialog, String dialogTitle) {
		File path = getFileToChoose(openOnInstance, jFileChooserDialog, dialogTitle, "Bilddateien",
				ImageIO.getReaderFileSuffixes());
		if (path == null) {
			return null;
		} else {
			return path.getPath();
		}

	}

	/**
	 * Dialog, der einen PFAD f�r XML Dateien ausw�hlen l�sst.
	 * 
	 * @param openOnInstance
	 *            Componente, auf der dieser Dialog angezeigt werden soll
	 * @return gibt einen String zur�ck, der den Pfad enth�lt
	 */
	public static String getStringPath(Component openOnInstance) {
		File path = getFileToChoose(openOnInstance, JFileChooser.OPEN_DIALOG, "�ffnen...", "Markup: xml",
				new String[] { "xml" });
		if (path == null) {
			return null;
		} else {
			return path.getPath();
		}

	}

	/**
	 * Dialog zum Speichern und Laden einer XML Datei.
	 * 
	 * @param openOnInstance
	 *            Auf welcher Instanz dieser Dialog angezeigt werden soll
	 * @param jFileChooserDialog
	 *            Legt fest, ob ein Laden oder Speichern gew�nscht ist, um die
	 *            Button dementsprechend azupassen
	 * @param text
	 *            Legt den Titel des Dialoges fest.
	 * @return Wenn eine Datei geladen werden soll, Wird ein String zur�ck
	 *         gegeben, der den Pfad enth�lt
	 */
	private static File getFileToChoose(Component openOnInstance, int jFileChooserDialog, String text, String markup,
			String[] suffixes) {

		JFileChooser chooser;
		chooser = new JFileChooser();

		chooser.setDialogType(jFileChooserDialog);
		FileNameExtensionFilter markUpFilter = new FileNameExtensionFilter(markup, suffixes);
		chooser.removeChoosableFileFilter(chooser.getAcceptAllFileFilter());
		chooser.setFileFilter(markUpFilter);
		chooser.setDialogTitle(text);
		chooser.setVisible(true);
		switch (jFileChooserDialog) {
		case 0:
			int dialog = chooser.showOpenDialog(openOnInstance);
			if (dialog == JFileChooser.APPROVE_OPTION) {
				return chooser.getSelectedFile();
			} else if (dialog == JFileChooser.CANCEL_OPTION) {
				System.out.println("Abbrechen wurde ausgew�hlt!");
			}

			break;
		case 1:
			int dialog1 = chooser.showSaveDialog(openOnInstance);
			if (dialog1 == JFileChooser.APPROVE_OPTION) {
				String pfad = chooser.getSelectedFile().toString();
				File file = new File(pfad);
				// Falls der Benutzer vergisst die Dateiendung ".xml" anzuh�ngen
				// wird dieses hier abgefangen.
				if (!markUpFilter.accept(file)) {
					pfad = pfad + ".xml";
					file = new File(pfad);
					chooser.setSelectedFile(file);
				}
				return chooser.getSelectedFile();
			} else if (dialog1 == JFileChooser.CANCEL_OPTION) {
				System.out.println("Abbrechen wurde ausgew�hlt!");
			}

			break;
		}

		return null;
	}

	/**
	 * Skaliert die Position der Bl�cke, wenn beim Editor negative x-Positionen
	 * gespeichert wurden. (wegen Sidescrolling)
	 * 
	 * WIRD NUR BEIM SPEICHERN AUFGERUFEN!!!
	 */
	private static void optimizePosition(Level l) {
		if (l.getListe() != null)
			if (l.getListe().size() != 0)
				if (l.getListe().get(0).getX() < 0) {
					int optimizer = l.getListe().get(0).getX() * -1;
					l.getListe().forEach(gb -> gb.setX(gb.getX() + optimizer)); 
				}

	}

	@SuppressWarnings("unchecked")
	public static ArrayList<Gameblock> readBlocks(){
		File f = new File(getStorageFile());
		{
			XMLDecoder decoder = null;
			
			try {
				decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(f)));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Object o = null;
			try{
			o =  decoder.readObject();
			
			}
			catch (Exception ex){
				ex.printStackTrace();
			}
			if(o == null)
				return null;
			ArrayList<Gameblock> blocks = (ArrayList<Gameblock>)o;
			decoder.close();
			return blocks;
		}
	}
	
	public static void writeBlocks(JList<Gameblock> gameblockList) {

		ArrayList<Gameblock> blocks = new ArrayList<Gameblock>();
		for (int i = 4; i < gameblockList.getModel().getSize(); i++) {
			blocks.add(gameblockList.getModel().getElementAt(i));
		}

		File f = null;
		f = new File(getStorageFile());
		if (f.exists())
			f.delete();
		try {
			f.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		XMLEncoder encoder = null;
		try {
			encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(f)));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		encoder.writeObject(blocks);
		encoder.close();
	}
	
	private static String getStorageFile(){
		File currentDirFile = new File(".");
		String helper = currentDirFile.getAbsolutePath();
		return helper.substring(0, helper.length() - 1)
				+ "src\\de\\dataport\\standardcatalog\\storage\\blocks.xml";
	}
}
