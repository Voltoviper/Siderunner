package de.dataport.network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import de.dataport.Objekte.Level;
import de.dataport.Objekte.Spielfigur;
import de.dataport.window.Multiplayer;

public class Game_Link_Client
{
	Registry registry;
	Game_Link_Interface stub;
	public Client start(Client client) throws RemoteException, NotBoundException
	{

		 registry = LocateRegistry.getRegistry(client.getIp(), 1101);
		 stub = (Game_Link_Interface) registry.lookup("Game_Link");

		return stub.getClient(Multiplayer.client);
	}
	public boolean Spielstarten() throws RemoteException, NotBoundException{
		
		return stub.Spielstarten();
		
	}
	public Level getLevel() throws RemoteException, NotBoundException{
		return stub.getLevel();
	}
	
	public Spielfigur getSpielfigur(Spielfigur player) throws RemoteException, NotBoundException{
		
		return stub.getSpielfigur(player);
		
	}
	public boolean pause(boolean pause) throws RemoteException, NotBoundException{
		return stub.isPaused(pause);
	}
}
