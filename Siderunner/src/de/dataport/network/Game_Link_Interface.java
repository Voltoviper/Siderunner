package de.dataport.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

import de.dataport.Objekte.Level;

public interface Game_Link_Interface extends Remote {
	public Client getClient(Client client_Client) throws RemoteException;
	public boolean Spielstarten() throws RemoteException;
	public Level getLevel()throws RemoteException;
	public de.dataport.Objekte.Spielfigur getSpielfigur(de.dataport.Objekte.Spielfigur figur)throws RemoteException;
	public boolean stoppen(boolean gestoppt) throws RemoteException;
	public boolean getStart() throws RemoteException;
	
}
