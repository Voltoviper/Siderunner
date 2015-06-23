package de.dataport.datastructures;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconHelper implements Icon {

	private Integer iconHeight;
	private Integer iconWidth;
	private Color color;

	public IconHelper(Integer iconWidth, Integer iconHeight, Color color) {
		this.iconHeight = iconHeight;
		this.iconWidth = iconWidth;
		this.color = color;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		g.setColor(color);
		g.fillRect(0, 0, iconWidth, iconHeight);
	}

	@Override
	public int getIconHeight() {
		return iconHeight;
	}

	@Override
	public int getIconWidth() {
		return iconWidth;
	}
}
