package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.dataport.Objekte.Spielfigur;
import de.dataport.berechnungen.Bewegung;
import de.dataport.level.Level;
import de.dataport.system.Painter;
import de.dataport.system.Serializer;

public class Singleplayer {

	public static Graphics graphics;
	public static Spielfigur player;
	public static JFrame frame;
	public static Level level1 = new Level();
	public static JLabel lblNewLabel = new JLabel("New label");
	public static JLabel lblNewLabel_1 = new JLabel("New label");
	public static Info dialog;
	public static BufferedImage myPicture = null;
	public static Level level;
	public static Canvas canvas;
	public static Timer timer;
	public static Painter p;
	private static Bewegung movement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Singleplayer.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Singleplayer() {
		initialize();
		movement = new Bewegung();
		frame.addKeyListener(movement);
		canvas.addKeyListener(movement);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(true);
		frame.setTitle("Jack Runner");
		frame.setBounds(100, 100, 900, 554);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(frame, "Wollen Sie das Spiel beenden?", "Beenden",
						JOptionPane.YES_NO_OPTION);
				if (i == 0)
					Menu.dispose(frame);
			}
		});

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		JMenuItem mntmSchlieen = new JMenuItem("Schlie\u00DFen");
		mntmSchlieen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(frame, "Wollen Sie das Spiel beenden?", "Beenden",
						JOptionPane.YES_NO_OPTION);
				if (i == 0)
					Menu.dispose(frame);
			}
		});

		JMenuItem mntmKoordinatenAnzeigen = new JMenuItem("Koordinaten anzeigen");
		mntmKoordinatenAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel lblX = new JLabel("x:");
				lblX.setBounds(780, 11, 46, 14);
				JLabel lblY = new JLabel("y:");
				lblY.setBounds(780, 29, 46, 14);
				frame.getContentPane().add(lblX);
				frame.getContentPane().add(lblY);
				frame.getContentPane().add(lblNewLabel);
				frame.getContentPane().add(lblNewLabel_1);
				lblNewLabel.setText(player.getX() + "");
				lblNewLabel.setBounds(800, 11, 46, 14);
				lblNewLabel_1.setText(player.getY() + "");
				lblNewLabel_1.setBounds(800, 29, 46, 14);

			}
		});
		mnDatei.add(mntmKoordinatenAnzeigen);

		mnDatei.add(mntmSchlieen);

		JMenu mnLevel = new JMenu("Level");
		menuBar.add(mnLevel);

		canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(0, 0, 725, 494);
		frame.getContentPane().add(canvas);

		JMenuItem mntmLaden = new JMenuItem("laden");
		mntmLaden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					level = Serializer.read(frame);
					if (level != null) {
						player = new Spielfigur(level.getSpawn().getX(), level.getSpawn().getY()
								- Spielfigur.getHoehe(), "/de/dataport/window/graphics/pirat.png");
						level.addPlayer(player);

						Bewegung.bewegen(32); // h�-h�pf

						p = new Painter(canvas, level);
						p.start();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		mnLevel.add(mntmLaden);

		JMenu menu = new JMenu("?");
		menuBar.add(menu);

		JMenuItem mntmber = new JMenuItem("\u00DCber...");
		mntmber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new Info();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		menu.add(mntmber);

		JLabel lblBewegenMitDen = new JLabel("Bewegen mit den Pfeiltasten und springen mit der Leertaste");
		lblBewegenMitDen.setBounds(192, 28, 350, 14);
		frame.getContentPane().add(lblBewegenMitDen);

		// Bewegung bewegung = new Bewegung();
		// bewegung.Bewegung_erkennen();
	}

	public static void pause() {
		// Painter.run=false;
		canvas.setBackground(new Color(100, 100, 100, 50));
		
	}
}
