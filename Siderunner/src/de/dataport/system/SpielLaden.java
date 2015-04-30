package de.dataport.system;

import java.awt.Color;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;
import de.dataport.system.errorhandler.ProjectErrorHandler;

/**
 * Beispielhafter Reader für Sherd-Diagrammdateien.
 * 
 * Es wird eine Datei "sherd.xml" im Programmverzeichnis geladen und geparst,
 * die Elemente werden ausgegeben.
 * 
 * Die Datei wird gegen das FH-Schema validiert.
 * 
 * @author Wolfgang Knauf
 * 
 */
public class SpielLaden {
	/**
	 * DocumentBuilder für das Einlesen und Parsen von Sherd-Dokumenten, wird im
	 * Konstruktor erzeugt.
	 * 
	 */
	// private DocumentBuilderFactory documentBuilderFactory ;
	private DocumentBuilder documentBuilder;
	public Level level;

	/**
	 * Konstruktor, lädt das Dokument ein.
	 * 
	 * @exception Exception
	 *                Fehler beim Initialisieren des DocumentBuilder
	 */
	public SpielLaden() throws Exception {
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();

		this.documentBuilder = documentBuilderFactory.newDocumentBuilder();

		// ErrorHandler zufügen (der wirft Exceptions bei jedem Piep!)
		// Ohne ihn würde das Parsen nicht abgebrochen, sondern es kämen nur
		// Konsolenausgaben.
		this.documentBuilder.setErrorHandler(new ProjectErrorHandler());
	}

	/**
	 * Parsen der übergebenen Datei und Ausgabe der Informationen.
	 * 
	 * @param sFile
	 *            Aus dieser Datei wird geladen.
	 * @exception Exception
	 *                Fehler beim Parsen
	 */
	public Level parse(String sFile) throws Exception {
		File fileSherd = new File(sFile);
		// Jeder Parsefehler wird vom SherdErrorHandler weitergeworfen.
		level = new Level();
		Document docSherd = this.documentBuilder.parse(fileSherd);

		// Spawn auslesen
		NodeList listlevelSpawn = docSherd.getElementsByTagName("level");
		// Auch hier: auf Verdacht auf Element 0 zugreifen, weil es gemäß Schema
		// genau eines geben muss.
		Element elementlevelSpawn = (Element) listlevelSpawn.item(0);
		// Über alle Childs laufen:
		NodeList listSpawn = elementlevelSpawn.getElementsByTagName("Spawn");
		Element elementSpawn = (Element) listSpawn.item(0);
		NodeList nodeListSpawn = elementSpawn.getElementsByTagName("Gameblock");
		Gameblock[] loadedSpawn = new Gameblock[1];
		for (int intIndex = 0; intIndex < listSpawn.getLength(); intIndex++) {
			Element elementGameblockSpawn = (Element) nodeListSpawn
					.item(intIndex);
			loadedSpawn[intIndex] = new Gameblock(
					Integer.valueOf(elementGameblockSpawn.getAttribute("X")),
					Integer.valueOf(elementGameblockSpawn.getAttribute("Y")),
					Integer.valueOf(elementGameblockSpawn.getAttribute("width")),
					Integer.valueOf(elementGameblockSpawn
							.getAttribute("height")), Boolean
							.valueOf(elementGameblockSpawn
									.getAttribute("isdeadly")),
					elementGameblockSpawn.getAttribute("name"), Color
							.getColor(elementGameblockSpawn.getAttribute("X")));
			System.out.println(loadedSpawn[intIndex].toString());
			level.addBlock(loadedSpawn[intIndex]);
		}

		// Element "Gameobjects" auslesen/holen:
		NodeList listlevel = docSherd.getElementsByTagName("level");
		// Seeehr unsauber: auf Verdacht auf Element 0 zugreifen, weil es gemäß
		// Schema genau eines geben muss.
		// Trotzdem unsauber...
		Element elementGameobject = (Element) listlevel.item(0);
		// Über alle Childs laufen:
		NodeList listGameobject = elementGameobject
				.getElementsByTagName("Gameobject");

		for (int intIndex = 0; intIndex < listGameobject.getLength(); intIndex++) {
			Element elementTabelle = (Element) listGameobject.item(intIndex);
			NodeList nodeListGameblock = elementTabelle
					.getElementsByTagName("Gameblock");
			Gameblock[] loadedGameblocks = new Gameblock[1000];
			for (int intIndexGameblocks = 0; intIndexGameblocks < nodeListGameblock
					.getLength(); intIndexGameblocks++) {

				Element elementBlock = (Element) nodeListGameblock
						.item(intIndexGameblocks);
				loadedGameblocks[intIndexGameblocks] = new Gameblock(
						Integer.valueOf(elementBlock.getAttribute("X")),
						Integer.valueOf(elementBlock.getAttribute("Y")),
						Integer.valueOf(elementBlock.getAttribute("width")),
						Integer.valueOf(elementBlock.getAttribute("height")),
						Boolean.valueOf(elementBlock.getAttribute("isdeadly")),
						elementBlock.getAttribute("name"),
						Color.getColor(elementBlock.getAttribute("X")));
				System.out.println(loadedGameblocks[intIndexGameblocks]
						.toString());
				level.addBlock(loadedGameblocks[intIndexGameblocks]);
			}
		}

		// Jetzt das "Goal" auslesen
		NodeList listlevelGoal = docSherd.getElementsByTagName("level");
		// Auch hier: auf Verdacht auf Element 0 zugreifen, weil es gemäß Schema
		// genau eines geben muss.
		Element elementlevelGoal = (Element) listlevelGoal.item(0);
		// Über alle Childs laufen:
		NodeList listGoal = elementlevelGoal.getElementsByTagName("Goal");
		Element elementComment = (Element) listGoal.item(0);
		NodeList nodeListGoal = elementComment
				.getElementsByTagName("Gameblock");
		Gameblock[] loadedGoal = new Gameblock[1];
		for (int intIndex = 0; intIndex < listGoal.getLength(); intIndex++) {
			Element elementGoal = (Element) nodeListGoal.item(intIndex);
			loadedGoal[intIndex] = new Gameblock(Integer.valueOf(elementGoal
					.getAttribute("X")), Integer.valueOf(elementGoal
					.getAttribute("Y")), Integer.valueOf(elementGoal
					.getAttribute("width")), Integer.valueOf(elementGoal
					.getAttribute("height")), Boolean.valueOf(elementGoal
					.getAttribute("isdeadly")),
					elementGoal.getAttribute("name"),
					Color.getColor(elementGoal.getAttribute("X")));
			System.out.println(loadedGoal[intIndex].toString());
			level.addBlock(loadedGoal[intIndex]);

		}
		return level;

	}

	/**
	 * Main-Methode, liest eine XML Datei im Programmverzeichnis ein und gibt
	 * ihren Inhalt aus.
	 * 
	 * @param args
	 *            Kommandozeile, hier bedeutungslos
	 */
	public static void main(String[] args) {
		try {
			SpielLaden xmlReader = new SpielLaden();

			xmlReader.parse("C:/Users/chris_000/Desktop/level.xml");

			System.out.println("Parsed!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
