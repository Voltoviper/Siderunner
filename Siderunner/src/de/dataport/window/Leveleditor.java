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
import javax.swing.JLabel;
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

import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;

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
	private JPanel panel, panel2;
	private JMenuBar menuBar;
	private JMenuItem editoranzeigen, mntmNew_1;
	private JScrollPane jspGameblocks;

	public JFrame getFrame() {
		return frame;
	}

	private Level level;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

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

		canvas.setBackground(Color.WHITE);

		gameblockList = new JList<Gameblock>(createDefaultBlockCatalog());
		gameblockList.setCellRenderer(new GameblockListElement());
		jspGameblocks = new JScrollPane(gameblockList);
		jspGameblocks.setBounds(10, 28, 186, 251);
		panel.add(jspGameblocks);
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 794, 21);
		panel.add(menuBar);

		JMenu mnWorld = new JMenu("World");
		menuBar.add(mnWorld);

		JMenuItem mntmNew = new JMenuItem("New...");
		mntmNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				level.deleteLevel(canvas);
			}
		});
		mnWorld.add(mntmNew);

		JMenuItem mntmLoad = new JMenuItem("Load...");
		mnWorld.add(mntmLoad);

		JMenuItem mntmSave = new JMenuItem("Save...");
		mntmSave.addActionListener(new ActionListener() {
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

		mnWorld.add(mntmSave);

		JMenu mnBlocks = new JMenu("Blocks");
		menuBar.add(mnBlocks);

		mntmNew_1 = new JMenuItem("New...");
		mntmNew_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel2();

			}

		});
		mnBlocks.add(mntmNew_1);

		JMenuItem mntmManage = new JMenuItem("Manage...");
		mnBlocks.add(mntmManage);
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
		JMenu Editor = new JMenu("Editor");
		menuBar.add(Editor);

		editoranzeigen = new JMenuItem("anzeigen");
		editoranzeigen.setEnabled(false);
		Editor.add(editoranzeigen);

		
		
		canvas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if (isMouseDown)
					DrawBlock(e.getX(), e.getY());
			}
			@Override
			public void mouseMoved(MouseEvent e) {
				if(e.getX() > ((double)canvas.getWidth() * 0.90))
					level.move(true, canvas);
				else if (e.getX() < ((double)canvas.getWidth() * 0.10))
					level.move(false,canvas);
			}
		});

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		panel = new JPanel();
		panel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		canvas = new Canvas();
		canvas.setBounds(208, 20, 582, 501);
		panel.add(canvas);
		panel2 = new JPanel();
		panel2.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		frame.getContentPane().add(panel2);
		panel2.setLayout(null);
		panel2.setVisible(false);
		JLabel label = new JLabel("Breite");
		label.setBounds(10, 50, 75, 23);
		panel2.add(label);
		JLabel label2 = new JLabel("Hoehe");
		label2.setBounds(10, 80, 75, 23);
		panel2.add(label2);
		JLabel label3 = new JLabel("Name");
		label3.setBounds(10, 110, 75, 23);
		panel2.add(label3);
		JCheckBox chckbxNewCheckBox = new JCheckBox("toedlich?");
		chckbxNewCheckBox.setBounds(40, 170, 100, 23);
		panel2.add(chckbxNewCheckBox);
		JLabel label4 = new JLabel("Bild");
		label4.setBounds(10, 140, 75, 23);
		panel2.add(label4);

		JButton btnNewButton = new JButton("Hinzufuegen");
		btnNewButton.setBounds(10, 200, 150, 23);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Gameblock gb = new Gameblock(null, null, Integer.parseInt(textField.getText().toString()),
						Integer.parseInt(textField_1.getText().toString()), chckbxNewCheckBox.isSelected(),
						textField_2.getText().toString(), null);

				((DefaultListModel<Gameblock>) gameblockList.getModel()).addElement(gb);
			}

		});
		panel2.add(btnNewButton);

		textField = new JTextField();
		textField.setBounds(85, 50, 86, 23);
		panel2.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(85, 80, 86, 23);
		panel2.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(85, 110, 86, 23);
		panel2.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(85, 140, 150, 23);
		panel2.add(textField_3);
		textField_3.setColumns(10);

		JButton btnNewButton_1 = new JButton("Auswaehlen");
		btnNewButton_1.setBounds(235, 140, 110, 22);
		btnNewButton_1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				textField_3.setText(Serializer.getImagePath(frame, 0, "Bild auswählen"));
			}
			
		});
		panel2.add(btnNewButton_1);

		/* Canvas */

		/* Scrollbar */

		/* Menu */
	}

	/**
	 * Draws the chosen block on the canvas. Additionally verifies it and binds
	 * it to the level.
	 */
	private void DrawBlock(int x, int y) {
		if (gameblockList.getSelectedValue() != null) {

			/* Create NEW Block */
			Gameblock newBlock = new Gameblock(x, y, gameblockList.getSelectedValue().getWidth(),
					gameblockList.getSelectedValue().getHeight(), gameblockList.getSelectedValue()
							.getIsDeadly(), gameblockList.getSelectedValue().getName(), gameblockList
							.getSelectedValue().getColor());

			/* Spawn & Goal - lock */
			if ((this.level.getSpawn() != null && newBlock.getName().equals("Spawn"))
					|| (this.level.getGoal() != null && newBlock.getName().equals("Goal")))
				return;

			newBlock.paint(canvas, level);

		}

	}

	private void panel2() {
		if (panel.isVisible()) {
			panel.setVisible(false);
		}
		panel2.add(menuBar);
		frame.setTitle("Block hinzufuegen");
		panel2.setVisible(true);
		mntmNew_1.setEnabled(false);
		editoranzeigen.setEnabled(true);
		editoranzeigen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panel();

			}

		});

	}

	private void panel() {
		if (panel2.isVisible()) {
			panel2.setVisible(false);
		}
		frame.setTitle("Leveleditor");
		panel.add(menuBar);
		panel.setVisible(true);
		mntmNew_1.setEnabled(true);
		editoranzeigen.setEnabled(false);
	}
}
