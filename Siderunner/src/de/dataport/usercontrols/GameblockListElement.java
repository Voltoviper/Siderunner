package de.dataport.usercontrols;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

import de.dataport.datastructures.Gameblock;
import de.dataport.datastructures.IconHelper;

public class GameblockListElement extends JPanel implements ListCellRenderer<Gameblock> {
	public GameblockListElement() {
	}
	
	private static final long serialVersionUID = 1L;

	protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

	protected static Border noFocusBorder = new MatteBorder(0, 0, 1, 0, Color.BLACK);

	protected static TitledBorder focusBorder = new TitledBorder(LineBorder.createGrayLineBorder(), "");

	@Override
	public Component getListCellRendererComponent(JList<? extends Gameblock> list, Gameblock gameblock, int index,
			boolean isSelected, boolean cellHasFocus) {
		// TODO Auto-generated method stub

		JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, gameblock, index, isSelected,
				cellHasFocus);

		renderer.setFont(new Font("Tahoma", Font.PLAIN, 19));
		renderer.setText("<html><body><p style=\"padding:1; font-size:19\">" + gameblock.getName()
				+ "</p><p style=\"padding:1; font-size:10\">" + gameblock.infoSize() + " - " + gameblock.infoIsDeadly()
				+ "</p></body></html>");
		if (gameblock.getImage() == null)
			renderer.setIcon(new IconHelper(40, 40, gameblock.getColor()));
		else
			renderer.setIcon(gameblock.getImage());

		renderer.setBorder(cellHasFocus ? focusBorder : noFocusBorder);
		return renderer;
	}
}
