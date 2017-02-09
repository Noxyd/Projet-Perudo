package perudoV1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


/* 
 * TODO ce week-end:
 * 		- Gestion du menteur et tout pile (v�rifier mais il me semble que le menteur fonctionne - sam)
 * 		- Jouer une partie jusqu'� la fin pour tester le gameplay
 * 		- Afficher les d�s aux joueurs
 * 		- Am�liorer les affichages
 */

//Server
public class Main {
	public static void main(String[] args){
		
		try {
			
			GameImpl game = new GameImpl();
			
			System.out.println("[SERVER ONLINE]");
			
			//Turn on the registry
			LocateRegistry.createRegistry(1099);
			
			//Bind url with the shared game object
			Naming.rebind("game-1", game);
			
			//The game wait for the notify of the GC
			game.round();
			
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}