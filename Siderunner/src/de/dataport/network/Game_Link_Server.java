package de.dataport.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Game_Link_Server implements Game_Link_Interface
{

	public void start(){
		Registry registry;
		try
		{
			registry = LocateRegistry.createRegistry(1101);
			Game_Link_Interface stub = (Game_Link_Interface) UnicastRemoteObject.exportObject(new Game_Link_Server(), 0);
			registry.rebind("Game_Link", stub);
		} catch (RemoteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public Client getClient() throws RemoteException
	{
		// TODO Auto-generated method stub
		Client c = new Client("Christoph Nebendahl", 1);
		return c ;
	}

	@Override
	public boolean Spielfigur(de.dataport.Objekte.Spielfigur spieler) throws RemoteException
	{
		// TODO Auto-generated method stub
		return false;
	}
	
}
