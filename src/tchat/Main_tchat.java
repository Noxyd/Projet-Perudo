package tchat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;


public class Main_tchat {

	public static void main(String[] args) {
		try{
			//1° On instancie l'objet "serveur" contenant les méthodes RMI
			Server_Communicator_Impl server = new Server_Communicator_Impl();
			
			//2° Démarrage du registre sur le port 1099
			LocateRegistry.createRegistry(1099);
			
			//3° On déclare le serveur dans le registre RMI
			Naming.rebind("server-1", server);
			
			System.out.println("[SERVER ONLINE]");
			
			while(true){
				//4° On garde le server en attente, pas d'autres actions dans le thread principal.
				//L'appel d'une méthode RMI génére automatiquement un nouveau thread.
			}
		}catch(RemoteException | MalformedURLException e){
			e.printStackTrace();
		}

	}

}
