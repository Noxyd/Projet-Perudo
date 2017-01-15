package perudoV1;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;


public class Run {
	public static void main(String[] args) throws Exception{
		
		try {
						
			PartieInt squelette = (PartieInt) new Partie(0, null);
			
			LocateRegistry.createRegistry(10000);
			
			System.out.println("Online");
			Naming.rebind("partie",  squelette);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
