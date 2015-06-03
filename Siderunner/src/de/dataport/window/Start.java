package de.dataport.window;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Start {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Start window = new Start();
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
	public Start() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Jack Runner");
		frame.setBounds(Toolkit.getDefaultToolkit()
	            .getScreenSize().width/2-225, 0, 450, 728);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

		Box verticalBox = Box.createVerticalBox();
		verticalBox.setAlignmentX(Component.CENTER_ALIGNMENT);
		frame.getContentPane().add(verticalBox);

		Box horizontalBoxLogo = Box.createHorizontalBox();
		verticalBox.add(horizontalBoxLogo);

		JPanel panel = new JPanel();
		horizontalBoxLogo.add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JLabel lblIcon = new JLabel("");
		lblIcon.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Siderunner Test Logo.png")));
		lblIcon.setHorizontalAlignment(SwingConstants.CENTER);

		panel.add(lblIcon, BorderLayout.CENTER);

		Box verticalBoxButtons = Box.createVerticalBox();
		verticalBox.add(verticalBoxButtons);

		JButton btnSingleplayer = new JButton("");
		btnSingleplayer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				new Main();
				Main.frmJackRunner.setVisible(true);
			}
		});
		btnSingleplayer
				.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Singleplayer.png")));
		btnSingleplayer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		verticalBoxButtons.add(btnSingleplayer);
		btnSingleplayer.setAlignmentX(Component.CENTER_ALIGNMENT);

		JButton btnMultiplayer = new JButton("");
		btnMultiplayer.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Multiplayer.png")));
		btnMultiplayer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMultiplayer.setAlignmentX(Component.CENTER_ALIGNMENT);
		verticalBoxButtons.add(btnMultiplayer);

		JButton btnLeveleditor = new JButton("");
		btnLeveleditor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new Leveleditor().getFrame().setVisible(true);
			}
		});
		btnLeveleditor.setIcon(new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/Leveleditor.png")));
		btnLeveleditor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		verticalBoxButtons.add(btnLeveleditor);
		btnLeveleditor.setAlignmentX(Component.CENTER_ALIGNMENT);

	}

}
