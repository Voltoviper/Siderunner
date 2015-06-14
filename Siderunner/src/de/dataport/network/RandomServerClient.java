package de.dataport.network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RandomServerClient {
	
	Game_FinderInterface stub;
	public Client start(Client client) throws RemoteException, NotBoundException {
			
			
			Registry registry = LocateRegistry.getRegistry("192.168.0.2", 1100);
			stub = (Game_FinderInterface) registry.lookup("Game_Finder");

			Client c = stub.getClient();
			return c;

	}
	
	public void waitforClient(Client client) throws RemoteException, NotBoundException{
		stub.SearchGamepartner(client);
	}
	
	public void removeSeraching(Client client) throws RemoteException, NotBoundException{
		stub.Removesearching(client);
	}

}
