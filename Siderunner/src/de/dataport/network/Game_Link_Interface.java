package de.dataport.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Game_Link_Interface extends Remote 
{
	public Client getClient(Client client_Client) throws RemoteException;
	public boolean Spielfigur(de.dataport.Objekte.Spielfigur spieler)throws RemoteException;
	
}
