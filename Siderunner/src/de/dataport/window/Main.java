package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.Timer;

import de.dataport.Objekte.Kollision;
import de.dataport.Objekte.Spielfigur;
import de.dataport.berechnungen.Bewegung;
import de.dataport.berechnungen.Boden;
import de.dataport.level.Level;
import de.dataport.system.Painter;
import de.dataport.system.Serializer;

public class Main {

	public static Graphics spielfigur;
	public static Spielfigur spieler;
	public static Boden test;
	public static JFrame frmJackRunner;
	public static Level level1 = new Level();
	public static JLabel lblNewLabel = new JLabel("New label");
	public static JLabel lblNewLabel_1 = new JLabel("New label");
	public static Info dialog;
	public static BufferedImage myPicture = null;
	public static Level level;
	public static Canvas canvas;
	public static Timer timer;
	public static Painter p;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					Main.frmJackRunner.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJackRunner = new JFrame();
		frmJackRunner.setResizable(true);
		frmJackRunner.setTitle("Jack Runner");
		frmJackRunner.setBounds(100, 100, 900, 554);
		frmJackRunner.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmJackRunner.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frmJackRunner.setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		JMenuItem mntmSchlieen = new JMenuItem("Schlie\u00DFen");
		mntmSchlieen.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if (timer != null) {
					if (timer.isRunning()) {
						timer.stop();
						p.stop();
					}
				}
				System.exit(0);
			}
		});

		JMenuItem mntmKoordinatenAnzeigen = new JMenuItem(
				"Koordinaten anzeigen");
		mntmKoordinatenAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel lblX = new JLabel("x:");
				lblX.setBounds(780, 11, 46, 14);
				JLabel lblY = new JLabel("y:");
				lblY.setBounds(780, 29, 46, 14);
				frmJackRunner.getContentPane().add(lblX);
				frmJackRunner.getContentPane().add(lblY);
				frmJackRunner.getContentPane().add(lblNewLabel);
				frmJackRunner.getContentPane().add(lblNewLabel_1);
				lblNewLabel.setText(spieler.getX() + "");
				lblNewLabel.setBounds(800, 11, 46, 14);
				lblNewLabel_1.setText(spieler.getY() + "");
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
		frmJackRunner.getContentPane().add(canvas);

		JMenuItem mntmLaden = new JMenuItem("laden");
		mntmLaden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					level = Serializer.read(frmJackRunner);
					if (level != null) {
						spieler = new Spielfigur(level.getSpawn().getX(), level
								.getSpawn().getY() - Spielfigur.getHoehe(), "/de/dataport/window/graphics/pirat.png");
						level.addPlayer(spieler);
						Bewegung.bewegen(39);		//hü-hüpf
						timer = new Timer(1, new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								level.repaintLevel(canvas);
							}
						});
						timer.start();
						p = new Painter(spieler);
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
		

		JLabel lblBewegenMitDen = new JLabel(
				"Bewegen mit den Pfeiltasten und springen mit der Leertaste");
		lblBewegenMitDen.setBounds(192, 28, 350, 14);
		frmJackRunner.getContentPane().add(lblBewegenMitDen);

		Bewegung bewegung = new Bewegung();
		bewegung.Bewegung_erkennen();
	}
}
