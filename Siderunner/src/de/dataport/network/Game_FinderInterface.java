package de.dataport.network;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Game_FinderInterface extends Remote {

	public Client getClient() throws RemoteException;
	public boolean SearchGamepartner(Client client) throws RemoteException;
}
