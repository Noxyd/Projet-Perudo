package perudoV3;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Classe Main.
 * Programme principal.
 * 
 * @author Groupe Garcia, Lesaichot, Tavera - STRI
 * 
 */

public class Main {
	public static void main(String[] args){
		
		try {
			
			//Creation of the gamemanager
			GameManagerImpl gm = new GameManagerImpl();
			
			//Turn on the registry
			LocateRegistry.createRegistry(1099);
			
			System.out.println("[SERVER ONLINE]");
			
			//Bind url with the shared game object
			Naming.rebind("main-gm", gm);
			
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
		
	}
}