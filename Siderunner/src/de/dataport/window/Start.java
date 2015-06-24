package de.dataport.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import de.dataport.system.Speicher;
import de.dataport.system.Speicher_Enum;
import de.dataport.system.Tastatur;
import de.dataport.usercontrols.RotatingLogo;
import de.dataport.window.tone.Ton;

public class Start extends JFrame {


	private static final long serialVersionUID = 1L;
	public static JPanel panel;
	public static JFrame frame;
	private boolean ton = true;

	private Ton mp3;
	private RotatingLogo logo;

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

		setResizable(false);
		setTitle("Jack Runner");
		setBounds(0, 21, 800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		setUndecorated(true);
		setBackground(Color.WHITE);
		setVisible(true);
		panel = new JPanel();
		panel.setBounds(0, 21, 800, 400);
		panel.setBackground(Color.WHITE);
		// panel.setBounds(Fullscreen.desktopPane.getWidth()/2-panel.getWidth()/2,Fullscreen.desktopPane.getHeight()/2-panel.getHeight()/2,800,400);
		getContentPane().add(panel);
		Tastatur key = new Tastatur(this);
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				requestFocus();
			}

		});
		panel.addMouseMotionListener(key);
		Box horizontalBox = Box.createHorizontalBox();
		horizontalBox.addKeyListener(key);
		horizontalBox.setBackground(Color.white);
		horizontalBox.setPreferredSize(panel.getSize());
		panel.add(horizontalBox);

		Box horizontalBoxLogo = Box.createHorizontalBox();
		horizontalBox.add(horizontalBoxLogo);

		horizontalBoxLogo.addKeyListener(key);
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
		});
		logo.addMouseMotionListener(key);
		horizontalBoxLogo.addKeyListener(key);
		panel.requestFocus();
		horizontalBoxLogo.add(logo);
		Box verticalBoxButtons = Box.createVerticalBox();
		horizontalBox.add(verticalBoxButtons);
		verticalBoxButtons.addKeyListener(key);

		/* Sound */
		String mp3Source = Start.class.getResource("/de/dataport/window/tone/DJ_Cymru_-_Valentines.mp3").getPath();
		mp3 = new Ton(mp3Source);
		if (Speicher.getBoolean(Speicher_Enum.SOUND1)) {
			mp3.play();
		}

		/* Singleplayer-Button */

		/* Multiplayer-Button */

		/* Leveleditor-Button */

		Box horizontalBox_1 = Box.createHorizontalBox();
		horizontalBox_1.setBorder(null);
		verticalBoxButtons.add(horizontalBox_1);
		JButton btnSingleplayer = new JButton("");
		horizontalBox_1.add(btnSingleplayer);
		btnSingleplayer
				.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Singleplayer.png")));
		buttonGeneralSetting(btnSingleplayer);

		Box horizontalBox_2 = Box.createHorizontalBox();
		horizontalBox_2.setAlignmentY(Component.CENTER_ALIGNMENT);
		horizontalBox_2.setBorder(null);
		verticalBoxButtons.add(horizontalBox_2);
		JButton btnMultiplayer = new JButton("");
		horizontalBox_2.add(btnMultiplayer);
		btnMultiplayer.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Multiplayer.png")));
		buttonGeneralSetting(btnMultiplayer);

		Box horizontalBox_3 = Box.createHorizontalBox();
		horizontalBox_3.setBorder(null);
		verticalBoxButtons.add(horizontalBox_3);
		JButton btnLeveleditor = new JButton("");
		horizontalBox_3.add(btnLeveleditor);
		btnLeveleditor.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Leveleditor.png")));
		buttonGeneralSetting(btnLeveleditor);
		btnLeveleditor.addKeyListener(key);

		/* Events */
		btnMultiplayer.addKeyListener(key);
		btnMultiplayer.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Fullscreen.removeAll();
				Fullscreen.callMultiplayer();
			}
		});
		btnSingleplayer.addKeyListener(key);
		btnSingleplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Fullscreen.removeAll();
				Fullscreen.callGame();
			}
		});
		btnLeveleditor.addMouseListener(new MouseAdapter() {
			@Override

			public void mouseClicked(MouseEvent arg0) {
				
				Fullscreen.removeAll();
				Fullscreen.callLeveleditor();

			}
		});

	}

	private void buttonGeneralSetting(JButton button) {
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		button.setOpaque(false);
		button.setContentAreaFilled(false);
		button.setBorderPainted(false);
	}
	public String getName(){
		return logo.getName();
	}
}
