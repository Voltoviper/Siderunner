package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import de.dataport.Objekte.Level;
import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.EnumStandardGameblockNames;
import de.dataport.standardcatalog.StandardContent;
import de.dataport.system.Painter;
import de.dataport.system.Serializer;
import de.dataport.usercontrols.GameblockListElement;
import de.dataport.usercontrols.PopUpClickListener;
import de.dataport.usercontrols.PopUpMenuGameblock;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JPanel;

/**
 * 
 * @author Jan Koch
 *
 */
public class Leveleditor {

	private Boolean isMouseDown = false; // Indicator for the Mouse-Move Event
											// to keep painting

	private JList<Gameblock> gameblockList;
	private Canvas canvas;
	private JFrame frame;
	private JPanel panel;
	private JMenuBar menuBar;
	private JScrollPane jspGameblocks;
	public static Painter backgroundPainter;

	public JFrame getFrame() {
		return frame;
	}

	private Level level;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Leveleditor window = new Leveleditor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Leveleditor() {
		initialize();
		fillList();
		level = new Level();
		backgroundPainter = new Painter(canvas, level);
		backgroundPainter.start();
	}

	/** Instantiating and filling the Gameblock-Jlist */
	private void fillList() {

		canvas.setBackground(Color.WHITE);

		/* Gameblock-List */
		gameblockList = new JList<Gameblock>(createDefaultBlockCatalog());
		gameblockList.setCellRenderer(new GameblockListElement());
		jspGameblocks = new JScrollPane(gameblockList);
		jspGameblocks.setBounds(10, 28, 186, 251);

		/* ContextMenu for adding, editing & deleting Gameblocks from the list */
		gameblockList.addMouseListener(new PopUpClickListener() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					StartPopUp(e, gameblockList);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					StartPopUp(e, gameblockList);

				}
			}

			private void StartPopUp(MouseEvent e, JList<Gameblock> gameblockList) {
				PopUpMenuGameblock menu = new PopUpMenuGameblock(gameblockList);
				menu.show(e.getComponent(), e.getX(), e.getY());
			}
		});

		panel.add(jspGameblocks);
	}

	/** Defaultcatalog for the Gameblock-Jlist -> Spawn, Goal, Vanilla(normal) */
	private ListModel<Gameblock> createDefaultBlockCatalog() {
		DefaultListModel<Gameblock> listModel = new DefaultListModel<>();
		for (Gameblock gb : StandardContent.getStandardBlocks())
			listModel.addElement(gb);
		return listModel;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		/* JFrame */
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Leveleditor");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(frame,
						"Wollen Sie den Editor beenden?", "Beenden",
						JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					Painter.run = false;
					Menu.dispose(frame);
				}
			}
		});
		frame.getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		canvas = new Canvas();
		canvas.setBounds(208, 20, 582, 501);
		panel.add(canvas);

		/* MenuBar */
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 794, 21);
		panel.add(menuBar);

		/* Level-Menu */
		JMenu jmLevel = new JMenu("Level");
		menuBar.add(jmLevel);

		JMenuItem jmiNewLevel = new JMenuItem("New...");
		jmiNewLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				level.deleteLevel(canvas);
			}
		});
		jmLevel.add(jmiNewLevel);

		JMenuItem jmiLoadLevel = new JMenuItem("Load...");
		jmLevel.add(jmiLoadLevel);

		JMenuItem jmiSave = new JMenuItem("Save...");
		jmiSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Speichern_unter speichern = new Speichern_unter();
				// speichern.saveAs(null, level);
				try {
					Serializer.write(level, frame);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		jmLevel.add(jmiSave);

		/* Block-Menu */
		JMenu jmBlocks = new JMenu("Blocks");
		menuBar.add(jmBlocks);
		JMenuItem jmiSaveBlocks = new JMenuItem("Save...");
		jmBlocks.add(jmiSaveBlocks);

		/* Canvas-Mouse-Interaction for Painting */
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				isMouseDown = true;
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				isMouseDown = false;
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				AddBlock(e.getX(), e.getY());
			}

		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (isMouseDown)
					AddBlock(e.getX(), e.getY());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				/* Canvas-5%-Border leads to a Level extension */
				/* Extend Level to the right */
				if (e.getX() > ((double) canvas.getWidth() * 0.95))
					level.move(true, canvas);
				/* Scrolling back to the first block is allowed */
				else if (e.getX() < ((double) canvas.getWidth() * 0.05))
					if (level.getListe() != null)
						if (level.getListe().size() != 0)
							if (level.getListe().get(0).getX()
									- level.getListe().get(0).getWidth() < 0)
								level.move(false, canvas);
			}
		});

	}

	/**
	 * Draws the chosen block on the canvas. Additionally verifies it and binds
	 * it to the level.
	 */
	private void AddBlock(int x, int y) {
		if (gameblockList.getSelectedValue() != null) {

			/* Create NEW Block */
			Gameblock parent = gameblockList.getSelectedValue();
			Gameblock newBlock;
			if (parent.isFuellen()) {
				while (y < canvas.getHeight()) {
					if (parent.getImage() == null)
						newBlock = new Gameblock(x, y, parent.getWidth(),
								parent.getHeight(), parent.getIsDeadly(),
								parent.getName(), parent.getColor(), parent.isFuellen());
					else
						newBlock = new Gameblock(x, y, parent.getImageSource(),
								parent.getIsDeadly(), parent.getName(), parent.isFuellen());

					/* Spawn & Goal - lock */
					if ((this.level.getSpawn() != null && newBlock
							.getName()
							.equals(EnumStandardGameblockNames.SPAWN.toString()))
							|| (this.level.getGoal() != null && newBlock
									.getName().equals(
											EnumStandardGameblockNames.GOAL
													.toString())))
						return;

					level.processNewBlock(newBlock);
					
					y+=parent.getHeight();
				}
			}else{
				if (parent.getImage() == null)
					newBlock = new Gameblock(x, y, parent.getWidth(),
							parent.getHeight(), parent.getIsDeadly(),
							parent.getName(), parent.getColor(), parent.isFuellen());
				else
					newBlock = new Gameblock(x, y, parent.getImageSource(),
							parent.getIsDeadly(), parent.getName(), parent.isFuellen());

				/* Spawn & Goal - lock */
				if ((this.level.getSpawn() != null && newBlock
						.getName()
						.equals(EnumStandardGameblockNames.SPAWN.toString()))
						|| (this.level.getGoal() != null && newBlock
								.getName().equals(
										EnumStandardGameblockNames.GOAL
												.toString())))
					return;

				level.processNewBlock(newBlock);
			}
		}

	}

}
