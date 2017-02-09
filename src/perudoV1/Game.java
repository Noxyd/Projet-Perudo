package perudoV1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Game extends Remote {
	
	public int connexion(Clients cli)throws RemoteException;
}
