package tmp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Test_RMIImpl extends UnicastRemoteObject implements Test_RMI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Test_RMIImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void sayHello() throws RemoteException {
		System.out.print("HELLO WORLD !");

	}

}
