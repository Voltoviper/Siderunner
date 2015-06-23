package de.dataport.window;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

import de.dataport.Objekte.Level;
import de.dataport.Objekte.Spielfigur;
import de.dataport.network.Client;
import de.dataport.network.Game_Link_Client;
import de.dataport.network.Game_Link_Server;
import de.dataport.network.RandomServerClient;
import de.dataport.system.Painter;
import de.dataport.system.Serializer;

import java.awt.Label;

import javax.swing.JSeparator;

public class Multiplayer extends JFrame {

	static Multiplayer multiplayer;
	static JFrame frame;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldIP;
	JLabel LabelLoading;
	static JLabel LabelIcon;
	static JLabel lblServerGestartet;
	private JTextField textFieldPlayerName;
	public static Client client;
	RandomServerClient network;
	JButton ButtonAbbrechen;
	static JButton ButtonSpielstarten;
	Game_Link_Client game_client;
	Game_Link_Server game_server;
	public static boolean spiel_server, spiel_client, gestartet = false;
	private static JTextField textField_2;
	private JButton btnAuswhlen;
	JLabel lblNewLabel_1;
	static boolean isHost;
	public static Level level;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					multiplayer = new Multiplayer("");
					Multiplayer.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param string 
	 */
	public Multiplayer(String playerName) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		frame.getContentPane().add(contentPane);
		frame.setVisible(true);

		textFieldIP = new JTextField();
		textFieldIP.setBounds(299, 105, 125, 20);
		contentPane.add(textFieldIP);
		textFieldIP.setColumns(10);
		textFieldIP.setVisible(false);

