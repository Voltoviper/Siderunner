package de.dataport.window;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListModel;

import de.dataport.datastructures.Gameblock;
import de.dataport.level.Level;
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
	public static  Painter backgroundPainter;

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
		backgroundPainter=new Painter(null, canvas, level);
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
		gameblockList.addMouseListener(new PopUpClickListener(){
			@Override
			public void mousePressed(MouseEvent e){
		        if (e.isPopupTrigger()){
		        	StartPopUp(e,gameblockList);
		        }
		    }
			@Override
		    public void mouseReleased(MouseEvent e){
		        if (e.isPopupTrigger()){
		        	StartPopUp(e,gameblockList);

		        }
		    }
			private void StartPopUp(MouseEvent e, JList<Gameblock> gameblockList) {
	        	PopUpMenuGameblock menu = new PopUpMenuGameblock(gameblockList);
	            menu.show(e.getComponent(), e.getX(), e.getY());
			}
		});
		
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
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mntmClose.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				int i = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Editor beenden?", "Beenden",JOptionPane.YES_NO_OPTION);
				if (i == 0)
					Menu.dispose(frame);
			}
			
		});
		mnWorld.add(mntmClose);

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
				AddBlock(e.getX(), e.getY());
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
					AddBlock(e.getX(), e.getY());
			}

			@Override
			public void mouseMoved(MouseEvent e) {
				/* Canvas-Rand führt zur Erweiterung des Levels */
				/* Level nach Rechts erweitern */
				if (e.getX() > ((double) canvas.getWidth() * 0.95))
					level.move(true, canvas);
				/*
				 * Zurückscrollen --> Keine Erweiterung nach Links; Blockade
				 * beim Block der am weitesten Links ist.
				 */
				else if (e.getX() < ((double) canvas.getWidth() * 0.05))
					if (level.getListe() != null)
						if (level.getListe().size() != 0)
							if (level.getListe().get(0).getX() - level.getListe().get(0).getWidth() < 0)
								level.move(false, canvas);
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
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				int i = JOptionPane.showConfirmDialog(frame, "Wollen Sie den Editor beenden?", "Beenden",JOptionPane.YES_NO_OPTION);
				if (i == 0)
					Menu.dispose(frame);
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
		btnNewButton_1.addActionListener(new ActionListener() {

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
	private void AddBlock(int x, int y) {
		if (gameblockList.getSelectedValue() != null) {

			/* Create NEW Block */
			Gameblock parent = gameblockList.getSelectedValue();
			Gameblock newBlock;
			if (parent.getImage() == null)
				newBlock = new Gameblock(x, y, parent.getWidth(), parent.getHeight(), parent.getIsDeadly(),
						parent.getName(), parent.getColor());
			else
				newBlock = new Gameblock(x, y, parent.getImageSource(), parent.getIsDeadly(),
						parent.getName());
			
			/* Spawn & Goal - lock */
			if ((this.level.getSpawn() != null && newBlock.getName().equals("Spawn"))
					|| (this.level.getGoal() != null && newBlock.getName().equals("Goal")))
				return;
			
			level.verification(newBlock);
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
