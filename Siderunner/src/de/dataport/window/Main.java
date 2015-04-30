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

import de.dataport.Objekte.Kollision;
import de.dataport.Objekte.Spielfigur;
import de.dataport.berechnungen.Bewegung;
import de.dataport.berechnungen.Boden;
import de.dataport.level.Level;
import de.dataport.system.Serializer;
import de.dataport.system.Statisches;

public class Main{

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
		frmJackRunner.setBounds(100, 100, 741, 554);
		frmJackRunner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJackRunner.getContentPane().setLayout(null);

//		test = new Boden(0, frmJackRunner.getHeight() - 65, frmJackRunner.getWidth(), 10);
//		test.boden_einpflegen();
//		Spielfigur spieler = new Spielfigur(5, test.getY() - Spielfigur.getHoehe());
//		Statisches.Bild_rechts();
//		Kollision.koordinaten[0] = spieler.getX();
//		Kollision.koordinaten[1] = spieler.getY();
		JMenuBar menuBar = new JMenuBar();
		frmJackRunner.setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		JMenuItem mntmSchlieen = new JMenuItem("Schlie\u00DFen");
		mntmSchlieen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem mntmKoordinatenAnzeigen = new JMenuItem("Koordinaten anzeigen");
		mntmKoordinatenAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JLabel lblX = new JLabel("x");
				lblX.setBounds(575, 11, 46, 14);
				JLabel lblY = new JLabel("y");
				lblY.setBounds(575, 29, 46, 14);
				lblNewLabel.setText(spieler.getX() + "");
				lblNewLabel.setBounds(612, 11, 46, 14);
				lblNewLabel_1.setText(spieler.getY() + "");
				lblNewLabel_1.setBounds(612, 29, 46, 14);

				frmJackRunner.getContentPane().add(lblX);
				frmJackRunner.getContentPane().add(lblY);
				frmJackRunner.getContentPane().add(lblNewLabel);
				frmJackRunner.getContentPane().add(lblNewLabel_1);
			}
		});
		mnDatei.add(mntmKoordinatenAnzeigen);

		mnDatei.add(mntmSchlieen);

		JMenu mnLevel = new JMenu("Level");
		menuBar.add(mnLevel);

		canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(5, 10, 710, 479);
		frmJackRunner.getContentPane().add(canvas);

		JMenuItem mntmLaden = new JMenuItem("laden");
		mntmLaden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// SpielLaden xmlReader;
				// try {
				// xmlReader = new SpielLaden();
				// level = (xmlReader
				// .parse(xmlReader.ausw�hlen()));
				// level.repaintAll(canvas);
				// } catch (Exception e) {
				// // TODO Auto-generated catch block
				// e.printStackTrace();
				// }

				try {
					level = Serializer.read(frmJackRunner);
					if (level != null){
						level.repaintAll(canvas);
						spieler = new Spielfigur(level.getSpawn().getX(), level.getSpawn().getY());
						spielfigur = canvas.getGraphics();
						spielfigur.setColor(Color.BLACK);
						spielfigur.fillRect(spieler.getX(), spieler.getY(), spieler.getWidth(), spieler.getHeigth());
						Kollision.koordinaten[0] = spieler.getX();
						Kollision.koordinaten[1] = spieler.getY();
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
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

//		 spielfigur = canvas.getGraphics();
//		 spielfigur.setColor(Color.BLUE);
		// spielfigur.fillRect(spieler.getX() - (spieler.getWidth() / 2),
		// spieler.getY() - (spieler.getHeigth() / 2), spieler.getWidth(),
		// spieler.getHeigth());

		// paintComponent(spielfigur);
		JLabel lblBewegenMitDen = new JLabel("Bewegen mit den Pfeiltasten und springen mit der Leertaste");
		lblBewegenMitDen.setBounds(192, 28, 350, 14);
		frmJackRunner.getContentPane().add(lblBewegenMitDen);

		Bewegung bewegung = new Bewegung();
		bewegung.Bewegung_erkennen();
	}

}
