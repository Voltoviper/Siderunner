package de.dataport.usercontrols;

import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import de.dataport.standardcatalog.StandardContent;
import de.dataport.window.Start;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;

public class RotatingLogo extends JPanel {

	private static final long serialVersionUID = 1L;
	private ImageIcon imageBackground;
	private ImageIcon imageForeground;

	private double angle;
	private JTextField textField;

	public RotatingLogo() {
		initialize();
	}

	public RotatingLogo(int scaleWidthBackground, int scaleHeightBackground, int scaleWidthForeground,
			int scaleHeightForground) {
		this();
		imageBackground = new ImageIcon(resizeImg(imageBackground, scaleWidthBackground, scaleHeightBackground));
		imageForeground = new ImageIcon(resizeImg(imageForeground, scaleWidthForeground, scaleHeightForground));
	}

	private void initialize() {
		imageBackground = new ImageIcon(
				Start.class.getResource("/de/dataport/window/graphics/logo background"
						+ ((int) new Random().nextInt(3) + 1) + ".png"));
		imageForeground = new ImageIcon(
				Start.class.getResource("/de/dataport/window/graphics/logo foreground2.png"));
		Timer timer = new Timer(40, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				angle += 2;
				repaint();
			}
		});
		Box verticalBox = Box.createVerticalBox();
		add(verticalBox);
		
		Component verticalStrut = Box.createVerticalStrut((int)(imageBackground.getIconHeight()*0.93));
		verticalBox.add(verticalStrut);
		
		Box horizontalBox = Box.createHorizontalBox();
		verticalBox.add(horizontalBox);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalBox.add(horizontalStrut);
		
		textField = new JTextField("Siderunner");
		horizontalBox.add(textField);
		textField.setColumns(10);
		textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		textField.setOpaque(false);
		textField.setFont(StandardContent.neuropolFont(Font.BOLD, 25f));
		textField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textField.setHorizontalAlignment(JTextField.CENTER);
		Start.frame.requestFocusInWindow();
		timer.start();
	}

	public static BufferedImage resizeImg(ImageIcon img, int newW, int newH) {

		BufferedImage buImg = new BufferedImage(img.getIconWidth(), img.getIconHeight(),
				BufferedImage.TYPE_INT_ARGB);

		buImg.getGraphics().drawImage(img.getImage(), 0, 0, img.getImageObserver());

		int w = buImg.getWidth();
		int h = buImg.getHeight();
		BufferedImage dimg = new BufferedImage(newW, newH, buImg.getType());
		Graphics2D g = dimg.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(buImg, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return dimg;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g.create();

		int x = 25;
		int y = (getHeight() - imageBackground.getIconHeight()) / 2;

		AffineTransform at = new AffineTransform();
		at.translate(x, y);
		at.rotate(Math.toRadians(angle), imageBackground.getIconWidth() / 2,
				imageBackground.getIconHeight() / 2);
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
