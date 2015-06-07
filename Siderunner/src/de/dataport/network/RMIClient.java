package de.dataport.network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RMIClient {
	public void start(Client client) throws RemoteException, NotBoundException {
			
			
			Registry registry = LocateRegistry.getRegistry("localhost", 1100);
			Game_FinderInterface stub = (Game_FinderInterface) registry.lookup("Game_Finder");

			stub.SearchGamepartner(client);
			System.out.println(stub.getClient().getName());
		
	}
}
