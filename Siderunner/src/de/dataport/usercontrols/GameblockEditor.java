package de.dataport.usercontrols;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;

import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.GameblockEditorMode;
import de.dataport.system.Serializer;

import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JCheckBox;

import java.awt.Font;
import java.awt.Component;

import javax.swing.Box;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameblockEditor extends JDialog {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldImageSource;
	private JDialog thisDialog;
	private JCheckBox chckbxIsDeadly;
	private JTextField textFieldName;
	private JScrollPane jspGameblockView;

	private JList<Gameblock> gameblockList;
	GameblockEditorMode mode;

	/**
	 * Creates a view for editing or adding a Gameblock.
	 * 
	 * @param gameblockList
	 *            List, that contains or will contain the Gameblock.
	 * @param mode
	 *            Defines if selected Gameblock from list will be edited or a
	 *            new one will be added.
	 */
	public GameblockEditor(JList<Gameblock> gameblockList, GameblockEditorMode mode) {
		this.gameblockList = gameblockList;
		this.mode = mode;
		initialize();
		if (mode == GameblockEditorMode.EDIT)
			loadGameblockToView();
	}

	private void initialize() {
		/* JDialog */
		thisDialog = this;
		setResizable(false);
		setTitle("Gameblock Editor");
		setBounds(100, 100, 200, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		jspGameblockView = new JScrollPane(null);
		jspGameblockView.setBounds(10, 11, 174, 42);
		getContentPane().add(jspGameblockView);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setBounds(0, 64, 194, 7);
		getContentPane().add(horizontalStrut_3);

		textFieldName = new JTextField();
		textFieldName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateView();
			}
		});
		textFieldName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldName.setColumns(10);
		textFieldName.setBounds(10, 84, 174, 23);
		getContentPane().add(textFieldName);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblName.setBounds(10, 118, 46, 14);
		getContentPane().add(lblName);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(0, 143, 194, 7);
		getContentPane().add(horizontalStrut);

		textFieldImageSource = new JTextField();
		textFieldImageSource.setEditable(false);
		textFieldImageSource.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textFieldImageSource.setBounds(10, 161, 174, 23);
		getContentPane().add(textFieldImageSource);
		textFieldImageSource.setColumns(10);

		JLabel lblImage = new JLabel("Image");
		lblImage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblImage.setBounds(10, 195, 46, 14);
		getContentPane().add(lblImage);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldImageSource.setText(Serializer.getImagePath(thisDialog, 0, "Choose Image"));
				UpdateView();
			}
		});

		btnBrowse.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBrowse.setBounds(95, 191, 89, 23);
		getContentPane().add(btnBrowse);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 220, 194, 7);
		getContentPane().add(horizontalStrut_1);

		chckbxIsDeadly = new JCheckBox("is Deadly?");
		chckbxIsDeadly.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UpdateView();
			}
		});
		chckbxIsDeadly.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxIsDeadly.setBounds(10, 234, 97, 23);
		getContentPane().add(chckbxIsDeadly);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(0, 264, 194, 7);
		getContentPane().add(horizontalStrut_2);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<Gameblock> listModel = (DefaultListModel<Gameblock>) gameblockList
						.getModel();

				if (mode == GameblockEditorMode.ADD) {
					listModel.addElement(new Gameblock(null, null, textFieldImageSource.getText(),
							chckbxIsDeadly.isSelected(), textFieldName.getText()));
				} else {
					Gameblock gb = gameblockList.getSelectedValue();
					if (gb != null) {
						gb.setImageSource(textFieldImageSource.getText());
						gb.setIsDeadly(chckbxIsDeadly.isSelected());
						gb.setName(textFieldName.getText());
					}
				}

				gameblockList.setModel(gameblockList.getModel());
				dispose();
			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.setBounds(95, 282, 89, 23);
		getContentPane().add(btnSave);
		setVisible(true);
	}

	private void UpdateView() {
		
		DefaultListModel<Gameblock> listModel = new DefaultListModel<Gameblock>();
		listModel.addElement(new Gameblock(null, null, textFieldImageSource.getText(), chckbxIsDeadly
				.isSelected(), textFieldName.getText()));

		JList<Gameblock> gameblock = new JList<Gameblock>(listModel);
		gameblock.setCellRenderer(new GameblockListElement());
		jspGameblockView.setViewportView(gameblock);
	}

	private void loadGameblockToView() {
		if (gameblockList.getSelectedValue() != null) {
			textFieldName.setText(gameblockList.getSelectedValue().getName());
			textFieldImageSource.setText(gameblockList.getSelectedValue().getImageSource());
			chckbxIsDeadly.setSelected(gameblockList.getSelectedValue().getIsDeadly());
			UpdateView();
		}
	}

}
