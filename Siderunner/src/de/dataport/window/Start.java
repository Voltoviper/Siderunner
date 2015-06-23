package de.dataport.window;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import de.dataport.system.Speicher;
import de.dataport.system.Speicher_Enum;
import de.dataport.system.Tastatur;
import de.dataport.usercontrols.RotatingLogo;
import de.dataport.window.tone.Ton;

public class Start {

	public static JFrame frame;
	private boolean ton = true;
	Ton mp3;
	Point clickPoint;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */
	public Start() throws IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Jack Runner");
		frame.setBounds(
				Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 400,
				Toolkit.getDefaultToolkit().getScreenSize().height / 2 - 200,
				800, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(
				new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		frame.setUndecorated(true);
		Tastatur key = new Tastatur(frame);
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Tastatur.clickPoint = e.getPoint();
			}

		});
		frame.addMouseMotionListener(key);
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.addKeyListener(key);
		horizontalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(horizontalBox);

		Box horizontalBoxLogo = Box.createHorizontalBox();
		horizontalBox.add(horizontalBoxLogo);
		horizontalBoxLogo.addKeyListener(key);
		RotatingLogo logo = new RotatingLogo();
		logo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
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
			public void mousePressed(MouseEvent e){
				Tastatur.clickPoint = e.getPoint();
			}
		});
		logo.addMouseMotionListener(key);
		horizontalBoxLogo.add(logo);

		Box verticalBoxButtons = Box.createVerticalBox();
		horizontalBox.add(verticalBoxButtons);
		verticalBoxButtons.addKeyListener(key);

		/* Sound */
		String mp3Source = Start.class.getResource(
				"/de/dataport/window/tone/DJ_Cymru_-_Valentines.mp3").getPath();
		mp3 = new Ton(mp3Source);
		if (Speicher.getBoolean(Speicher_Enum.SOUND1)) {
			mp3.play();
		}

		/* Singleplayer-Button */
		JButton btnSingleplayer = new JButton("");
		btnSingleplayer.setIcon(new ImageIcon(Start.class
				.getResource("/de/dataport/window/graphics/Singleplayer.png")));
		buttonGeneralSetting(btnSingleplayer);
		verticalBoxButtons.add(btnSingleplayer);
		btnSingleplayer.addKeyListener(key);

		/* Multiplayer-Button */
		JButton btnMultiplayer = new JButton("");
		btnMultiplayer.setIcon(new ImageIcon(Start.class
				.getResource("/de/dataport/window/graphics/Multiplayer.png")));
		buttonGeneralSetting(btnMultiplayer);
		verticalBoxButtons.add(btnMultiplayer);

		/* Leveleditor-Button */
		JButton btnLeveleditor = new JButton("");
		btnLeveleditor.setIcon(new ImageIcon(Start.class
				.getResource("/de/dataport/window/graphics/Leveleditor.png")));
		buttonGeneralSetting(btnLeveleditor);
		verticalBoxButtons.add(btnLeveleditor);

		/* Events */
		btnSingleplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Game();
				Game.frame.setVisible(true);
			}
		});
		btnMultiplayer.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new Multiplayer();

			}
		});
		btnLeveleditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Leveleditor().getFrame().setVisible(true);
			}
		});
	}

	private void buttonGeneralSetting(JButton button) {
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}
}