		JLabel lblNewLabel = new JLabel("IP:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setLabelFor(textFieldIP);
		lblNewLabel.setBounds(254, 108, 46, 14);
		contentPane.add(lblNewLabel);
		lblNewLabel.setVisible(false);

		ButtonGroup radiobuttons = new ButtonGroup();
		JRadioButton RadioRandomSearching = new JRadioButton("Random Searching");
		RadioRandomSearching.setSelected(true);
		RadioRandomSearching.setBounds(278, 23, 135, 23);
		RadioRandomSearching.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (RadioRandomSearching.isSelected()) {

					textFieldIP.setVisible(false);
					lblNewLabel.setVisible(false);
				} else {
					textFieldIP.setVisible(true);
					lblNewLabel.setVisible(true);
				}
			}

		});
		radiobuttons.add(RadioRandomSearching);
		contentPane.add(RadioRandomSearching);

		JRadioButton RadioHost = new JRadioButton("Host Game");
		RadioHost.setBounds(278, 49, 109, 23);
		RadioHost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (RadioHost.isSelected()) {

					textFieldIP.setVisible(false);
					lblNewLabel.setVisible(false);
				} else {
					textFieldIP.setVisible(true);
					lblNewLabel.setVisible(true);
				}
			}

		});
		contentPane.add(RadioHost);
		radiobuttons.add(RadioHost);

		JRadioButton RadioSearchDirect = new JRadioButton("Direct Search");
		RadioSearchDirect.setBounds(278, 75, 135, 23);
		RadioSearchDirect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (RadioSearchDirect.isSelected()) {

					textFieldIP.setVisible(true);
					lblNewLabel.setVisible(true);
				} else {
					textFieldIP.setVisible(false);
					lblNewLabel.setVisible(false);
				}
			}
		});
		radiobuttons.add(RadioSearchDirect);
		contentPane.add(RadioSearchDirect);

		JButton ButtonSearch = new JButton("Search");
		ButtonSearch.setBounds(278, 141, 146, 23);
		ButtonSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (textFieldPlayerName.getText() != null) {
					client = new Client(textFieldPlayerName.getText(), 1);
				} else {
					client = new Client("Player" + Math.random() * 10000, 1);
				}
				if (RadioRandomSearching.isSelected()) {

					network = new RandomServerClient();

					Thread net = new Thread(new Runnable() {

						@Override
						public void run() {
							try {
								LabelLoading.setVisible(true);
								Client c = network.start(client);
								if (c != null) {
									game_client = new Game_Link_Client();
									c = game_client.start(c);
									Nachricht(c.getName(), Icons.OK);
									isHost = false;
									SpielstartenButon();

								} else {
									network.waitforClient(client);
									game_server = new Game_Link_Server(client);
									game_server.start(client);
									isHost = true;
									Nachricht("Warte auf Verbindung! <br> IP:"+InetAddress.getLocalHost(), Icons.OK);
									ButtonSearch.setEnabled(false);
									ButtonAbbrechen.setVisible(true);
									textField_2.setVisible(true);
									btnAuswhlen.setVisible(true);
								}

								LabelLoading.setVisible(false);
							} catch (RemoteException | NotBoundException | UnknownHostException e) {
								System.out.println(e);
								Nachricht("Fehler bei der Verbindung",
										Icons.ERROR);
								LabelLoading.setVisible(false);
							}
						}

					});
					net.start();

				} else if (RadioSearchDirect.isSelected()) {
					game_client = new Game_Link_Client();
					try {
						Client c = new Client("Gegner", 1);
						c.setIp(textFieldIP.getText());
						c = game_client.start(c);
						c.setIp(textFieldIP.getText());
						Nachricht(c.getName(), Icons.OK);
						isHost = false;
						SpielstartenButon();
					} catch (RemoteException e1) {
						Nachricht("Fehler bei der Verbindung", Icons.ERROR);
					} catch (NotBoundException e1) {
						e1.printStackTrace();
						Nachricht("Fehler bei der Verbindung", Icons.ERROR);

					}
					lblServerGestartet.setVisible(true);
					LabelIcon.setVisible(true);
					LabelLoading.setVisible(false);
				} else if (RadioHost.isSelected()) {
					game_server = new Game_Link_Server(client);
					game_server.start(client);

					isHost = true;
					try {
						Nachricht("<html><body>Warte auf Verbindung<br> IP:"+InetAddress.getLocalHost()+"</body></html>", Icons.OK);
					} catch (UnknownHostException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					ButtonSearch.setEnabled(false);
					ButtonAbbrechen.setVisible(true);
					textField_2.setVisible(true);
					btnAuswhlen.setVisible(true);
				}
			}

		});
		contentPane.add(ButtonSearch);

		LabelLoading = new JLabel("");
		ImageIcon icon = new ImageIcon(
				Start.class
						.getResource("/de/dataport/window/graphics/loading.gif"));
		ImageIcon icon2 = new ImageIcon();
		icon2.setImage(icon.getImage().getScaledInstance(20, 20,
				Image.SCALE_DEFAULT));
		LabelLoading.setIcon(icon2);
		LabelLoading.setHorizontalAlignment(SwingConstants.RIGHT);
		LabelLoading.setBounds(414, 292, 20, 20);
		LabelLoading.setVisible(false);
		contentPane.add(LabelLoading);
		LabelIcon = new JLabel();
		LabelIcon
				.setIcon(new ImageIcon(
						Multiplayer.class
								.getResource("/de/dataport/window/graphics/gruener_haken.gif")));
		LabelIcon.setBounds(52, 34, 166, 135);
		contentPane.add(LabelIcon);
		LabelIcon.setVisible(false);

		lblServerGestartet = new JLabel("Server gestartet");
		lblServerGestartet.setHorizontalAlignment(SwingConstants.CENTER);
		lblServerGestartet.setBounds(38, 164, 166, 36);
		contentPane.add(lblServerGestartet);

		lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(241, 180, 46, 14);
		contentPane.add(lblNewLabel_1);

		textFieldPlayerName = new JTextField(playerName);
		lblNewLabel_1.setLabelFor(textFieldPlayerName);
		textFieldPlayerName.setBounds(299, 175, 125, 20);
		contentPane.add(textFieldPlayerName);
		textFieldPlayerName.setColumns(10);
