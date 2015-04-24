package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Scrollbar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import de.dataport.datastructures.Gameblock;
import de.dataport.usercontrols.GameblockListElement;

import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

public class Leveleditor {

	private JList<Gameblock> gameblockList;
	private Canvas canvas;
	private JFrame frame;

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
	}

	private void TESTfillList() {

		Gameblock peter = new Gameblock(null,null,10, 15, false, "Peter", Color.BLUE);
		Gameblock peter1 = new Gameblock(null,null,10, 10, false, "Peter1", Color.RED);
		Gameblock peter2 = new Gameblock(null,null,5, 5, false, "Peter2", Color.BLACK);
		Gameblock peter3 = new Gameblock(null,null,30, 30, true, "Peter3", Color.GREEN);
		Gameblock peter4 = new Gameblock(null,null,1, 1, false, "Peter4", Color.CYAN);
		Gameblock peter5 = new Gameblock(null,null,3, 60, true, "Peter5", Color.MAGENTA);
		Gameblock peter6 = new Gameblock(null,null,30, 3, false, "Peter6",
				Color.DARK_GRAY);
		Gameblock peter7 = new Gameblock(null,null,13, 37, false, "Peter7", Color.YELLOW);

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

		jspGameblocks.setBounds(10, 83, 186, 251);

		frame.getContentPane().add(jspGameblocks);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 514, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		canvas = new Canvas();
		canvas.setBackground(Color.WHITE);
		canvas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/* Placing the selected Gameblock on the levelsurface. */
				Graphics g = canvas.getGraphics();
				canvas.paint(g);
				int x = arg0.getX();
				int y = arg0.getY();
				int w = gameblockList.getSelectedValue().getWidth();
				int h = gameblockList.getSelectedValue().getHeigth();
				g.setColor(gameblockList.getSelectedValue().getColor());
				g.fillRect(x-w/2, y-h/2, w, h);
				
			}
		});
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				/*
				 * Presenting the current selection, while moving the cursor
				 * over the canvas
				 */
				
				
				
			}
		});
		canvas.setBounds(202, 11, 286, 294);
		frame.getContentPane().add(canvas);

		Scrollbar scrollbar = new Scrollbar();
		scrollbar.setOrientation(Scrollbar.HORIZONTAL);
		scrollbar.setBounds(202, 311, 286, 23);
		frame.getContentPane().add(scrollbar);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(399, 350, 89, 23);
		frame.getContentPane().add(btnSave);

		
		TESTfillList();
	}
}
