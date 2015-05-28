package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;
import de.dataport.standardcatalog.StandardContent;
import de.dataport.system.Serializer;
import de.dataport.usercontrols.GameblockListElement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;

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
	}

	/** Instantiating and filling the Gameblock-Jlist */
	private void fillList() {

		gameblockList = new JList<Gameblock>(createDefaultBlockCatalog());
		gameblockList.setCellRenderer(new GameblockListElement());
		JScrollPane jspGameblocks = new JScrollPane(gameblockList);

		jspGameblocks.setBounds(10, 11, 186, 251);

		frame.getContentPane().add(jspGameblocks);

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
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		/* Canvas */
		canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(202, 10, 582, 501);
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
				DrawBlock(e.getX(), e.getY());
			}

		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (isMouseDown)
					DrawBlock(e.getX(), e.getY());
			}
		});
		frame.getContentPane().add(canvas);

		/* Scrollbar */
		Scrollbar scrollbar = new Scrollbar();
		scrollbar.addAdjustmentListener(new AdjustmentListener() {
			public void adjustmentValueChanged(AdjustmentEvent arg0) {
				
			}
		});
		scrollbar.setOrientation(Scrollbar.HORIZONTAL);
		scrollbar.setBounds(202, 517, 582, 23);
		frame.getContentPane().add(scrollbar);

		/* Menu */
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnWorld = new JMenu("World");
		menuBar.add(mnWorld);

		JMenuItem mntmNew = new JMenuItem("New...");
		mnWorld.add(mntmNew);

		JMenuItem mntmLoad = new JMenuItem("Load...");
		mnWorld.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("Save...");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				Speichern_unter speichern = new Speichern_unter();
//				speichern.saveAs(null, level);
				try {
					Serializer.write(level, frame);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		mnWorld.add(mntmSave);

		JMenu mnBlocks = new JMenu("Blocks");
		menuBar.add(mnBlocks);

		JMenuItem mntmNew_1 = new JMenuItem("New...");
		mnBlocks.add(mntmNew_1);

		JMenuItem mntmManage = new JMenuItem("Manage...");
		mnBlocks.add(mntmManage);
	}

	/**
	 * Draws the chosen block on the canvas. Additionally verifies it and binds
	 * it to the level.
	 */
	private void DrawBlock(int x, int y) {
		if (gameblockList.getSelectedValue() != null) {

			/* Create NEW Block */
			Gameblock newBlock = new Gameblock(x, y, gameblockList.getSelectedValue().getWidth(), gameblockList
					.getSelectedValue().getHeight(), gameblockList.getSelectedValue().getIsDeadly(), gameblockList
					.getSelectedValue().getName(), gameblockList.getSelectedValue().getColor());

			/* Spawn & Goal - lock */
			if ((this.level.getSpawn() != null && newBlock.getName().equals("Spawn"))
					|| (this.level.getGoal() != null && newBlock.getName().equals("Goal")))
				return;

			newBlock.paint(canvas, level);

		}

	}
}
