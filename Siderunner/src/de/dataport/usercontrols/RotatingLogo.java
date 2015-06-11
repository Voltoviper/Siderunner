package de.dataport.usercontrols;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.dataport.window.Start;

public class RotatingLogo extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private ImageIcon imageBackground;
	private ImageIcon imageForeground;

	private double angle;

	public RotatingLogo() {
		imageBackground = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/logo background"
				+ ((int) new Random().nextInt(3) + 1) + ".png"));
		imageForeground = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/logo foreground.png"));
		Timer timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				angle += 2;
				repaint();
			}
		});
		timer.start();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();

		int x = 25;
		int y = (getHeight() - imageBackground.getIconHeight()) / 2;

		AffineTransform at = new AffineTransform();
		at.translate(x, y);
		at.rotate(Math.toRadians(angle), imageBackground.getIconWidth() / 2, imageBackground.getIconHeight() / 2);
		g2d.setTransform(at);
		g2d.drawImage(imageBackground.getImage(), at, null);
		g.drawImage(imageForeground.getImage(), this.getWidth() / 2 - imageForeground.getIconWidth() / 2,
				this.getHeight() / 2 - imageForeground.getIconHeight() / 2, this);
		// // Reset...
		// // Equally, you could dispose of the g2d and create a new copy
		// g2d.setTransform(current);
		//
		// x = getWidth() - 25 - prop.getBounds().width;
		// y = (getHeight() - prop.getBounds().height) / 2;
		//
		// at = new AffineTransform();
		// at.translate(x, y);
		// at.rotate(Math.toRadians(-angle), prop.getBounds().width / 2,
		// prop.getBounds().height / 2);
		// g2d.setTransform(at);
		// g2d.setColor(Color.BLUE);
		// g2d.draw(prop);

		g2d.dispose();
	}

}
