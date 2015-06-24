package de.dataport.system;

import java.util.prefs.Preferences;

import de.dataport.window.Start;
/**
 * Speichert Variablen in den Preferences (unter Windows in der Registry)
 * @author Christoph Nebendahl
 *
 */
public class Speicher {
	private static Preferences prefs;

	/**
	 * Setzt den Punkt in den Preferences.
	 */
	public static void setNode(){
		try{
		prefs = Preferences.userRoot().node(Start.class.getName());
		}catch(Exception e){
			prefs=null;
		}
	}
	/**
	 * Speichert einen Wert in den preferences
	 * @param s Enum, unter dem gespeichert werden soll.
	 * @param b Boolscher Wert, der gepeichert werden soll
	 */
	public static void SpeicherBoolean(Enum<Speicher_Enum> s, boolean b){
		if(prefs==null){
			setNode();
		}
		if(prefs==null){
		prefs.putBoolean(s.toString(), b);
		System.out.println(s + "gespeichert mit Wert:" + b);
		}else{
			System.out.println("Fehler bei der Speicherung");
		}
	}
	/**
	 * Sucht den Wert aus den User Preferences heraus. 
	 * @param s Enum, nach dem gesucht werden soll
	 * @return Gibt den Wert der Suche zurück. 
	 */
	public static boolean getBoolean(Enum<Speicher_Enum> s){
		boolean bool;
		if(prefs==null){
			setNode();
		}
		if(prefs==null){
			bool =  prefs.getBoolean(s.toString(), true);
		}else{
			bool=false;
		}
		return bool;
	}
}
