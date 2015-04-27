package de.dataport.system;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;

/**
 * @author Christoph Nebendahl
 * @version 0.1
 */
public class level_speichern {

	/**
	 * Es wird eine XML Datei erstellt, in der das Level abgespeichert wird.
	 * Hierfür wird das Tutorial von der Seite
	 * http://www.cs.hs-rm.de/~knauf/SWTProjekt2009/xml/ verwendet
	 * 
	 * @author Christoph Nebendahl
	 * @param level
	 *            Übergebe ein Objekt der Klasse Level, um die Gameobjekte
	 *            abzuspeichern.
	 * 
	 */
	public static void speichern(Level level) {
		// TODO Auto-generated method stub
		System.out.println("Speichern...");
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder;
		Document docSherd = null;
		try {
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			docSherd = documentBuilder.newDocument();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Element nodeSherd = docSherd.createElementNS("http://www.wak-sh.de",
				"level");

		nodeSherd.setAttribute("name", "Level1");
		nodeSherd.setAttribute("version", "0.5");
		nodeSherd.setAttribute("schwierigkeit", "EASY");

		// Speichern des SpawnPoints
		if (level.getSpawn() != null) {
			Element elementSpawn1 = docSherd.createElement("Spawn");
			nodeSherd.appendChild(elementSpawn1);
			Element elementSpawn = docSherd.createElement("Gameblock");
			elementSpawn.setAttribute("name", level.getSpawn().getName());
			elementSpawn.setAttribute("X", level.getSpawn().getX() + "");
			elementSpawn.setAttribute("Y", level.getSpawn().getY() + "");
			elementSpawn
					.setAttribute("width", level.getSpawn().getWidth() + "");
			elementSpawn.setAttribute("height", level.getSpawn().getHeigth()
					+ "");
			elementSpawn.setAttribute("isDeadly", level.getSpawn()
					.getIsDeadly() + "");
			elementSpawn
					.setAttribute("color", level.getSpawn().getColor() + "");
			elementSpawn1.appendChild(elementSpawn);
		}

		// Speichern der platzierten Blöcke
		if (level.getListe().size() != 0) {
			Element[] elementGameobjects = new Element[1000];
			Element[] elementGameobject = new Element[1000];
			elementGameobjects[0] = docSherd.createElement("Gameobject");
			nodeSherd.appendChild(elementGameobjects[0]);
			int i = 0;
			for (Gameblock gb : level.getListe()) {
				if (!(gb.getName().equals("Spawn") || gb.getName().equals(
						"Goal"))) {
					elementGameobject[i] = docSherd.createElement("Gameblock");
					elementGameobject[i].setAttribute("name", gb.getName());
					elementGameobject[i].setAttribute("X", gb.getX() + "");
					elementGameobject[i].setAttribute("Y", gb.getY() + "");
					elementGameobject[i].setAttribute("width", gb.getWidth()
							+ "");
					elementGameobject[i].setAttribute("height", gb.getHeigth()
							+ "");
					elementGameobject[i].setAttribute("isDeadly",
							gb.getIsDeadly() + "");
					elementGameobject[i].setAttribute("color", gb.getColor()
							+ "");
					elementGameobjects[0].appendChild(elementGameobject[i]);
					i++;
				}
			}
		}
		// Speichern des GOALPoints
		if (level.getGoal() != null) {
			Element elementGoal1 = docSherd.createElement("Goal");
			nodeSherd.appendChild(elementGoal1);
			Element elementGoal = docSherd.createElement("Gameblock");
			elementGoal.setAttribute("name", level.getGoal().getName());
			elementGoal.setAttribute("X", level.getGoal().getX() + "");
			elementGoal.setAttribute("Y", level.getGoal().getY() + "");
			elementGoal.setAttribute("width", level.getGoal().getWidth() + "");
			elementGoal
					.setAttribute("height", level.getGoal().getHeigth() + "");
			elementGoal.setAttribute("isDeadly", level.getGoal().getIsDeadly()
					+ "");
			elementGoal.setAttribute("color", level.getGoal().getColor() + "");
			elementGoal1.appendChild(elementGoal);
		}

		// SpeicherVorgang mit Konvertierung in XML
		DOMSource domSource = new DOMSource(nodeSherd);
		File fileOutput = new File("level.xml");
		StreamResult streamResult = new StreamResult(fileOutput);
		TransformerFactory tf = TransformerFactory.newInstance();

		Transformer serializer;
		try {
			serializer = tf.newTransformer();
			serializer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			serializer.setOutputProperty(OutputKeys.INDENT, "yes");
			serializer.transform(domSource, streamResult);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Speichern erfolgreich!");
	}

}
