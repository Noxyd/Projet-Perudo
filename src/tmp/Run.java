package tmp;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import base.GameManager;
import base.GameManagerImpl;

public class Run {
	public static void main(String[] args) throws RemoteException{
		
		GameManagerImpl gm = new GameManagerImpl();
		
		rmi_init(gm);
		
		//Cr�ation de 3 parties
		gm.creer_partie();
		gm.creer_partie();
		gm.creer_partie();
		
		//TEST Recherche d'une partie
		String url = gm.recherche_partie();
		System.out.println("\nurl : "+url);
	}
	
	/* initialisation du RMI */
	public static void rmi_init(GameManager obj){
		try {
			System.out.println("D�marrage du registre\n");
			LocateRegistry.createRegistry(1099);
			String url = "rmi://localhost:1099/gm";
			Naming.rebind(url, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
