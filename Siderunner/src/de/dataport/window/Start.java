package de.dataport.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import de.dataport.system.Speicher;
import de.dataport.system.Speicher_Enum;
import de.dataport.system.Tastatur;
import de.dataport.usercontrols.RotatingLogo;
import de.dataport.window.tone.Ton;

public class Start {

	private JPanel panel;
	private boolean ton = true;
	Ton mp3;
	Point clickPoint;
	RotatingLogo logo;

	public JPanel getPanel() {
		return panel;
	}

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// new Start();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public Start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() {

		panel = new JPanel();
		panel.setBounds(0, 0, 800, 400);


		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		horizontalBox.setBackground(Color.white);
		horizontalBox.setPreferredSize(new Dimension(800, 400));
		panel.add(horizontalBox);

		Box horizontalBoxLogo = Box.createHorizontalBox();
		horizontalBox.add(horizontalBoxLogo);
		logo = new RotatingLogo();
		logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.requestFocus();
				if (ton) {
					ton = false;
					Speicher.SpeicherBoolean(Speicher_Enum.SOUND1, false);
					mp3.close();
				} else {
					ton = true;
					Speicher.SpeicherBoolean(Speicher_Enum.SOUND1, true);
					mp3.play();
				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				Tastatur.clickPoint = e.getPoint();
			}
		});
		horizontalBoxLogo.add(logo);
		panel.requestFocus();
		Box verticalBoxButtons = Box.createVerticalBox();
		horizontalBox.add(verticalBoxButtons);
		
		Box horizontalBox_1 = Box.createHorizontalBox();
		verticalBoxButtons.add(horizontalBox_1);
		JButton btnSingleplayer = new JButton("");
		horizontalBox_1.add(btnSingleplayer);
		btnSingleplayer
				.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Singleplayer.png")));
		buttonGeneralSetting(btnSingleplayer);
		
		Box horizontalBox_2 = Box.createHorizontalBox();
		verticalBoxButtons.add(horizontalBox_2);
		JButton btnMultiplayer = new JButton("");
		horizontalBox_2.add(btnMultiplayer);
		btnMultiplayer.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Multiplayer.png")));
		buttonGeneralSetting(btnMultiplayer);
		
		Box horizontalBox_3 = Box.createHorizontalBox();
		verticalBoxButtons.add(horizontalBox_3);
		JButton btnLeveleditor = new JButton("");
		horizontalBox_3.add(btnLeveleditor);
		btnLeveleditor.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Leveleditor.png")));
		buttonGeneralSetting(btnLeveleditor);
		btnLeveleditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Fullscreen.callLeveleditor();
				Fullscreen.getMenu().changeMenu(Menu_State.LEVELEDITOR);
			}
		});
		btnMultiplayer.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Fullscreen.callMultiplayer();
			}
		});
		btnSingleplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Fullscreen.callGame();
				Fullscreen.getMenu().changeMenu(Menu_State.SINGLEPLAYER);
			}
		});

		/* Sound */
		String mp3Source = Start.class.getResource("/de/dataport/window/tone/DJ_Cymru_-_Valentines.mp3").getPath();
		mp3 = new Ton(mp3Source);
		if (Speicher.getBoolean(Speicher_Enum.SOUND1)) {
			mp3.play();
		}
	}

	private void buttonGeneralSetting(JButton button) {
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}

	public String getName() {
		return logo.getName();
	}
}