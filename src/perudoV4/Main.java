package perudoV4;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

//Server
public class Main {
	public static void main(String[] args){
		
		try {
						
			GameManagerImpl gm = new GameManagerImpl();
			
			//Turn on the registry
			LocateRegistry.createRegistry(1099);
			
			System.out.println("[SERVER ONLINE]");
			
			//Bind url with the shared game object
			Naming.rebind("main-gm", gm);
			
			//Create games
			gm.creer_partie();
			gm.creer_partie();
			
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}