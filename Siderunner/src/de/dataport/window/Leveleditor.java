package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Scrollbar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;
import de.dataport.usercontrols.GameblockListElement;
import java.awt.event.MouseMotionAdapter;

public class Leveleditor {

	private Thread tDrawBlocks;
	private Integer xForThread;
	private Integer yForThread;
	private boolean tRunning;

	private JList<Gameblock> gameblockList;
	private Canvas canvas;
	private JFrame frame;

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
		level = new Level();
	}

	private void TESTfillList() {

		Gameblock peter = new Gameblock(null, null, 10, 15, false, "Peter",
				Color.BLUE);
		Gameblock peter1 = new Gameblock(null, null, 10, 10, false, "Peter1",
				Color.RED);
		Gameblock peter2 = new Gameblock(null, null, 5, 5, false, "Peter2",
				Color.BLACK);
		Gameblock peter3 = new Gameblock(null, null, 30, 30, true, "Peter3",
				Color.GREEN);
		Gameblock peter4 = new Gameblock(null, null, 1, 1, false, "Peter4",
				Color.CYAN);
		Gameblock peter5 = new Gameblock(null, null, 3, 60, true, "Peter5",
				Color.MAGENTA);
		Gameblock peter6 = new Gameblock(null, null, 30, 3, false, "Peter6",
				Color.DARK_GRAY);
		Gameblock peter7 = new Gameblock(null, null, 13, 37, false, "Peter7",
				Color.YELLOW);

		// create the model and add elements
		DefaultListModel<Gameblock> listModel = new DefaultListModel<>();
		listModel.addElement(peter);
		listModel.addElement(peter1);
		listModel.addElement(peter2);
		listModel.addElement(peter3);
		listModel.addElement(peter4);
		listModel.addElement(peter5);
		listModel.addElement(peter6);
		listModel.addElement(peter7);

		// create the list
		gameblockList = new JList<Gameblock>(listModel);
		gameblockList.setCellRenderer(new GameblockListElement());
		JScrollPane jspGameblocks = new JScrollPane(gameblockList);

		jspGameblocks.setBounds(10, 11, 186, 251);

		frame.getContentPane().add(jspGameblocks);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		canvas = new Canvas();
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				xForThread = e.getX();
				yForThread = e.getY();
			}
		});
		canvas.setBackground(Color.WHITE);
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				drawBlocks(false);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				tRunning = true;
				tDrawBlocks = new Thread(new Runnable() {
					public void run() {
						while (tRunning)
							drawBlocks(tRunning);
					}
				});
				tDrawBlocks.start();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				tRunning = false;
				System.out.println(tRunning);
				tDrawBlocks.interrupt();
			}
		});

		canvas.setBounds(202, 10, 582, 501);

		frame.getContentPane().add(canvas);

		Scrollbar scrollbar = new Scrollbar();
		scrollbar.setOrientation(Scrollbar.HORIZONTAL);
		scrollbar.setBounds(202, 517, 582, 23);
		frame.getContentPane().add(scrollbar);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnWorld = new JMenu("World");
		menuBar.add(mnWorld);

		JMenuItem mntmNew = new JMenuItem("New...");
		mnWorld.add(mntmNew);

		JMenuItem mntmLoad = new JMenuItem("Load...");
		mnWorld.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("Save...");
		mnWorld.add(mntmSave);

		JMenu mnBlocks = new JMenu("Blocks");
		menuBar.add(mnBlocks);

		JMenuItem mntmNew_1 = new JMenuItem("New...");
		mnBlocks.add(mntmNew_1);

		JMenuItem mntmManage = new JMenuItem("Manage...");
		mnBlocks.add(mntmManage);

		TESTfillList();
	}

	private void drawBlocks(boolean pressedCondition) {
		while (pressedCondition)
			if (gameblockList.getSelectedValue() != null) {
				Gameblock block = gameblockList.getSelectedValue();
				block.setX(xForThread);
				block.setY(yForThread);
				block.paint(canvas);
				level.addBlock(block);
			}
	}
}
