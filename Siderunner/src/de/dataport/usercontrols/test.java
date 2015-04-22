package de.dataport.usercontrols;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class test extends JPanel {

	/**
	 * Create the panel.
	 */
	public test() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 73, 73);
		add(panel);
		
		JLabel lblNewLabel = new JLabel("...Gameblock...");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(93, 11, 252, 73);
		add(lblNewLabel);

	}
}
