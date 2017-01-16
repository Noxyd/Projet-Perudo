package perudoV1;


import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Run {
	public static void main(String[] args) throws Exception{
		try {
			LocateRegistry.createRegistry(1099);
			
			String url ="rmi://localhost/Perudo";
			
			Test_RMIImpl le_test = new Test_RMIImpl();
			Naming.rebind(url, (Remote) le_test);
			
			
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
