package de.dataport.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import de.dataport.network.Client;

public class Game_Finder implements Game_FinderInterface{
	
	ArrayList<Client> searching = new ArrayList<Client>();

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(1100);
			
			Game_FinderInterface stub = (Game_FinderInterface) UnicastRemoteObject.exportObject(new Game_Finder(), 0);
			registry.rebind("Game_Finder", stub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Fragt ab, ob ein Client in der Liste wartet. Ist dies der Fall wird dieser an den Anfragenden zur�ckgegeben, um eine Verbindung aufzubauen.
	 * @return Gibt einen Client zur�ck der ebenfalls derzeit auf der Suche nach einem Spiel ist.
	 */
	@Override
	public Client getClient() throws RemoteException {
		if(!searching.isEmpty()){
			return searching.get(0);
		}else{
		return null;
		}
	}
/**
 * F�gt einen Clienten zur Suchenden Liste hinzu
 * @param client Ein Client, um eine Verbindung aufbauen zu k�nnen
 * @return Gibt einen boolschen Wert zur�ck, ob die Eintragung erfolgreich war.
 */
	@Override
	public boolean SearchGamepartner(Client client) throws RemoteException {
		try{
		searching.add(client);
		System.out.println("Added: "+ client.getName());
		return true;
		}catch(Exception e){
			return false;
		}
		
	}

}
