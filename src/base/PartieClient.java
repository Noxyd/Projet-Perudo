package base;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PartieClient extends Remote{
	
	public void jouer() throws RemoteException;
		
}
