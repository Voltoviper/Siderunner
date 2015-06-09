package de.dataport.network;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RandomServerClient {
	public void start(Client client) throws RemoteException, NotBoundException {
			
			
			Registry registry = LocateRegistry.getRegistry("localhost", 1100);
			Game_FinderInterface stub = (Game_FinderInterface) registry.lookup("Game_Finder");

			Client c = stub.getClient();
			
			if(c!=null){
				System.out.println(c.getName());
				Game_Link_Client game_client = new Game_Link_Client();
				game_client.start(client);
			}else{
				stub.SearchGamepartner(client);
				Game_Link_Server game_server = new Game_Link_Server(client);
				game_server.start(client);
			}
			
		
	}
}
