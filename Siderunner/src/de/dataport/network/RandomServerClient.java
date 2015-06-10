package de.dataport.network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RandomServerClient {
	
	Game_FinderInterface stub;
	public Client start(Client client) throws RemoteException, NotBoundException {
			
			
			Registry registry = LocateRegistry.getRegistry("localhost", 1100);
			stub = (Game_FinderInterface) registry.lookup("Game_Finder");

			Client c = stub.getClient();
			return c;
			/*
			if(c!=null){
				System.out.println(c.getName());
				Game_Link_Client game_client = new Game_Link_Client();
				game_client.start(client);
			}else{
				stub.SearchGamepartner(client);
				Game_Link_Server game_server = new Game_Link_Server(client);
				game_server.start(client);
			}
			*/
	}
	
	public void waitforClient(Client client) throws RemoteException, NotBoundException{
		stub.SearchGamepartner(client);
	}
	
	public void removeSeraching(Client client) throws RemoteException, NotBoundException{
		stub.Removesearching(client);
	}

}
