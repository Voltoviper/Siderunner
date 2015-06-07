package de.dataport.window;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import de.dataport.network.Client;
import de.dataport.network.RMIClient;

public class Multiplayer extends JFrame
{

	static Multiplayer multiplayer;
	static JFrame frame;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	JLabel LabelLoading;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{

					multiplayer = new Multiplayer();
					Multiplayer.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Multiplayer()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		frame.getContentPane().add(contentPane);
		frame.setVisible(true);

		textField = new JTextField();
		textField.setBounds(299, 78, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);

		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setLabelFor(textField);
		lblNewLabel.setBounds(250, 81, 46, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		ButtonGroup radiobuttons = new ButtonGroup();
		JRadioButton RadioRandomSearching = new JRadioButton("Random Searching");
		RadioRandomSearching.setSelected(true);
		RadioRandomSearching.setBounds(278, 23, 135, 23);
		RadioRandomSearching.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if (RadioRandomSearching.isSelected())
				{

					textField.setVisible(false);
					lblNewLabel.setVisible(false);
				}
				else
				{
					textField.setVisible(true);
					lblNewLabel.setVisible(true);
				}
			}

		});
		radiobuttons.add(RadioRandomSearching);
		contentPane.add(RadioRandomSearching);

		JRadioButton RadioSearchDirect = new JRadioButton("Direct Search");
		RadioSearchDirect.setBounds(278, 48, 135, 23);
		RadioSearchDirect.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if (RadioSearchDirect.isSelected())
				{

					textField.setVisible(true);
					lblNewLabel.setVisible(true);
				}
				else
				{
					textField.setVisible(false);
					lblNewLabel.setVisible(false);
				}
			}
		});
		radiobuttons.add(RadioSearchDirect);
		contentPane.add(RadioSearchDirect);

		JButton ButtonSearch = new JButton("Search");
		ButtonSearch.setBounds(278, 121, 146, 23);
		ButtonSearch.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				LabelLoading.setVisible(true);
				if(RadioRandomSearching.isSelected()){
					Client client = new Client("Hans Peter", 1);
					RMIClient network = new RMIClient();
					try
					{
						network.start(client);
					} catch (RemoteException | NotBoundException e1)
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					LabelLoading.setVisible(false);
					
				}else if(RadioSearchDirect.isSelected()){
					
				}
			}
			
		});
		contentPane.add(ButtonSearch);

		LabelLoading = new JLabel("");
		ImageIcon icon = new ImageIcon(Start.class.getResource("/de/dataport/window/graphics/loading.gif"));
		ImageIcon icon2 = new ImageIcon();
		icon2.setImage(icon.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		LabelLoading.setIcon(icon2);
		LabelLoading.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelLoading.setBounds(404, 231, 20, 20);
		contentPane.add(LabelLoading);
		LabelLoading.setVisible(false);

	}
}
