package de.dataport.window;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import de.dataport.standardcatalog.StandardContent;
import de.dataport.system.Tastatur;

import javax.swing.JInternalFrame;

import java.awt.BorderLayout;

import javax.swing.JDesktopPane;

import java.awt.Color;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Fullscreen
{
	public static JDesktopPane desktopPane;
	static JFrame frame;
	Start start = null;
	static JMenu mnModus;
	static JMenuBar menuBar;

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
					Fullscreen window = new Fullscreen();
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
		
		try
		{
			start = new Start();
		} catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Start.frame.setVisible(false);
		
		
		desktopPane.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		desktopPane.setBackground(Color.WHITE);
		desktopPane.add(Start.panel);
		
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
				new Game();
				Fullscreen.desktopPane.add(Game.mainPane);
				Game.mainPane.setVisible(true);
				Game.mainPane.setBounds(0,0, Game.mainPane.getWidth(), Game.mainPane.getHeight());
				Start.panel.setVisible(false);
				mnModus.setVisible(false);
			}
		});
		mntmSingelplayer.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		mnModus.add(mntmSingelplayer);
		
		JMenuItem mntmMultiplayer = new JMenuItem("Multiplayer");
		mntmMultiplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Multiplayer(start.getName());
				Fullscreen.desktopPane.add(Multiplayer.panel);
				Multiplayer.panel.setVisible(true);
				Multiplayer.panel.setBounds(Fullscreen.desktopPane.getWidth()/2-Multiplayer.panel.getWidth()/2,Fullscreen.desktopPane.getHeight()/2-Multiplayer.panel.getHeight()/2,450, 350);
				Multiplayer.panel.addKeyListener(key);
				Start.panel.setVisible(false);
				mnModus.setVisible(false);
			}
		});
		mntmMultiplayer.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		mnModus.add(mntmMultiplayer);
		
		JMenuItem mntmLeveleditor = new JMenuItem("Leveleditor");
		mntmLeveleditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Leveleditor();
				Fullscreen.desktopPane.add(Leveleditor.panel);
				Leveleditor.panel.setVisible(true);
				Leveleditor.panel.setBounds(Fullscreen.desktopPane.getWidth()/2-Leveleditor.panel.getWidth()/2,Fullscreen.desktopPane.getHeight()/2-Leveleditor.panel.getHeight()/2, 800,600);
				Start.panel.setVisible(false);
				mnModus.setVisible(false);
			}
		});
		mntmLeveleditor.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		mnModus.add(mntmLeveleditor);
		
		Start.panel.setVisible(true);
		Start.panel.setBounds(desktopPane.getWidth()/2-Start.panel.getWidth()/2,desktopPane.getHeight()/2-Start.panel.getHeight()/2,800,400);
		Start.panel.setBackground(Color.WHITE);
		desktopPane.setVisible(true);
	}


}