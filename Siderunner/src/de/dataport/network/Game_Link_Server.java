package de.dataport.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import de.dataport.Objekte.Level;
import de.dataport.Objekte.Spielfigur;
import de.dataport.window.Icons;
import de.dataport.window.Multiplayer;
import de.dataport.window.Singleplayer;

public class Game_Link_Server implements Game_Link_Interface
{
	Client client;
	Spielfigur player_client= null;

	public Game_Link_Server(Client client)
	{
		super();
		this.client = client;
	}

	public Game_Link_Server()
	{

	}

	public void start(Client client)
	{
		Registry registry;
		try
		{
			registry = LocateRegistry.createRegistry(1101);
			Game_Link_Interface stub = (Game_Link_Interface) UnicastRemoteObject.exportObject(new Game_Link_Server(client), 0);
			registry.rebind("Game_Link", stub);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Client getClient(Client client_Client) throws RemoteException
	{
		// TODO Auto-generated method stub
		System.out.println(client_Client.getName());
		Multiplayer.Nachricht(client_Client.getName(), Icons.OK);
		Multiplayer.SpielstartenButon();
		return client;
	}

	@Override
	public boolean Spielstarten() throws RemoteException
	{
		// TODO Auto-generated method stub
		Multiplayer.spiel_client = true;
		return Multiplayer.spiel_server;
	}

	@Override
	public Level getLevel() throws RemoteException
	{
		return Multiplayer.level;
	}

	@Override
	public de.dataport.Objekte.Spielfigur getSpielfigur(de.dataport.Objekte.Spielfigur figur) throws RemoteException
	{
		if (player_client == null){
			player_client=figur;
			Multiplayer.level.addPlayer(player_client);
		}else{
			player_client.setX(figur.getX());
			player_client.setY(figur.getY());
		}
		return Singleplayer.player;
	}


}
