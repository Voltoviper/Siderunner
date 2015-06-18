package de.dataport.usercontrols;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.JButton;

import de.dataport.system.Painter;
import de.dataport.system.Serializer;
import de.dataport.window.Singleplayer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PausePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public PausePanel() {
		initialize();
	}

	private void initialize() {
		setLayout(null);
		setSize(290, 185);

		JLabel lblPause = new JLabel("The game is paused...");
		lblPause.setBounds(20, 5, 236, 29);
		lblPause.setFont(new Font("Tahoma", Font.PLAIN, 24));
		add(lblPause);

		RotatingLogo logo = new RotatingLogo(100, 100, 120, 120);
		logo.setBounds(0, 34, 151, 151);
		logo.setOpaque(false);
		add(logo);

		JButton btnSaveGame = new JButton("Save Game");
		btnSaveGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Singleplayer.continueGame();
					Serializer.write(Singleplayer.level, Singleplayer.frame);
					Singleplayer.pause();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnSaveGame.setBounds(149, 45, 134, 41);
		buttonGeneralSetting(btnSaveGame);
		add(btnSaveGame);

		JButton btnEndGame = new JButton("End Game");
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Painter.run = false;
				Singleplayer.frame.dispose();
			}
		});
		btnEndGame.setBounds(149, 89, 134, 41);
		buttonGeneralSetting(btnEndGame);
		add(btnEndGame);

		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Singleplayer.continueGame();
				Singleplayer.canvas.requestFocusInWindow();
			}
		});
		btnContinue.setBounds(149, 133, 134, 41);
		buttonGeneralSetting(btnContinue);
		add(btnContinue);
	}

	private void buttonGeneralSetting(JButton button) {
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
	}
}
