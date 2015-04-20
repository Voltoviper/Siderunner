package de.dataport.system;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import de.dataport.window.Main;

public class Statisches {

	public static void Bild_rechts() {
		// TODO Auto-generated method stub
		try {
			URL in = Main.class.getResource("graphics/pirat.png");
			Main.myPicture = ImageIO.read(new File(in.getPath()));
			Main.spielfigur.setIcon(new ImageIcon(Main.myPicture));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void Bild_links() {
		// TODO Auto-generated method stub
		try {
			URL in = Main.class.getResource("graphics/pirat2.png");
			Main.myPicture = ImageIO.read(new File(in.getPath()));
			Main.spielfigur.setIcon(new ImageIcon(Main.myPicture));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	public static void Bild_Fass(JLabel label){
		try {
			URL in = Main.class.getResource("graphics/fass.jpg");
			Main.myPicture = ImageIO.read(new File(in.getPath()));
			label.setIcon(new ImageIcon(Main.myPicture));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
