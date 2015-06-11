package de.dataport.usercontrols;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;

public class PausePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	 public PausePanel(){
		 initialize();
	 }

	private void initialize() {
	 	setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	 	
	 	JLabel lblPause = new JLabel("The game is paused...");
	 	lblPause.setFont(new Font("Tahoma", Font.PLAIN, 24));
	 	add(lblPause);
	}
}
