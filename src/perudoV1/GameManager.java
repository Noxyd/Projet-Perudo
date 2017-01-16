package perudoV1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameManager extends Remote{

	public String rejoindre() throws RemoteException;
	
}
