package de.dataport.window;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Info extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public Info() {
		setTitle("\u00DCber Jack Runner");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblVersion = new JLabel("Version");
		lblVersion.setBounds(67, 39, 46, 14);
		contentPanel.add(lblVersion);
		
		JLabel label = new JLabel("0.1");
		label.setBounds(190, 39, 46, 14);
		contentPanel.add(label);
		
		JLabel lblHerausgeber = new JLabel("Herausgeber");
		lblHerausgeber.setBounds(67, 64, 67, 14);
		contentPanel.add(lblHerausgeber);
		
		JLabel lblJanKochChristoph = new JLabel("<html><body>Jan Koch <br> Christoph Nebendahl</body></html>");
		lblJanKochChristoph.setBounds(190, 64, 208, 28);
		contentPanel.add(lblJanKochChristoph);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Singleplayer.dialog.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
