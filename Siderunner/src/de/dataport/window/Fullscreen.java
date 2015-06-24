package de.dataport.window;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;

import de.dataport.system.Tastatur;

import javax.swing.JDesktopPane;

import java.awt.Color;

public class Fullscreen
{
	private static JDesktopPane desktopPane;
	static JFrame frame;
	private static Start start;
	private static Game game;
	private static Multiplayer multiplayer;
	private static Leveleditor leveleditor;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					new Fullscreen();
					Fullscreen.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Fullscreen()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setUndecorated(true);
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Tastatur key=new Tastatur(frame);
		desktopPane = new JDesktopPane();
		
		frame.getContentPane().add(desktopPane);
		frame.addKeyListener(key);
		
		start = new Start();

		
		
		desktopPane.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setVisible(true);
		desktopPane.add(start.getPanel());
		
		start.getPanel().setBounds(desktopPane.getWidth()/2-start.getPanel().getWidth()/2,desktopPane.getHeight()/2-start.getPanel().getHeight()/2,800,400);
		start.getPanel().setBackground(Color.WHITE);
		
	}
	
	/* Start-Fenster */
	public static void callStart(){
		
		desktopPane.add(start.getPanel());	
	}
	
	/* Leveleditor-Fenster */
	public static void callLeveleditor(){
		leveleditor = new Leveleditor();
		leveleditor.getPanel().setBounds(Fullscreen.desktopPane.getWidth() / 2 - leveleditor.getPanel().getWidth() / 2,
				Fullscreen.desktopPane.getHeight() / 2 - leveleditor.getPanel().getHeight() / 2, 800, 600);
		desktopPane.add(leveleditor.getPanel());
		
	}

	/* Game-Fenster */
	public static void callGame(){
		game = new Game();
		game.getPanel().setBounds(Fullscreen.desktopPane.getWidth() / 2 - game.getPanel().getWidth() / 2,
				Fullscreen.desktopPane.getHeight() / 2 - game.getPanel().getHeight() / 2, 740, 554);
		desktopPane.add(game.getPanel());	
	}

	/* Multiplayer-Fenster */
	public static void callMultiplayer(){
		multiplayer = new Multiplayer(start.getName());
		multiplayer.getPanel().setBounds(Fullscreen.desktopPane.getWidth() / 2 - multiplayer.getPanel().getWidth() / 2,
				Fullscreen.desktopPane.getHeight() / 2 - multiplayer.getPanel().getHeight() / 2, 450, 350);
		desktopPane.add(multiplayer.getPanel());	
	}

	public static void removeAll(){
		desktopPane.removeAll();
		desktopPane.repaint();
	}

}
