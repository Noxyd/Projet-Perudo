package tchat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client_Communicator extends Remote{
	
	public String get_pseudo()throws RemoteException;
	
	public void envoyer_message(String message)throws RemoteException;
}
