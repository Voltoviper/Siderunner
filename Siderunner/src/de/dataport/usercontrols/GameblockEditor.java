package de.dataport.usercontrols;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;

import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.EnumGameblockEditorMode;
import de.dataport.standardcatalog.StandardContent;
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
	private JCheckBox chckbxIsDeadly, chckbxFillDownwards;
	private JTextField textFieldName;
	private JScrollPane jspGameblockView;

	private JList<Gameblock> gameblockList;
	EnumGameblockEditorMode mode;

	/**
	 * Erstellt ein Fenster, indem die Gameblöcke angepasst werden können.
	 * 
	 * @param gameblockList
	 *            Liste, in der die Gameblöcke organisiert sind
	 * @param mode
	 *            Legt fest, welcher Modus gewählt wurde
	 */
	public GameblockEditor(JList<Gameblock> gameblockList, EnumGameblockEditorMode mode) {
		this.gameblockList = gameblockList;
		this.mode = mode;
		initialize();
		if (mode == EnumGameblockEditorMode.EDIT)
			loadGameblockToView();
	}

	private void initialize() {
		/* JDialog */
		thisDialog = this;
		setResizable(false);
		setTitle("Gameblock Editor");
		setBounds(100, 100, 200, 379);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);

		jspGameblockView = new JScrollPane(null);
		jspGameblockView.setBounds(10, 7, 174, 55);
		getContentPane().add(jspGameblockView);

		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setBounds(0, 64, 194, 7);
		getContentPane().add(horizontalStrut_3);
		
		ActionListener alUpdateAction = new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateView();
			}
		};
		

		textFieldName = new JTextField();
		textFieldName.addActionListener(alUpdateAction);
		textFieldName.setFont(StandardContent.neuropolFont(Font.PLAIN, 12f));
		textFieldName.setColumns(10);
		textFieldName.setBounds(10, 84, 174, 23);
		getContentPane().add(textFieldName);

		JLabel lblName = new JLabel("Name");
		lblName.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		lblName.setBounds(10, 118, 46, 14);
		getContentPane().add(lblName);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(0, 143, 194, 7);
		getContentPane().add(horizontalStrut);

		textFieldImageSource = new JTextField();
		textFieldImageSource.setEditable(false);
		textFieldImageSource.setFont(StandardContent.neuropolFont(Font.PLAIN, 12f));
		textFieldImageSource.setBounds(10, 161, 174, 23);
		getContentPane().add(textFieldImageSource);
		textFieldImageSource.setColumns(10);

		JLabel lblImage = new JLabel("Image");
		lblImage.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		lblImage.setBounds(10, 195, 62, 14);
		getContentPane().add(lblImage);

		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textFieldImageSource.setText(Serializer.getImagePath(thisDialog, 0, "Choose Image"));
				UpdateView();
			}
		});

		btnBrowse.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		btnBrowse.setBounds(82, 191, 102, 23);
		getContentPane().add(btnBrowse);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(0, 220, 194, 7);
		getContentPane().add(horizontalStrut_1);

		chckbxIsDeadly = new JCheckBox("is deadly?");
		chckbxIsDeadly.addActionListener(alUpdateAction);
		chckbxIsDeadly.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		chckbxIsDeadly.setBounds(10, 234, 174, 23);
		getContentPane().add(chckbxIsDeadly);

		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setBounds(0, 283, 194, 7);
		getContentPane().add(horizontalStrut_2);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultListModel<Gameblock> listModel = (DefaultListModel<Gameblock>) gameblockList
						.getModel();

				if (mode == EnumGameblockEditorMode.ADD) {
					listModel.addElement(new Gameblock(null, null, textFieldImageSource.getText(),
							chckbxIsDeadly.isSelected(), textFieldName.getText(), chckbxFillDownwards.isSelected()));
				} else {
					Gameblock gb = gameblockList.getSelectedValue();
					if (gb != null) {
						gb.setImageSource(textFieldImageSource.getText());
						gb.setIsDeadly(chckbxIsDeadly.isSelected());
						gb.setName(textFieldName.getText());
					}
				}
			
				gameblockList.setModel(gameblockList.getModel());
				saveBlocksAutomatically(gameblockList);
				dispose();
			}
		});
		btnSave.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		btnSave.setBounds(82, 301, 102, 23);
		getContentPane().add(btnSave);
		
		chckbxFillDownwards = new JCheckBox("fill downwards?");
		chckbxFillDownwards.setFont(StandardContent.neuropolFont(Font.BOLD, 13f));
		chckbxFillDownwards.setBounds(10, 260, 174, 23);
		chckbxFillDownwards.addActionListener(alUpdateAction);
		getContentPane().add(chckbxFillDownwards);
		setVisible(true);
	}
	
	private void saveBlocksAutomatically(JList<Gameblock> gameblockList){
		Serializer.writeBlocks(gameblockList);
	}
	
	private void UpdateView() {
		
		DefaultListModel<Gameblock> listModel = new DefaultListModel<Gameblock>();
		listModel.addElement(new Gameblock(null, null, textFieldImageSource.getText(), chckbxIsDeadly
				.isSelected(), textFieldName.getText(), chckbxFillDownwards.isSelected()));

		JList<Gameblock> gameblock = new JList<Gameblock>(listModel);
		gameblock.setCellRenderer(new GameblockListElement());
		jspGameblockView.setViewportView(gameblock);
	}

	private void loadGameblockToView() {
		if (gameblockList.getSelectedValue() != null) {
			textFieldName.setText(gameblockList.getSelectedValue().getName());
			textFieldImageSource.setText(gameblockList.getSelectedValue().getImageSource());
			chckbxIsDeadly.setSelected(gameblockList.getSelectedValue().isDeadly());
			UpdateView();
		}
	}
}
