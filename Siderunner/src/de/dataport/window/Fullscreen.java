package de.dataport.window;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;

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
	static JMenu mnModus;
	static JMenuBar menuBar;

	private static Start start;
	private static Game game;
	private static Multiplayer multiplayer;
	private static Leveleditor leveleditor;

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

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, desktopPane.getWidth(), 21);
		desktopPane.add(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		mnDatei.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		menuBar.add(mnDatei);

		JMenuItem mntmBeenden = new JMenuItem("Beenden");
		mntmBeenden.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		mntmBeenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu.beenden();
			}
		});
		mnDatei.add(mntmBeenden);

		mnModus = new JMenu("Modus");
		mnModus.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		menuBar.add(mnModus);

		JMenuItem mntmSingelplayer = new JMenuItem("Singelplayer");
		mntmSingelplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callGame();
			}
		});
		mntmSingelplayer.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		mnModus.add(mntmSingelplayer);

		JMenuItem mntmMultiplayer = new JMenuItem("Multiplayer");
		mntmMultiplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callMultiplayer();
			}
		});
		mntmMultiplayer.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		mnModus.add(mntmMultiplayer);

		JMenuItem mntmLeveleditor = new JMenuItem("Leveleditor");
		mntmLeveleditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				callLeveleditor();
			}
		});
		mntmLeveleditor.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		mnModus.add(mntmLeveleditor);

		start.getPanel().setBounds(desktopPane.getWidth() / 2 - start.getPanel().getWidth() / 2,
				desktopPane.getHeight() / 2 - start.getPanel().getHeight() / 2, 800, 400);
		start.getPanel().setBackground(Color.WHITE);
		desktopPane.setVisible(true);
		mnModus.setVisible(true);
	}

	/* Start-Fenster */
	public static void callStart() {
		removeAll();
		mnModus.setVisible(true);
		desktopPane.add(start.getPanel());
	}

	/* Leveleditor-Fenster */
	public static void callLeveleditor() {
		removeAll();
		leveleditor = new Leveleditor();
		leveleditor.getPanel().setBounds(Fullscreen.desktopPane.getWidth() / 2 - leveleditor.getPanel().getWidth() / 2,
				Fullscreen.desktopPane.getHeight() / 2 - leveleditor.getPanel().getHeight() / 2,
				leveleditor.getPanel().getWidth(), leveleditor.getPanel().getHeight());
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
		desktopPane.add(menuBar);
		mnModus.setVisible(false);
	}

}
