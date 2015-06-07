package de.dataport.network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Game_Link_Client
{
	public String start(String URL) throws RemoteException, NotBoundException
	{

		Registry registry = LocateRegistry.getRegistry(URL, 1101);
		Game_Link_Interface stub = (Game_Link_Interface) registry.lookup("Game_Link");

		return stub.getClient().getName();
	}
}
