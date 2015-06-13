package de.dataport.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import de.dataport.network.Client;

public class Game_Finder implements Game_FinderInterface {

	ArrayList<Client> searching = new ArrayList<Client>();

	public Game_Finder() {

	}

	public static void main(String[] args) {
		String[] options = { "OK" };
		try {

			Registry registry = LocateRegistry.createRegistry(1100);

			Game_FinderInterface stub = (Game_FinderInterface) UnicastRemoteObject
					.exportObject(new Game_Finder(), 0);
			registry.rebind("Game_Finder", stub);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (JOptionPane.showOptionDialog(null, "Server beenden?",
				"Siderunner Server", JOptionPane.PLAIN_MESSAGE,
				JOptionPane.QUESTION_MESSAGE, null, options, options[0]) == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}

	/**
	 * Fragt ab, ob ein Client in der Liste wartet. Ist dies der Fall wird
	 * dieser an den Anfragenden zurückgegeben, um eine Verbindung aufzubauen.
	 * 
	 * @return Gibt einen Client zurück der ebenfalls derzeit auf der Suche nach
	 *         einem Spiel ist.
	 */
	@Override
	public Client getClient() throws RemoteException {

		if (!searching.isEmpty()) {
			Client c = searching.get(0);
			if (searching.remove(searching.get(0)))
				System.out.println("Found: " + c.getName());
			return c;
		} else {
			return null;
		}
	}

	/**
	 * Fügt einen Clienten zur Suchenden Liste hinzu
	 * 
	 * @param client
	 *            Ein Client, um eine Verbindung aufbauen zu können
	 * @return Gibt einen boolschen Wert zurück, ob die Eintragung erfolgreich
	 *         war.
	 */
	@Override
	public boolean SearchGamepartner(Client client) throws RemoteException {
		try {
			client.setIp(RemoteServer.getClientHost());
			searching.add(client);
			System.out.println("Added: " + client.getName() + " "
					+ client.getIp());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	@Override
	public boolean Removesearching(Client client) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			if (searching.remove(client))
				System.out.println("Aborted: " + client.getName());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
