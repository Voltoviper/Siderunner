package de.dataport.window;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import de.dataport.system.Tastatur;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.JDesktopPane;
import java.awt.Color;

public class Fullscreen
{
	public static JDesktopPane desktopPane;
	static JFrame frame;

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
					window.frame.setVisible(true);
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
		Start start = null;
		try
		{
			start = new Start();
			start.frame.setVisible(false);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		desktopPane.setBounds(0,0,Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		desktopPane.setBackground(Color.WHITE);
		desktopPane.add(start.panel);
		start.panel.setVisible(true);
		start.panel.setBounds(desktopPane.getWidth()/2-start.panel.getWidth()/2,desktopPane.getHeight()/2-start.panel.getHeight()/2,800,400);
		start.panel.setBackground(Color.WHITE);
		desktopPane.setVisible(true);
	}

}
