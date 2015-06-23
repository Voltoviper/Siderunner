package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.dataport.Objekte.Level;
import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.EnumStandardGameblockNames;
import de.dataport.standardcatalog.StandardContent;
import de.dataport.system.Movement;
import de.dataport.system.Bewegungsanimation;
import de.dataport.system.Painter;
import de.dataport.system.Serializer;
import de.dataport.system.Speicher;
import de.dataport.system.Speicher_Enum;
import de.dataport.usercontrols.PausePanel;

public class Game {

	public static Graphics graphics;
	public static Spielfigur player;
	public static JFrame frame;
	public static JLabel lblNewLabel = new JLabel("New label");
	public static JLabel lblNewLabel_1 = new JLabel("New label");
	public static Info dialog;
	public static BufferedImage myPicture = null;
	public static Level level;
	public static Canvas canvas;
	public static Timer timer;
	public static Painter painter;
	private static Movement movement;
	private static JLayeredPane mainPane;
	private static PausePanel pausePanel;
	public static Bewegungsanimation bewegunganim = new Bewegungsanimation();
	public static JCheckBoxMenuItem ton;
	private static boolean pause = false;

	/**
	 * Create the application.
	 * 
	 * @param string
	 * 
	 * @wbp.parser.constructor
	 */
	public Game() {
		initialize();
		movement = new Movement();
		frame.addKeyListener(movement);
		canvas.addKeyListener(movement);
	}

	/**
	 * Für den Start aus dem Multiplayer/mit einem vorgegebenem Level
	 * 
	 * @param level
	 *            Level für die Initialisierung (Das MenüItem "Level-laden" wird
	 *            durch diesen Konstruktor nicht mehr angezeigt.
	 */
	public Game(Level level) {
		this();
		Game.level = level;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(true);
		frame.setTitle("Jack Runner");
		frame.setBounds(100, 100, 740, 554);
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
		mnDatei.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		menuBar.add(mnDatei);

		JMenuItem mntmSchlieen = new JMenuItem("Schlie\u00DFen");
		mntmSchlieen.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		mntmSchlieen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(frame, "Wollen Sie das Spiel beenden?", "Beenden",
						JOptionPane.YES_NO_OPTION);
				if (i == 0)
					Menu.dispose(frame);
			}
		});

		JMenuItem mntmKoordinatenAnzeigen = new JMenuItem("Koordinaten anzeigen");
		mntmKoordinatenAnzeigen.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
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

		mainPane = new JLayeredPane();
		mainPane.setBounds(0, 0, 725, 494);
		mainPane.setVisible(true);

		frame.add(mainPane);
		JPanel canvasPanel = new JPanel();
		canvasPanel.setBounds(0, 0, 725, 494);
		canvasPanel.setOpaque(true);

		canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(0, 0, 725, 494);

		canvasPanel.add(canvas);
		mainPane.add(canvasPanel, new Integer(0), 0);

		JMenu mnLevel = new JMenu("Level");
		mnLevel.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		menuBar.add(mnLevel);

		ton = new JCheckBoxMenuItem("Ton?");
		ton.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		ton.setSelected(Speicher.getBoolean(Speicher_Enum.SOUND2));

		ton.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if (ton.isSelected()) {
					Speicher.SpeicherBoolean(Speicher_Enum.SOUND2, false);

				} else {

					Speicher.SpeicherBoolean(Speicher_Enum.SOUND2, true);
				}
			}
		});

		if (level == null) {

			JMenuItem mntmLaden = new JMenuItem("laden");
			mntmLaden.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
			mntmLaden.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					try {
						/*
						 * Beendet das Zeichnen des vorherigen Levels (falls
						 * vorhanden).
						 */
						Painter.run = false;

						/*
						 * Laden des Levels & Zuweisen+Starten der Spielmechanik
						 * (Steuerung+Zeichnen)
						 */
						level = Serializer.read(frame);
						if (level != null) {
							initializeGameplay();

						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			});
			mnLevel.add(mntmLaden);
		} else {
			try {
				if (level != null) {
					initializeGameplay();
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		mnLevel.add(ton);

		JMenu menu = new JMenu("?");
		menu.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		menuBar.add(menu);

		JMenuItem mntmber = new JMenuItem("\u00DCber...");
		mntmber.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		mntmber.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog = new Info();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		menu.add(mntmber);
	}

	protected void initializeGameplay() {
		/* Spieler schon vorhanden? --> für Multiplayer wichtige Abfrage */
		if (level.getAllPlayer().size() == 0) {
			player = new Spielfigur(level.getSpawn().getX(), level.getSpawn().getY() - Spielfigur.getHoehe(),
					"/de/dataport/window/graphics/pirat.png");
			level.addPlayer(player);
		} else {
			player = level.getAllPlayer().get(0);
		}
		startthreadbewegung();
		Movement.bewegen(32); // Spieler startet mit einem "Sprung ins Level".

		painter = new Painter(canvas, level);
		painter.start();
	}

	public static boolean isPaused() {
		return pause;
	}

	public static void pause() {
		/* Initialisierung des Pause-Overlays */
		level.processNewBlock(new Gameblock(0, 0, 10000, 10000, null, EnumStandardGameblockNames.PAUSE.toString(),
				new Color(0, 0, 0, 200), false));
		pausePanel = new PausePanel();
		pausePanel.setLocation(frame.getContentPane().getWidth() / 2 - pausePanel.getWidth() / 2, frame
				.getContentPane().getHeight() / 2 - pausePanel.getHeight() / 2);
		pausePanel.setVisible(true);
		mainPane.add(pausePanel, new Integer(1), 1);
		pause = true;
	}

	public static void continueGame() {
		level.removePauseBlock();
		mainPane.remove(pausePanel);
		pause = false;
	}

	private static void startthreadbewegung() {
		// Herausgenommen, da der Thread noch fehlerhaft ist.
		// bewegunganim.start();
	}

	public static boolean isPause() {
		return pause;
	}
}
