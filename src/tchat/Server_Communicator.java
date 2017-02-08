package tchat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server_Communicator extends Remote{
	
	public void inscription(Client_Communicator client)throws RemoteException;
	
	public void envoyer_a_tous(String pseudo, String message)throws RemoteException;
}
