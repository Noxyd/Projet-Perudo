package tmp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Test_RMI extends Remote {
	public void sayHello() throws RemoteException;
}
