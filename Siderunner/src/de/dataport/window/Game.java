package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
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

import de.dataport.Objekte.Level;
import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.EnumStandardGameblockNames;
import de.dataport.standardcatalog.StandardContent;
import de.dataport.system.Movement;
import de.dataport.system.Painter;
import de.dataport.system.Serializer;
import de.dataport.system.Speicher;
import de.dataport.system.Speicher_Enum;
import de.dataport.system.Tastatur;
import de.dataport.usercontrols.PausePanel;

public class Game
{

	public static Graphics graphics;
	public static Spielfigur player;
	public static JFrame frame;
	public static Info dialog;
	public static BufferedImage myPicture = null;
	private Level level;
	private static Canvas canvas;
	public static Painter painter;
	private static Movement movement;
	private static JLayeredPane mainPane;
	private static PausePanel pausePanel;
	private boolean pause = false;
	JPanel canvasPanel;

	public Level getLevel(){
		return level;
	}
	public void setLevel(Level level){
		this.level = level;
	}
	public JLayeredPane getPanel()
	{
		return mainPane;
	}

	public Canvas getCanvas()
	{
		return canvas;
	}

	/**
	 * Create the application.
	 * 
	 * @param string
	 * 
	 * @wbp.parser.constructor
	 */
	public Game(){
		globalInitialize();
	}

	/**
	 * Für den Start aus dem Multiplayer/mit einem vorgegebenem Level
	 * 
	 * @param level
	 *            Level für die Initialisierung (Das MenüItem "Level-laden" wird
	 *            durch diesen Konstruktor nicht mehr angezeigt.
	 */
	public Game(Level level){
		this.level = level;
		globalInitialize();
	}
	
	private void globalInitialize(){
		initialize();
		movement = new Movement();
		Fullscreen.desktopPane.addKeyListener(movement);
		mainPane.addKeyListener(movement);
		canvasPanel.addKeyListener(movement);
		canvas.addKeyListener(movement);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{

		Tastatur tastatur = new Tastatur(null);
		mainPane = new JLayeredPane();
		mainPane.addKeyListener(tastatur);
		mainPane.setBounds(0, 21, Fullscreen.desktopPane.getWidth(), Fullscreen.desktopPane.getHeight());

		mainPane.setVisible(true);
		mainPane.addKeyListener(tastatur);
		canvasPanel = new JPanel();
		canvasPanel.setBounds(0, 21, Fullscreen.desktopPane.getWidth(), Fullscreen.desktopPane.getHeight() - 21);
		canvasPanel.setOpaque(true);

		canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(0, 21, Fullscreen.desktopPane.getWidth(), Fullscreen.desktopPane.getHeight() - 21);

		canvasPanel.add(canvas);
		mainPane.add(canvasPanel, new Integer(0), 0);
		canvas.requestFocusInWindow();

		if(level!=null)
			initializeGameplay();
		else
			Level();
	}

	private void initializeGameplay()
	{
		/* Spieler schon vorhanden? --> für Multiplayer wichtige Abfrage */
		if (level.getAllPlayer().size() == 0)
		{
			player = new Spielfigur(level.getSpawn().getX(), level.getSpawn().getY() - Spielfigur.getHoehe(),
					"/de/dataport/window/graphics/pirat.png");
			level.addPlayer(player);
		}
		else
		{
			player = level.getAllPlayer().get(0);
		}
		Movement.bewegen(32); // Spieler startet mit einem "Sprung ins Level".

		painter = new Painter(canvas, level);
		painter.start();
	}

	public boolean isPaused()
	{
		return pause;
	}

	public void pause(String headline){
		pause();
		pausePanel.setHeadline(headline);
	}

	public void pause()
	{
		/* Initialisierung des Pause-Overlays */
		level.processNewBlock(new Gameblock(0, 0, 10000, 10000, null, EnumStandardGameblockNames.PAUSE.toString(), new Color(0, 0, 0, 200),
				false));
		pausePanel = new PausePanel();
		pausePanel.setLocation(mainPane.getWidth() / 2 - pausePanel.getWidth() / 2, mainPane.getHeight() / 2 - pausePanel.getHeight() / 2);
		pausePanel.setVisible(true);
		mainPane.add(pausePanel, new Integer(1), 1);
		pause = true;
	}

	public void continueGame()
	{
		level.removePauseBlock();
		mainPane.remove(pausePanel);
		pause = false;
	}

	public boolean isPause()
	{
		return pause;
	}

	public void Level()
	{
		try
		{
			level = Serializer.read(mainPane);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (level != null)
		{
			initializeGameplay();
		}
	}
}