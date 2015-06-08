package de.dataport.window;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

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
import de.dataport.network.Game_Link_Client;
import de.dataport.network.Game_Link_Server;
import de.dataport.network.RandomServerClient;

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
	JLabel LabelIcon;
	JLabel lblServerGestartet;
	private JTextField textField_1;
	Client client;

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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setBounds(100, 100, 450, 300);
		frame.setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		frame.getContentPane().add(contentPane);
		frame.setVisible(true);

		textField = new JTextField();
		textField.setBounds(299, 105, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);

		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setLabelFor(textField);
		lblNewLabel.setBounds(254, 108, 46, 14);
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

		JRadioButton RadioHost = new JRadioButton("Host Game");
		RadioHost.setBounds(278, 49, 109, 23);
		RadioHost.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				if (RadioHost.isSelected())
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
		contentPane.add(RadioHost);
		radiobuttons.add(RadioHost);

		JRadioButton RadioSearchDirect = new JRadioButton("Direct Search");
		RadioSearchDirect.setBounds(278, 75, 135, 23);
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
		ButtonSearch.setBounds(278, 141, 146, 23);
		ButtonSearch.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				LabelLoading.setVisible(true);
				if(textField_1.getText()!=null){
					client = new Client(textField_1.getText(), 1);
					}
					else{
						client = new Client("Player"+Math.random()*10000,1);
					}
				if (RadioRandomSearching.isSelected())
				{
					
					
					RandomServerClient network = new RandomServerClient();
					try
					{
						network.start(client);
					} catch (RemoteException | NotBoundException e1)
					{
						e1.printStackTrace();
					}
					LabelLoading.setVisible(false);

				}
				else if (RadioSearchDirect.isSelected())
				{
					Game_Link_Client game_link_client = new Game_Link_Client();
					try
					{
						Client client1 = new Client("Player",1);
						
						lblServerGestartet.setText(game_link_client.start(client1));
						LabelIcon.setIcon(new ImageIcon(Multiplayer.class.getResource("/de/dataport/window/graphics/gruener_haken.gif")));
					} catch (RemoteException e1)
					{
						lblServerGestartet.setText("Fehler bei der Verbindung!");
						LabelIcon.setIcon(new ImageIcon(Multiplayer.class.getResource("/de/dataport/window/graphics/rotes_kreuz.gif")));
					} catch (NotBoundException e1)
					{
						e1.printStackTrace();
						LabelIcon.setIcon(new ImageIcon(Multiplayer.class.getResource("/de/dataport/window/graphics/rotes_kreuz.gif")));
						lblServerGestartet.setText("Fehler bei der Verbindung!");
					}
					lblServerGestartet.setVisible(true);
					LabelIcon.setVisible(true);
					LabelLoading.setVisible(false);
				}
				else if (RadioHost.isSelected())
				{
					Game_Link_Server server = new Game_Link_Server(client);
					server.start(client);

					LabelIcon.setVisible(true);
					lblServerGestartet.setVisible(true);
					LabelLoading.setVisible(false);
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

		LabelIcon = new JLabel();
		LabelIcon.setIcon(new ImageIcon(Multiplayer.class.getResource("/de/dataport/window/graphics/gruener_haken.gif")));
		LabelIcon.setBounds(52, 34, 166, 135);
		contentPane.add(LabelIcon);
		LabelIcon.setVisible(false);

		lblServerGestartet = new JLabel("Server gestartet");
		lblServerGestartet.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerGestartet.setBounds(52, 180, 166, 14);
		contentPane.add(lblServerGestartet);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(241, 180, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		lblNewLabel_1.setLabelFor(textField_1);
		textField_1.setBounds(299, 175, 125, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		double rand = Math.random()*10000;
		textField_1.setText("Player"+ (int)rand);
		lblServerGestartet.setVisible(false);

	}
}
