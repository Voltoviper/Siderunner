package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.Timer;

import de.dataport.Objekte.Level;
import de.dataport.Objekte.Spielfigur;
import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.EnumStandardGameblockNames;
import de.dataport.system.Bewegung;
import de.dataport.system.Bewegungsanimation;
import de.dataport.system.Painter;
import de.dataport.system.Serializer;
import de.dataport.usercontrols.PausePanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Singleplayer
{

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
	public static Painter p;
	private static Bewegung movement;
	private static PausePanel pausePanel;
	public static Bewegungsanimation bewegunganim = new Bewegungsanimation();
	public static JCheckBoxMenuItem ton;
	private static boolean pause = false;

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.constructor 
	 */
	public Singleplayer()
	{
		initialize();
		movement = new Bewegung();
		frame.addKeyListener(movement);
		canvas.addKeyListener(movement);
	}

	/**
	 * Für den Start aus dem Multiplayer
	 * 
	 * @param path
	 *            Level Pfad für die Initialisierung
	 */
	public Singleplayer(Level level)
	{
		Singleplayer.level = level;
		initialize();
		movement = new Bewegung();
		frame.addKeyListener(movement);
		canvas.addKeyListener(movement);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.setResizable(true);
		frame.setTitle("Jack Runner");
		frame.setBounds(100, 100, 740, 554);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				int i = JOptionPane.showConfirmDialog(frame, "Wollen Sie das Spiel beenden?", "Beenden", JOptionPane.YES_NO_OPTION);
				if (i == 0)
					Menu.dispose(frame);
			}
		});

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnDatei = new JMenu("Datei");
		menuBar.add(mnDatei);

		JMenuItem mntmSchlieen = new JMenuItem("Schlie\u00DFen");
		mntmSchlieen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int i = JOptionPane.showConfirmDialog(frame, "Wollen Sie das Spiel beenden?", "Beenden", JOptionPane.YES_NO_OPTION);
				if (i == 0)
					Menu.dispose(frame);
			}
		});

		JMenuItem mntmKoordinatenAnzeigen = new JMenuItem("Koordinaten anzeigen");
		mntmKoordinatenAnzeigen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
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

		canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(0, 0, 725, 494);
		frame.getContentPane().add(canvas);
		
		JMenu mnLevel = new JMenu("Level");
		menuBar.add(mnLevel);
		
		ton = new JCheckBoxMenuItem("Ton?");
		ton.setSelected(true);
		ton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(ton.isSelected()){
					ton.setSelected(false);
					
				}else{
					ton.setSelected(true);
				}
				
				
			}
		});
		
		if (level==null)
		{
			

			JMenuItem mntmLaden = new JMenuItem("laden");
			mntmLaden.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{

					try
					{
						level = Serializer.read(frame);
						if (level != null)
						{
							player = new Spielfigur(level.getSpawn().getX(), level.getSpawn().getY() - Spielfigur.getHoehe(),
									"/de/dataport/window/graphics/pirat.png");
							level.addPlayer(player);
							startthreadbewegung();
							Bewegung.bewegen(32); // hü-hüpf

							p = new Painter(canvas, level);
							p.start();
						}
					} catch (Exception e)
					{
						e.printStackTrace();
					}

				}
			});
			mnLevel.add(mntmLaden);
		}
		else
		{
			try
			{
				if (level != null)
				{
					player = new Spielfigur(level.getSpawn().getX(), level.getSpawn().getY() - Spielfigur.getHoehe(),
							"/de/dataport/window/graphics/pirat.png");
					level.addPlayer(player);

					startthreadbewegung();
					Bewegung.bewegen(32); // hü-hüpf

					p = new Painter(canvas, level);
					p.start();
				}
			} catch (Exception e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		mnLevel.add(ton);

		JMenu menu = new JMenu("?");
		menuBar.add(menu);

		JMenuItem mntmber = new JMenuItem("\u00DCber...");
		mntmber.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dialog = new Info();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		menu.add(mntmber);
	}

	

	public static boolean isPaused()
	{
		return pause;
	}

	public static void pause()
	{
		level.processNewBlock(new Gameblock(0, 0, 10000, 10000, null, EnumStandardGameblockNames.PAUSE.toString(), new Color(0, 0, 0, 200)));
		pausePanel = new PausePanel();
		pausePanel.setBounds(frame.getContentPane().getWidth() / 2, frame.getContentPane().getHeight() / 2, 100, 100);
		frame.getContentPane().add(pausePanel);
		pause = true;
	}

	public static void continueGame()
	{
		level.removePauseBlock();
		frame.getContentPane().remove(pausePanel);
		pause = false;
	}
	
	private static void startthreadbewegung(){
		//Herausgenommen, da der Thread noch fehlerhaft ist.
		//bewegunganim.start();
	}
}
