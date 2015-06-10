package de.dataport.usercontrols;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import de.dataport.datastructures.Gameblock;
import de.dataport.standardcatalog.GameblockEditorMode;
import de.dataport.standardcatalog.StandardContent;

public class PopUpMenuGameblock extends JPopupMenu {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JMenuItem jmiAdd;
	JMenuItem jmiEdit;
	JMenuItem jmiDelete;

	public PopUpMenuGameblock(JList<Gameblock> gameblockList) {

		/* Add new Gameblock */
		jmiAdd = new JMenuItem("Add...");
		jmiAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GameblockEditor(gameblockList, GameblockEditorMode.ADD);
			}
		});
		add(jmiAdd);

		/* Edit existing Gameblock, but no standard-block */
		if (gameblockList.getSelectedValue() != null) {
			if (!StandardContent.isStandardBlock(gameblockList.getSelectedValue())) {
				jmiEdit = new JMenuItem("Edit...");
				jmiEdit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						new GameblockEditor(gameblockList, GameblockEditorMode.EDIT);
					}
				});
				add(jmiEdit);

				/* Delete created Block */
				jmiDelete = new JMenuItem("Delete...");
				jmiDelete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						gameblockList.remove(gameblockList.getSelectedIndex());
					}
				});
				add(jmiDelete);
			}
		}

	}
}
