package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
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
	static JPanel panel;
	private JScrollPane jspGameblocks;
	public static Painter backgroundPainter;

	private Level level;

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

//		
		panel = new JPanel();
		panel.setBounds(0, 0, Fullscreen.desktopPane.getWidth(), Fullscreen.desktopPane.getHeight());
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(208, 20, Fullscreen.desktopPane.getWidth(),Fullscreen.desktopPane.getHeight());
		panel.add(canvas);



		/* Level-Menu */
		JMenu jmLevel = new JMenu("Level");
		jmLevel.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		Fullscreen.menuBar.add(jmLevel);

		JMenuItem jmiNewLevel = new JMenuItem("New...");
		jmiNewLevel.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		jmiNewLevel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				level.deleteLevel(canvas);
			}
		});
		jmLevel.add(jmiNewLevel);

		JMenuItem jmiLoadLevel = new JMenuItem("Load...");
		jmiLoadLevel.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		jmLevel.add(jmiLoadLevel);

		JMenuItem jmiSave = new JMenuItem("Save...");
		jmiSave.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		jmiSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Speichern_unter speichern = new Speichern_unter();
				// speichern.saveAs(null, level);
				try {
					Serializer.write(level, panel);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		jmLevel.add(jmiSave);



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
	 * Fügt den ausgewählten Block dem Level hinzu!
	 * @param x Koordinate des neuen Blockes
	 * @param y Koordinate des neuen Blockes
	 */
	private void AddBlock(int x, int y) {
		if (gameblockList.getSelectedValue() != null) {

			/* Create NEW Block */
			Gameblock parent = gameblockList.getSelectedValue();
			Gameblock newBlock;
			if (parent.isFillDownwards()) {
				while (y < canvas.getHeight()) {
					if (parent.getImage() == null)
						newBlock = new Gameblock(x, y, parent.getWidth(),
								parent.getHeight(), parent.isDeadly(),
								parent.getName(), parent.getColor(), parent.isFillDownwards());
					else
						newBlock = new Gameblock(x, y, parent.getImageSource(),
								parent.isDeadly(), parent.getName(), parent.isFillDownwards());

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
							parent.getHeight(), parent.isDeadly(),
							parent.getName(), parent.getColor(), parent.isFillDownwards());
				else
					newBlock = new Gameblock(x, y, parent.getImageSource(),
							parent.isDeadly(), parent.getName(), parent.isFillDownwards());

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
