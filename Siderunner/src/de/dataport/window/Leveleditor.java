package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Scrollbar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

import de.dataport.datastructures.Gameblock;
import de.dataport.usercontrols.GameblockListElement;

public class Leveleditor {

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

		Gameblock peter = new Gameblock(10, 15, false, "Peter", Color.BLUE);
		Gameblock peter1 = new Gameblock(10, 10, false, "Peter1", Color.RED);
		Gameblock peter2 = new Gameblock(5, 5, false, "Peter2", Color.BLACK);
		Gameblock peter3 = new Gameblock(30, 30, true, "Peter3", Color.GREEN);
		Gameblock peter4 = new Gameblock(1, 1, false, "Peter4", Color.CYAN);
		Gameblock peter5 = new Gameblock(1, 30, true, "Peter5", Color.MAGENTA);
		Gameblock peter6 = new Gameblock(30, 1, false, "Peter6", Color.DARK_GRAY);
		Gameblock peter7 = new Gameblock(13, 37, false, "Peter7", Color.YELLOW);
		
        //create the model and add elements
        DefaultListModel<Gameblock> listModel = new DefaultListModel<>();
        listModel.addElement(peter);
        listModel.addElement(peter1);
        listModel.addElement(peter2);
        listModel.addElement(peter3);
        listModel.addElement(peter4);
        listModel.addElement(peter5);
        listModel.addElement(peter6);
        listModel.addElement(peter7);

        //create the list
        JList<Gameblock> gameblockList = new JList<Gameblock>(listModel);
        gameblockList.setCellRenderer(new GameblockListElement());
        JScrollPane jsp = new JScrollPane(gameblockList);
        
        jsp.setBounds(10, 83, 186, 251);

		frame.getContentPane().add(jsp);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 514, 423);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Canvas canvas = new Canvas();
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
