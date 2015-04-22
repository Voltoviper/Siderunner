package de.dataport.usercontrols;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import de.dataport.datastructures.Gameblock;

public class GameblockListElement extends JPanel implements ListCellRenderer<Gameblock>{

	 protected static Border noFocusBorder = new EmptyBorder(15, 1, 1, 1);

	  protected static TitledBorder focusBorder = new TitledBorder(LineBorder.createGrayLineBorder(),
	      "");
	
	@Override
	public Component getListCellRendererComponent(
			JList<? extends Gameblock> list, Gameblock gameblock, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub
		
		
		setBounds(0, 0, 327, 56);
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 73, 73);
		panel.setBackground(gameblock.getColor());
		add(panel);
		
		JLabel lblNewLabel = new JLabel(gameblock.getName());
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel.setBounds(93, 11, 252, 73);
		add(lblNewLabel);
		
		setBorder(cellHasFocus ? focusBorder : noFocusBorder);
		
		return this;
	} 
}
