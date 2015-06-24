package de.dataport.window;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

import de.dataport.Objekte.Level;
import de.dataport.standardcatalog.StandardContent;
import de.dataport.system.Tastatur;

import javax.swing.JDesktopPane;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Fullscreen {
	public static JDesktopPane desktopPane;
	static JFrame frame;

	private static Start start;
	private static Game game;
	private static Multiplayer multiplayer;
	private static Leveleditor leveleditor;
	private static Menu menu;

	public static Leveleditor getLeveleditor() {
		return leveleditor;
	}
	
	public static Menu getMenu() {
		return menu;
	}

	public static Game getGame() {
		return game;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Fullscreen();
					Fullscreen.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Fullscreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit()
				.getScreenSize().height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tastatur key = new Tastatur(frame);
		desktopPane = new JDesktopPane();

		frame.getContentPane().add(desktopPane);
		frame.addKeyListener(key);

		start = new Start();

		desktopPane.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit()
				.getScreenSize().height);
		desktopPane.setBackground(Color.WHITE);
		desktopPane.add(start.getPanel());

		start.getPanel().setBounds(desktopPane.getWidth() / 2 - start.getPanel().getWidth() / 2,
				desktopPane.getHeight() / 2 - start.getPanel().getHeight() / 2, 800, 400);
		start.getPanel().setBackground(Color.WHITE);
		desktopPane.setVisible(true);
		menu = new Menu();
		menu.setStandard();
		menu.setModus();
	}

	/* Start-Fenster */
	public static void callStart() {
		removeAll();
		desktopPane.add(start.getPanel());
	}

	/* Leveleditor-Fenster */
	public static void callLeveleditor() {
		removeAll();
		leveleditor = new Leveleditor();
		leveleditor.getPanel().setBounds(0, 0, leveleditor.getPanel().getWidth(), leveleditor.getPanel().getHeight());
		desktopPane.add(leveleditor.getPanel());

	}

	/* Game-Fenster */
	public static void callGame() {
		removeAll();
		game = new Game();
		game.getPanel().setBounds(Fullscreen.desktopPane.getWidth() / 2 - game.getPanel().getWidth() / 2,
				Fullscreen.desktopPane.getHeight() / 2 - game.getPanel().getHeight() / 2, game.getPanel().getWidth(),
				game.getPanel().getHeight());
		desktopPane.add(game.getPanel());
	}
	/* Game-Fenster */
	public static void callGame(Level level) {
		removeAll();
		game = new Game(level);
		game.getPanel().setBounds(Fullscreen.desktopPane.getWidth() / 2 - game.getPanel().getWidth() / 2,
				Fullscreen.desktopPane.getHeight() / 2 - game.getPanel().getHeight() / 2, game.getPanel().getWidth(),
				game.getPanel().getHeight());
		desktopPane.add(game.getPanel());
	}
	


	/* Multiplayer-Fenster */
	public static void callMultiplayer() {
		removeAll();
		multiplayer = new Multiplayer(start.getName());
		multiplayer.getPanel().setBounds(Fullscreen.desktopPane.getWidth() / 2 - multiplayer.getPanel().getWidth() / 2,
				Fullscreen.desktopPane.getHeight() / 2 - multiplayer.getPanel().getHeight() / 2,
				multiplayer.getPanel().getWidth(), multiplayer.getPanel().getHeight());
		desktopPane.add(multiplayer.getPanel());
	}

	private static void removeAll() {
		desktopPane.removeAll();
		desktopPane.repaint();
		menu.setMenu();

	}

}
