package de.dataport.usercontrols;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JButton;

import de.dataport.system.Painter;
import de.dataport.system.Serializer;
import de.dataport.window.Game;
import de.dataport.window.Start;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PausePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public PausePanel(String text) {
		initialize(text);
	}
/**
 * 
 * @param Text der als ‹berschrift angezeigt werden soll.
 */
	private void initialize(String Text) {
		setLayout(null);
		setSize(290, 185);

		JLabel lblPause = new JLabel(Text);
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
					Game.continueGame();
					Serializer.write(Game.level, Game.frame);
					Game.pause("The game is paused...");
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
				Game.mainPane.setVisible(false);
				
				Start.panel.setVisible(true);
				Game.mainPane = null;
			}
		});
		btnEndGame.setBounds(149, 89, 134, 41);
		buttonGeneralSetting(btnEndGame);
		add(btnEndGame);

		JButton btnContinue = new JButton("Continue");
		btnContinue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.continueGame();
				Game.canvas.requestFocusInWindow();
			}
		});
		btnContinue.setBounds(149, 133, 134, 41);
		buttonGeneralSetting(btnContinue);
		add(btnContinue);
	}
/**
 * Gleichschaltung der Button, damit alle die gleichen Einstellungen haben.
 * @param button Button, der Angepaﬂt werden soll
 */
	private void buttonGeneralSetting(JButton button) {
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
	}
}