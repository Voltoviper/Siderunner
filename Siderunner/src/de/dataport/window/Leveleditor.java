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
import javax.swing.ScrollPaneConstants;

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

		Gameblock peter = new Gameblock(null, null, false, "Peter", Color.BLUE);
		Gameblock peter1 = new Gameblock(null, null, false, "Peter1", Color.RED);
		Gameblock peter2 = new Gameblock(null, null, false, "Peter2", Color.BLACK);
		Gameblock peter3 = new Gameblock(null, null, false, "Peter3", Color.GREEN);
		Gameblock peter4 = new Gameblock(null, null, false, "Peter4", Color.CYAN);
		Gameblock peter5 = new Gameblock(null, null, false, "Peter5", Color.MAGENTA);
		
        //create the model and add elements
        DefaultListModel<Gameblock> listModel = new DefaultListModel<>();
        listModel.addElement(peter);
        listModel.addElement(peter1);
        listModel.addElement(peter2);
        listModel.addElement(peter3);
        listModel.addElement(peter4);
        listModel.addElement(peter5);

        //create the list
        JList<Gameblock> gameblockList = new JList<>(listModel);
        gameblockList.setLayoutOrientation(JList.VERTICAL_WRAP);
        gameblockList.setCellRenderer(new GameblockListElement());
        JScrollPane scrollPane = new JScrollPane(gameblockList);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        
        scrollPane.setBounds(10, 83, 186, 251);

		frame.getContentPane().add(scrollPane);
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
