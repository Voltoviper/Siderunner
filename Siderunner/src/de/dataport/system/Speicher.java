package de.dataport.system;

import java.util.prefs.Preferences;

import de.dataport.window.Start;

public class Speicher {
	private static Preferences prefs;

	public static void setNode(){
		try{
		prefs = Preferences.userRoot().node(Start.class.getName());
		}catch(Exception e){
			prefs=null;
		}
	}
	
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