//		double rand = Math.random() * 10000;
//		textFieldPlayerName.setText("Player" + (int) rand);

		ButtonAbbrechen = new JButton("Abbrechen");
		ButtonAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					network.removeSeraching(client);
				} catch (RemoteException | NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ButtonAbbrechen.setBounds(278, 205, 146, 23);
		ButtonAbbrechen.setVisible(false);
		contentPane.add(ButtonAbbrechen);

		ButtonSpielstarten = new JButton("Spiel starten");
		ButtonSpielstarten.setBounds(52, 205, 140, 23);
		contentPane.add(ButtonSpielstarten);

		Label LabelLevel = new Label("Level");
		LabelLevel.setAlignment(Label.CENTER);
		LabelLevel.setBounds(20, 231, 46, 22);
		contentPane.add(LabelLevel);

		textField_2 = new JTextField();
		textField_2.setBounds(82, 261, 187, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.setVisible(false);

		btnAuswhlen = new JButton("Ausw\u00E4hlen");
		btnAuswhlen.setBounds(278, 261, 109, 22);
		btnAuswhlen.setVisible(false);
		btnAuswhlen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				textField_2.setText(Serializer.getStringPath(frame));
			}

		});
		contentPane.add(btnAuswhlen);

		JSeparator separator = new JSeparator();
		separator.setBounds(0, 242, 434, 9);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(241, 0, 1, 243);
		contentPane.add(separator_1);
		ButtonSpielstarten.setVisible(false);
		ButtonSpielstarten.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LabelLoading.setVisible(true);
				ButtonSpielstarten.setVisible(false);
				if (isHost) {
					try {
						level = Serializer.readfromString(textField_2.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				Thread t = new Thread(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						while (true) {
							if (spiel_server == true) {
								if (spiel_client == true) {
									gestartet = true;
									break;
								}
							}
							try {
								Thread.sleep(50);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
						Nachricht("Beide haben gestartet", Icons.OK);
						LabelLoading.setVisible(false);

						new Game(level);
						Game.frame.setVisible(true);
					}

				});
				t.start();
				if (game_client != null) {
					
					
					try {
						game_client.Spielstarten();
					} catch (RemoteException | NotBoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					
					
							Thread pause = new Thread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									while (true) {
										try {
											if (game_client.getStart()) {
												Thread.sleep(50);
												break;
											}
										} catch (RemoteException | NotBoundException | InterruptedException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									}
									try {
										level = game_client.getLevel();
									} catch (RemoteException
											| NotBoundException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}

									Nachricht("Level wird geladen", Icons.OK);

									if (level != null) {
										new Game(level);
										Game.frame.setVisible(true);
										LabelLoading.setVisible(false);
										Spielfigur player = null;
										try {
											player = game_client
													.getSpielfigur(Game.player);
											level.addPlayer(player);
										} catch (RemoteException
												| NotBoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										while (true) {
											Spielfigur speicher = null;
											try {
												speicher = game_client
														.getSpielfigur(Game.player);
												player.setX(speicher.getX());
												player.setY(speicher.getY());

											} catch (RemoteException
													| NotBoundException e) {
												// TODO Auto-generated catch
												// block
												Game.frame.dispose();
												Painter.run = false;
												Nachricht(
														"Gegner hat das Spiel verlassen!",
														Icons.ERROR);
											}

										}
									}
								}

							});
							pause.start();
							;

				} else {
					spiel_server = true;
				}

			}

		});
		lblServerGestartet.setVisible(false);

	}

	public static void Nachricht(String string, Icons icon) {
		lblServerGestartet.setText(string);
		if (icon == Icons.OK) {
			LabelIcon
					.setIcon(new ImageIcon(
							Multiplayer.class
									.getResource("/de/dataport/window/graphics/gruener_haken.gif")));
		} else if (icon == Icons.QUESTION) {
			LabelIcon
					.setIcon(new ImageIcon(
							Multiplayer.class
									.getResource("/de/dataport/window/graphics/gruener_haken.gif")));
		} else if (icon == Icons.ERROR) {
			LabelIcon
					.setIcon(new ImageIcon(
							Multiplayer.class
									.getResource("/de/dataport/window/graphics/rotes_kreuz.gif")));
		}
		lblServerGestartet.setVisible(true);
		LabelIcon.setVisible(true);

	}

	public static void SpielstartenButon() {
		ButtonSpielstarten.setVisible(true);
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (isHost) {
					while (textField_2.getText().equals("")) {

						ButtonSpielstarten.setEnabled(false);
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				ButtonSpielstarten.setEnabled(true);
			}

		});
		t.start();

	}
}
