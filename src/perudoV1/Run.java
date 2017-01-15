package perudoV1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;


public class Run {
	public static void main(String[] args) throws Exception{
		try {
			
			LocateRegistry.createRegistry(1099);
			//PartieInt la_partie = (PartieInt) new Partie(0, null);	
			Test_RMIImpl le_test = new Test_RMIImpl();
			System.out.println(le_test.toString());
			Naming.rebind("rmi://localhost:1099/partie",  le_test);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
