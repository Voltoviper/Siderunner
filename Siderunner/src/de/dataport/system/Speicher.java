package de.dataport.system;

import java.util.prefs.Preferences;

import de.dataport.window.Start;

public class Speicher {
	private static Preferences prefs;

	public static void setNode(){
		prefs = Preferences.userRoot().node(Start.class.getName());
	}
	
	public static void SpeicherBoolean(Enum<Speicher_Enum> s, boolean b){
		if(prefs==null){
			setNode();
		}
		prefs.putBoolean(s.toString(), b);
		System.out.println(s + "gespeichert mit Wert:" + b);
		
	}
	public static boolean getBoolean(Enum<Speicher_Enum> s){
		if(prefs==null){
			setNode();
		}
		return prefs.getBoolean(s.toString(), true);
	}
}
